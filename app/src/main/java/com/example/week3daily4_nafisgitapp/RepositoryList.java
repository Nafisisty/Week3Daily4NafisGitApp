package com.example.week3daily4_nafisgitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.week3daily4_nafisgitapp.events.RepositoryEvent;
import com.example.week3daily4_nafisgitapp.model.datasource.okhttp.OkHttpHelper;
import com.example.week3daily4_nafisgitapp.model.user.RepositoryResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositoryList extends AppCompatActivity {

    String repo_url = "";
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        recyclerView = findViewById(R.id.recyclerViewId);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            repo_url = bundle.getString("repoUrl");
        }

        OkHttpHelper.asyncOkHttpApiCall(repo_url);
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void repositoryEvent(RepositoryEvent event) {
        RepositoryResponse[] repositoryResponse = event.getRepositoryResponse();

        List<RepositoryResponse> repositoryResponseList = Arrays.asList(repositoryResponse);

        recyclerViewAdapter = new RecyclerViewAdapter(repositoryResponseList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
