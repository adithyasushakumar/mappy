package com.adhithya.track.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adhithya.track.R;

import com.adhithya.track.homeMap.MapActivity;
import com.adhithya.track.model.User;
import com.adhithya.track.responce.LoginResponce;
import com.adhithya.track.service.Config;
import com.adhithya.track.service.RetroClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtUserName;
    EditText edtPassword;
    Button btnLogin;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intViews();
        setLidtener();
    }

    private void intViews() {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        edtUserName = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void setLidtener() {
        btnLogin.setOnClickListener(this);
    }

    private void Login() {
        String key = Config.KEY;
        String username = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        pDialog.setMessage("LoginResponce");
        pDialog.show();
        login(username, password, key).enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {

                if (response.body().getStatus()==1){
                    pDialog.dismiss();
                    String message = response.body().getMessage();
                    List<User> mUserLis = response.body().getUser();
                    User singleUser = mUserLis.get(0);
                    Intent in=new Intent(MainActivity.this,MapActivity.class);
                   in.putExtra("uid",singleUser.getUid());
                    startActivity(in);
                    Toast.makeText(MainActivity.this, message + " USER " + singleUser.getName(), Toast.LENGTH_SHORT).show();
                }else {
                    pDialog.dismiss();
                    Toast.makeText(MainActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {

            }
        });
    }

    private Call<LoginResponce> login(String username, String password, String key) {
        return RetroClient.getApiService().userLogin(username, password, key);
     }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Login();
                break;
        }
    }
}
