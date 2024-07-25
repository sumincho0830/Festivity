package org.techtown.festivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SIGNUP = 101;
    EditText usernameInput;
    EditText passwordInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        TextView loginButton = findViewById(R.id.loginButton_txt);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                Info newInfo = new Info(username, password);

                Iterator<Info> iterator = UserData.userInfo.iterator();

                boolean flag = false;
                while(iterator.hasNext()){
                    Info element = iterator.next();

                    if(element.equals(newInfo)){
                        flag = true;
                        showToast("로그인되었습니다");

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }
                }
                if(flag == false){
                    showToast("올바른 Id와 비밀번호를 입력해주세요");
                }
            }
        });

        TextView signupButton = findViewById(R.id.signupButton_txt);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SIGNUP);
            }
        });
    }

    public void showToast(String data){
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }
}