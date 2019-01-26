package com.example.week3daily4_nafisgitapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week3daily4_nafisgitapp.model.user.RepositoryResponse;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<RepositoryResponse> repositoryResponses;

    public RecyclerViewAdapter(List<RepositoryResponse> repositoryResponses) {
        this.repositoryResponses = repositoryResponses;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repository, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {

        RepositoryResponse response = repositoryResponses.get(position);

        if(response != null) {

            viewHolder.repositoryNameTextView.setText(response.getName());
            viewHolder.repositoryDescriptionTextView.setText(response.getDescription());
            viewHolder.repositoryUrlTextViewId.setText(response.getHtmlUrl());
        }

    }

    @Override
    public int getItemCount() {
        return repositoryResponses != null ? repositoryResponses.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView repositoryNameTextView;
        TextView repositoryDescriptionTextView;
        TextView repositoryUrlTextViewId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            repositoryNameTextView = itemView.findViewById(R.id.repositoryNameTextViewId);
            repositoryDescriptionTextView = itemView.findViewById(R.id.repositoryDescriptionTextViewId);
            repositoryUrlTextViewId = itemView.findViewById(R.id.repositoryUrlTextViewId);

        }
    }
}
