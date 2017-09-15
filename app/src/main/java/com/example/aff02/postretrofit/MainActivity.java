package com.example.aff02.postretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public EditText edtname,edtpass;
    public Button btnreg;
    public TextView txtresponse;
    private static final String TAG = "MainActivity";
    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnreg = (Button)findViewById(R.id.btnreg);
        edtname = (EditText)findViewById(R.id.edtpartyname);
        edtpass = (EditText)findViewById(R.id.edtpass);
        txtresponse = (TextView)findViewById(R.id.txtresponse);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtname.getText().toString();
                String pass = edtpass.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APInterface apInterface = retrofit.create(APInterface.class);

                Call<DataModel> call = apInterface.savePost(name,pass);

                call.enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                        if (response.isSuccessful())
                        {
                            showResponse(response.body().toString());
                            Toast.makeText(getApplicationContext(),"POST Submitted to API",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {

                        Toast.makeText(getApplicationContext(),"Failed :-(",Toast.LENGTH_LONG).show();
                    }
                });

            }
            });
        }

        public void showResponse (String response)
        {
            if (txtresponse.getVisibility() == View.GONE)
            {
                txtresponse.setVisibility(View.VISIBLE);
            }
            txtresponse.setText(response);
        }
    }
