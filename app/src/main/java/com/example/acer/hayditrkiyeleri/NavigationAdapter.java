package com.example.acer.hayditrkiyeleri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Navigation adapterdaki recyclerviewın sınıfı
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder>{
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<NavigationDrawerItem> mDataList;
    FragmentManager fragmentManager;

    NavigationAdapter(Context c,ArrayList<NavigationDrawerItem> data,FragmentManager fragmentManager){
        this.context = c;
        this.layoutInflater = LayoutInflater.from(context);
        this.mDataList = data;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.navigation_recview,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NavigationDrawerItem tiklanilan = mDataList.get(position);
        holder.setData(tiklanilan,position);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView baslik;
        ImageView resim;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            baslik = itemView.findViewById(R.id.titlerec);
            resim = itemView.findViewById(R.id.imgIcon);
            // Tıklandığında
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    switch (getAdapterPosition()){
                        case 0:
                            Log.d("mesaj","hmmm");
                            context.startActivity(new Intent(context,AracGerecActivity.class));
                            break;
                        case 2:
                            context.startActivity(new Intent(context,DenemelerimActivity.class));

                    }


                    Toast.makeText(context, "Şuna tıkladın" + getAdapterPosition(),Toast.LENGTH_SHORT).show();

                }
            });
        }

        public void setData(NavigationDrawerItem tiklanilan, int position) {
            this.baslik.setText(tiklanilan.getBaslik());
            this.resim.setImageResource(tiklanilan.getResimID());
        }
    }
}
