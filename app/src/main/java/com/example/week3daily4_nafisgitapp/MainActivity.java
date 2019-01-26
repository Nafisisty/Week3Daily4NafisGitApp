package com.example.week3daily4_nafisgitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3daily4_nafisgitapp.events.UserEvent;
import com.example.week3daily4_nafisgitapp.model.datasource.okhttp.OkHttpHelper;
import com.example.week3daily4_nafisgitapp.model.user.UserProfile;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.OkHttpClient;

import static com.example.week3daily4_nafisgitapp.model.Constants.BASE_URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView userNameTextView;
    TextView userTypeTextView;
    TextView userCompanyNameTextView;
    TextView userLocationTextView;
    TextView userPublicRepoNumberTextView;
    Button repositoryButton;

    UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.userImageViewId);
        userNameTextView = findViewById(R.id.userNameTextViewId);
        userTypeTextView = findViewById(R.id.userTypeTextViewId);
        userCompanyNameTextView = findViewById(R.id.userCompanyNameTextViewId);
        userLocationTextView = findViewById(R.id.userLocationTextViewId);
        userPublicRepoNumberTextView = findViewById(R.id.userPublicRepoNumberTextViewId);
        repositoryButton = findViewById(R.id.repositoryButtonId);

        OkHttpHelper.asyncOkHttpApiCall(BASE_URL);

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
    public void userEvent(UserEvent event) {
        if(event != null) {
            userProfile = event.getUser();
//            Log.d("TAG", "userEvent: " + userProfile.getCompany());

            Glide.with(imageView)
                    .load("" + userProfile.getAvatarUrl())
                    .into(imageView);

            userNameTextView.setText(userProfile.getLogin());
            userTypeTextView.setText(userProfile.getType());
            userCompanyNameTextView.setText(userProfile.getCompany());
            userLocationTextView.setText(userProfile.getLocation());
            userPublicRepoNumberTextView.setText("Public Repository = " + userProfile.getPublicRepos());

        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, RepositoryList.class);
        intent.putExtra("repoUrl", userProfile.getReposUrl());
        startActivity(intent);
    }
}
