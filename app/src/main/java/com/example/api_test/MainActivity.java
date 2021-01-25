package com.example.api_test;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // text view has id => text_view_result
        mTextViewResult = findViewById(R.id.text_view_result);

        // create OkHttpClient to use REST API
        OkHttpClient client = new OkHttpClient();

        // url for getting data
        String url = "https://reqres.in/api/users?page=2";

        // Creating request objects for make network calls
        // source https://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html#:~:text=OkHTTP%20is%20an%20open%20source,dependency%20to%20group%20ID%20com.
        Request request = new Request.Builder()
                .url(url)
                .build();

        // enqueue -> asynchronous
        // execute -> synchronous
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    // Change response to String type
                    String myResponse = response.body().string();

                    // This MainActivity.this.runOnUiThread is updating current UI
                    MainActivity.this.runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            mTextViewResult.setText((myResponse));
                        }
                    });
                }
            }
        });

    }


}