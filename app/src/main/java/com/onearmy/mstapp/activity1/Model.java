package com.onearmy.mstapp.activity1;

import android.text.TextUtils;
import android.util.Patterns;

public class Model implements Imodel{
    String email,password;

    public Model(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int isValid() {
        if(TextUtils.isEmpty(email)){
            return 1;
        }
        else if (TextUtils.isEmpty(password)){
            return 2;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return 3;
        }
        else if(password.length()<10){
            return 4;
        }
        else{
            //vaid login not
            //havve to check correcrt creds
            return -1;
        }
    }
}
