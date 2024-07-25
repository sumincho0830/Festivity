package org.techtown.festivity;

import androidx.annotation.Nullable;

public class Info {
    String username;
    String password;

    public Info(String username, String password){
        this.username = username;
        this.password = password;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Info) {
            Info info = (Info) obj;

            return this.username.equals(info.username) && this.password.equals(info.password);
        }else{
            return false;
        }
        //둘이 같지 않은 경우와 obj가 Info가 아닌 경우를 구분지을 방법은? return은 true와 false밖에 없다.
        //true의 경우 instanceof Info && usearname같음 && password같음
        //false의 경우 instanceof Info X || ! username || ! password
        // -> 달리 표현할 길이 없다.
    }
}
