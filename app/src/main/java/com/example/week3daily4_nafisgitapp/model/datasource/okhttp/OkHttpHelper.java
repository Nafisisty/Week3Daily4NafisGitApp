package com.example.week3daily4_nafisgitapp.model.datasource.okhttp;

import android.util.Log;

import com.example.week3daily4_nafisgitapp.events.RepositoryEvent;
import com.example.week3daily4_nafisgitapp.events.UserEvent;
import com.example.week3daily4_nafisgitapp.model.user.RepositoryResponse;
import com.example.week3daily4_nafisgitapp.model.user.UserProfile;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.week3daily4_nafisgitapp.model.Constants.BASE_URL;

public class OkHttpHelper {

    public static void asyncOkHttpApiCall(final String url) {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResponse;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResponse = response.body().string();
                Log.d("TAG", "onResponse: " + jsonResponse);

                Gson gson = new Gson();

                if(url == BASE_URL) {
                    UserProfile userProfile = gson.fromJson(jsonResponse, UserProfile.class);
                    System.out.println("Getting User Info");
                    EventBus.getDefault().post(new UserEvent(userProfile));
                }
                else {
                    RepositoryResponse[] repositoryResponse = gson.fromJson(jsonResponse, RepositoryResponse[].class);
                    System.out.println("Getting Repository Info");
                    EventBus.getDefault().post(new RepositoryEvent(repositoryResponse));
                }
            }
        });
    }
}
