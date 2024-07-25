package org.techtown.festivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;

public class SignupActivity extends AppCompatActivity {
    EditText usernameInput;
    EditText passwordInput;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                Info newInfo = new Info(username, password);

                if(username.length() > 2 && password.length() > 2){
                    Iterator<Info> iterator = UserData.userInfo.iterator();

                    boolean flag = false;
                    while(iterator.hasNext()){
                        Info element = iterator.next();

                        if(element.equals(newInfo)){
                            flag = true; //같은 값이 있다면
                            showToast("중복되는 ID입니다. 다른 ID를 입력해주세요.");
                        }
                    }
                    if(flag == false){
                        UserData.userInfo.add(newInfo); //새로운 정보 입력
                        showToast("회원 가입 완료.");
                        finish();
                    }
                }else{
                    showToast("3자리 이상의 값을 입력해주세요");
                }
            }
        });
    }

    public void showToast(String data){
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }
}
