package ru.uni_verse.quotetest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    MyTask myTask;
    OkHttpClient mOkHttpClient;
    Request mRequest;
    Call mCall;

    Button mButtonNewQuote;
    TextView mQuote;
    TextView mAuthor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOkHttpClient = new OkHttpClient();

        mButtonNewQuote = (Button)findViewById(R.id.buttonNewQuote);
        mButtonNewQuote.setOnClickListener(onClickListener);

        mQuote = (TextView)findViewById(R.id.quoteTextView);
        mAuthor = (TextView)findViewById(R.id.authorTextView);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            final String[] newResponse = new String[1];
            switch (v.getId()){
                case R.id.buttonNewQuote:
                {
                   /* mRequest = new Request.Builder().url("http://www.filltext.com/?rows=1&author={firstName}~{lastName}&quote={lorem|20}").build();/*http://api.theysaidso.com/qod.json
                    mCall = mOkHttpClient.newCall(mRequest);
                    mCall.enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            newResponse[0] = response.body().toString();
                        }
                    });*/
                    myTask = new MyTask();
                    myTask.execute();

                }
                break;
            }
  //          mQuote.setText(newResponse[0]);
  //          Log.v("Okhttp", newResponse[0]);
        }
    };

    class MyTask extends AsyncTask<Void, Void, String>{


        //Response mResponse;
        @Override
        protected String doInBackground(Void... params) {
            mRequest = new Request.Builder().url("http://echo.jsontest.com/quote/Here_we_go_again/author/Albert_Einstein").build();
            mCall = mOkHttpClient.newCall(mRequest);
            Response response;
            try {
                response = mCall.execute();
                if (response.isSuccessful()){
                    Log.v("OkHttp", response.body().string());
                    return response.body().toString();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String resp) {
            super.onPostExecute(resp);

                mQuote.setText(resp);

        }

    }

}
