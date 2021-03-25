package com.onearmy.mstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.onearmy.mstapp.activity2.MyAdapter;
import com.onearmy.mstapp.activity2.RetroModel;

import java.io.Serializable;
import java.util.List;

public class Item_activity extends AppCompatActivity implements Serializable {
    EditText userid,id,body;
    Button save;
    List<RetroModel> DataList;
    int positon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_activity);

        userid = (EditText)findViewById(R.id.ETuserId);
        id = (EditText)findViewById(R.id.ETid);
        body = (EditText)findViewById(R.id.ETbody);
        save = (Button) findViewById(R.id.BtnSave);

        Intent intent = getIntent();
        positon = intent.getIntExtra("position",0);
        RetroModel data = (RetroModel) intent.getSerializableExtra("data");


        userid.setText(data.getUserId());
        id.setText(data.getId());
        body.setText(data.getBody());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setUserId(userid.getText().toString());
                data.setId(id.getText().toString());
                data.setBody(body.getText().toString());
                Intent back = new Intent();
                back.putExtra("data",data);
                back.putExtra("position",positon);
                setResult(1001,back);
                finish();
            }
        });



    }
}