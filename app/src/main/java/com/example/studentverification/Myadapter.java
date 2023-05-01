package com.example.studentverification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

    private Context context;
    private ArrayList userId, itemName, itemColor, itemDescription;

    Myadapter(Context context,
                  ArrayList userId,
                  ArrayList itemName,
                  ArrayList itemColor,
                  ArrayList itemDescription){
        this.context = context;
        this.userId = userId;
        this.itemName = itemName;
        this.itemColor = itemColor;
        this.itemDescription = itemDescription;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.itemId1.setText(String.valueOf(userId.get(position)));
        holder.itemName1.setText(String.valueOf(itemName.get(position)));
        holder.itemColor1.setText(String.valueOf(itemColor.get(position)));
        holder.itemDescription1.setText(String.valueOf(itemDescription.get(position)));


    }

    @Override
    public int getItemCount() {
        return userId.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemId1, itemName1, itemColor1, itemDescription1;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemId1 = itemView.findViewById(R.id.idText);
            itemName1 = itemView.findViewById(R.id.itemName);
            itemColor1 = itemView.findViewById(R.id.textColor);
            itemDescription1 = itemView.findViewById(R.id.textDescription);

        }
    }

}
