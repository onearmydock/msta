package com.onearmy.mstapp.activity2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.onearmy.mstapp.Item_activity;
import com.onearmy.mstapp.R;

import java.io.Serializable;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Callback;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final String TAG = "myerror";
    private List<RetroModel> DataList;
    private Context context;
    private OnItemClick mItemClick;

    public MyAdapter(List<RetroModel> dataList, Context context,OnItemClick mItemClick) {
        DataList = dataList;
        this.context = context;
        this.mItemClick = mItemClick;
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        return new ViewHolder(view,mItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.userid.setText("Userid:"+DataList.get(position).getUserId());
        holder.id.setText("id:"+DataList.get(position).getId());
        holder.body.setText("body:"+DataList.get(position).getBody());
        Log.d(TAG , "onBindViewHolder: "+DataList.get(position).getUserId());

    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView userid,id,body;
         OnItemClick itemClick;

        public ViewHolder(@NonNull View itemView,OnItemClick itemClick) {
            super(itemView);
            userid = (TextView)itemView.findViewById(R.id.userId);
            id= (TextView)itemView.findViewById(R.id.id);
            body = (TextView)itemView.findViewById(R.id.body);
            this.itemClick = itemClick;

            itemView.setOnClickListener(this);



            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int p = getAdapterPosition();
                    //Toast.makeText(context, p, Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("delete selected querry");
                    builder.setCancelable(true);
                    builder.setPositiveButton(
                            "delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    RemoveItem(getAdapterPosition());
                                }
                            });
                    builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d(TAG, "onClick: dont delete");
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Log.d(TAG, "onLongClick: position"+p);
                    return true;
                }
            });

        }


        @Override
        public void onClick(View v) {
            itemClick.Onclick(getAdapterPosition());

        }
    }
    public void RemoveItem(int position){
        DataList.remove(position);
        notifyDataSetChanged();
        Log.d(TAG, "RemoveItem: item removesd");
    }
    public void EditItem(int position,RetroModel data){
        DataList.set(position,data);
        notifyDataSetChanged();
        Log.d(TAG, "EditItem: item changed" +data.getId());
    }

    public interface OnItemClick {
        public void Onclick(int Position);
    }
}

