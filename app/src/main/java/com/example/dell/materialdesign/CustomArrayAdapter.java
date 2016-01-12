package com.example.dell.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by DELL on 28-12-2015.
 */
public class CustomArrayAdapter extends RecyclerView.Adapter<CustomArrayAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<information> data= Collections.emptyList();
    Context context;
    private Clicklistener clicklistener;
    public CustomArrayAdapter(Context context,List<information>data){
        this.context=context;
     inflater=LayoutInflater.from(context);
        this.data=data;
    }
    public  void setclicklistener(Clicklistener clicklistener){
        this.clicklistener=clicklistener;
    }
    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
View view=inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
information current=data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.IconId);
     /*   holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"clicked at position "+ position,Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.texticon);
            icon=(ImageView)itemView.findViewById(R.id.imageicon);
           icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context,SubActivity.class));
            //delete(getAdapterPosition());
           // Toast.makeText(context,"clicked at position "+getAdapterPosition(),Toast.LENGTH_SHORT).show();
            if(clicklistener!=null){
                clicklistener.itemclick(v,getAdapterPosition());
            }
        }
    }
    public  interface  Clicklistener {
        public  void itemclick(View view,int position);
    }
}
