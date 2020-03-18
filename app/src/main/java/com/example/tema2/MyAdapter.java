package com.example.tema2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Review> mDataset;


    public MyAdapter(List<Review> dataset)
    {
        mDataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fullName.setText(mDataset.get(position).fullName);
        holder.mark.setText(mDataset.get(position).mark);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView fullName;
        public TextView mark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.full_name);
            mark = itemView.findViewById(R.id.mark);
        }
    }

    public void addItem(Review review)
    {
        mDataset.add(review);
        notifyDataSetChanged();
    }

    public void deleteItem(Review review)
    {
        for (Review r : mDataset)
        {
            if(r.fullName.equals(review.fullName))
            {
                mDataset.remove(r);
                notifyDataSetChanged();
                break;
            }
        }
    }
}
