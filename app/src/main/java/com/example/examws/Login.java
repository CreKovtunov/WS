package com.example.examws;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examws.ui.notifications.NotificationsFragment;
import com.example.examws.ui.notifications.NotificationsViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText password;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        if(getLogin().length() > 1) {
            email.setText(getLogin());
        }

        findViewById(R.id.sign_in_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = email.getText().toString();
                String pass = password.getText().toString();
                if (login.contains("@") && !pass.isEmpty()) {
                    signRequest(new LoginModel(login, pass));
                } else
                    Toast.makeText(Login.this, "Данные введены неверно", Toast.LENGTH_SHORT).show();
            }
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), Main.class));
//            }
        });
        findViewById(R.id.Profile_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Main.class));
            }
        });
    }
    private void signRequest(LoginModel loginModel) {
        NetworkServices.getInstance().getJSONApi().login(loginModel).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Intent intent1 = new Intent(Login.this, Main.class);
                    saveLoginInfo(loginModel.getEmail(), loginModel.getPassword());
                    startActivity(intent1);
                } else
                    Toast.makeText(Login.this, "Данные введены неверно", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveLoginInfo (String email, String password){
        SharedPreferences prefs = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("login", email);
        prefEdit.putString("pass", password);
        prefEdit.apply();
    }

    private String getLogin() {
        return getSharedPreferences("loginInfo",MODE_PRIVATE).getString("login", "");
    }
}