package com.onearmy.mstapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.onearmy.mstapp.activity2.MyAdapter;
import com.onearmy.mstapp.activity2.RetroModel;
import com.onearmy.mstapp.activity2.Retroapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity implements MyAdapter.OnItemClick{
    private static final String TAG = "myerror" ;
    TextView userid;
    RecyclerView recyclerView;
    List<RetroModel> DataList;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    private void init() {
        userid = (TextView) findViewById(R.id.userId);
        recyclerView = (RecyclerView)findViewById(R.id.recycleView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Retroapi api =retrofit.create(Retroapi.class);

        Call<List<RetroModel>> call =api.getJasonList();
        call.enqueue(new Callback<List<RetroModel>>() {
            @Override
            public void onResponse(Call<List<RetroModel>> call, Response<List<RetroModel>> response) {
                if(!response.isSuccess()){
                    Log.d(TAG, "onResponse: " + response.code());
                }
                DataList = response.body();
                adapter = new MyAdapter(DataList,MainActivity2.this,MainActivity2.this::Onclick);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<RetroModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: retro faliure" + t.getMessage());
            }
        });
    }

    @Override
    public void Onclick(int Position) {
        RetroModel data = DataList.get(Position);
        Log.d(TAG, "Onclick: "+data.getBody());
        Intent edit = new Intent(this,Item_activity.class);
        edit.putExtra("data",data);
        edit.putExtra("position",Position);

        startActivityForResult(edit,1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1001){
            RetroModel returned = (RetroModel) data.getSerializableExtra("data");
            int returnedPOsition = data.getIntExtra("position",0);
            adapter.EditItem(returnedPOsition,returned);
        }
    }
}