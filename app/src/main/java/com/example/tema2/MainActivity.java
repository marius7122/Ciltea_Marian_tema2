package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter mAdapter;

    Button newReviewBtn, deleteReviewBtn;
    EditText fullNameEdit, markEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newReviewBtn = findViewById(R.id.add_btn);
        deleteReviewBtn = findViewById(R.id.delete_btn);

        fullNameEdit = findViewById(R.id.full_name);
        markEdit = findViewById(R.id.mark);

        newReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
            }
        });
        deleteReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReview();
            }
        });


        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getReviews();
    }


    private void getReviews()
    {
        LinearLayoutManager x = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(x);
        class GetUsers extends AsyncTask<Void,Void, List<Review>>
        {
            @Override
            protected List<Review> doInBackground(Void... voids) {
                List<Review> reviews = DatabaseHandle.getInstance(getApplicationContext()).getAppDatabase().reviewDao().getAll();
                return reviews;
            }

            @Override
            protected void onPostExecute(List<Review> reviews) {
                super.onPostExecute(reviews);
                mAdapter = new MyAdapter(reviews);
                mRecyclerView.setAdapter(mAdapter);
            }
        }

        GetUsers getUsers = new GetUsers();
        getUsers.execute();
    }

    private void addReview()
    {
        final String fullName = fullNameEdit.getText().toString().trim();
        final String mark = markEdit.getText().toString().trim();

        if(fullName.isEmpty())
        {
            fullNameEdit.setError("Name required");
            fullNameEdit.requestFocus();
            return;
        }
        if(mark.isEmpty())
        {
            markEdit.setError("Mark required");
            markEdit.requestFocus();
            return;
        }

        final Review review = new Review(fullName, mark);
        class AddReview extends AsyncTask<Void, Void, Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseHandle.getInstance(getApplicationContext()).getAppDatabase().reviewDao().insert(review);
                return null;
            }

            @Override
            protected void onPostExecute(Void voids) {
                super.onPostExecute(voids);
                mAdapter.addItem(review);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        AddReview addReview = new AddReview();
        addReview.execute();
    }



    private void deleteReview()
    {
        final String mName = fullNameEdit.getText().toString().trim();

        if(mName.isEmpty()){
            fullNameEdit.setError("Name is required");
            fullNameEdit.requestFocus();
            return;
        }

        class DeleteReview extends AsyncTask<Void,Void,Void> {
            Boolean found = false;
            Review review;
            @Override
            protected Void doInBackground(Void... voids){
                review = DatabaseHandle.getInstance(getApplicationContext()).getAppDatabase().reviewDao().findByName(mName);
                if(review != null) {
                    this.found = true;
                    DatabaseHandle.getInstance(getApplicationContext()).getAppDatabase().reviewDao().delete(review);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void voids){
                super.onPostExecute(voids);
                if(this.found == true) {
                    Toast.makeText(getApplicationContext(), "Review Deleted!", Toast.LENGTH_LONG).show();
                    mAdapter.deleteItem(review);
                }
                else {
                    markEdit.setText("");
                    Toast.makeText(getApplicationContext(), "Review not found!", Toast.LENGTH_LONG).show();
                }
            }
        }
        DeleteReview deleteReview = new DeleteReview();
        deleteReview.execute();
    }

}
