package com.onearmy.mstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.onearmy.mstapp.activity1.Iview;
import com.onearmy.mstapp.activity1.Presenter;

public class MainActivity extends AppCompatActivity implements Iview {

    EditText EtEmail;
    EditText EtPassword;
    Button BtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Presenter presenter = new Presenter(this);
        BtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                presenter.doOnLogin(EtEmail.getText().toString(),EtPassword.getText().toString());

            }
        });
    }

    public void init(){
        EtEmail = (EditText)findViewById(R.id.editTextemail);
        EtPassword = (EditText)findViewById(R.id.editTextpassword);
        BtLogin = (Button)findViewById(R.id.BtLogin);
    }

    @Override
    public void login(String message,Boolean validity) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (validity == true){
            Intent mainIntent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(mainIntent);
        }
    }
}