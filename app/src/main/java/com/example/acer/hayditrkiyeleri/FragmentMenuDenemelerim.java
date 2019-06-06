package com.example.acer.hayditrkiyeleri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Repository;
import com.example.acer.hayditrkiyeleri.Util.RVItemGenerator;
import com.example.acer.hayditrkiyeleri.Util.RVItems.Denemelerim.Item_Denemelerim;
import com.example.acer.hayditrkiyeleri.Util.ViewModels.DenemelerimViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentMenuDenemelerim extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_denemelerim, container, false);

        DenemelerimViewModel viewModel= ViewModelProviders.of(getActivity()).get(DenemelerimViewModel.class);
        Repository myRepo=new Repository();
        myRepo.setDao(((ThisApplication)getActivity().getApplication()).get_dao()); //This will change by dagger
        viewModel.setMyRepo(myRepo);

        RecyclerView recyclerView= view.findViewById(R.id.RV_denemelerim);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        DenemelerimAdapter adapter=new DenemelerimAdapter();
        adapter.setItems(new ArrayList<>(0)); //In order to prevent errors
        recyclerView.setAdapter(adapter);

        viewModel.get_denemelerLive().observe(getActivity(), new Observer<List<DenemeEntity>>() {
            @Override
            public void onChanged(List<DenemeEntity> denemeEntities) {
                ArrayList<Item_Denemelerim> array= RVItemGenerator.pump_Item_denemelerim(denemeEntities);
                adapter.setItems(array);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }



    private class DenemelerimAdapter extends RecyclerView.Adapter<DenemelerimAdapter.DenemelerimViewHolder>{

        private ArrayList<Item_Denemelerim> items;

        public void setItems(ArrayList<Item_Denemelerim> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public DenemelerimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_denemelerim_denemeitem, parent, false);

            return new DenemelerimViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DenemelerimViewHolder holder, int position) {

            Item_Denemelerim item= items.get(position);

            holder.btn.setText(item.getDeneme_isim());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class DenemelerimViewHolder extends RecyclerView.ViewHolder{

            Button btn;

            public DenemelerimViewHolder(@NonNull View itemView) {
                super(itemView);

                btn=itemView.findViewById(R.id.btn_denemelerim_denemegoster);



                btn.setOnClickListener((View view)->{

                    Item_Denemelerim this_item= items.get(getAdapterPosition());
                    int denemeid=this_item.getDeneme_id();

                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    FragmentMenuDenemeGoster denemeGoster=new FragmentMenuDenemeGoster(denemeid);
                    fragmentTransaction.replace(R.id.fragment_container, denemeGoster);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                });
            }
        }
    }
}

