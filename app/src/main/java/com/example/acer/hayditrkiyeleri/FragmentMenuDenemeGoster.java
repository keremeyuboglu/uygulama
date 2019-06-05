package com.example.acer.hayditrkiyeleri;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Repository;
import com.example.acer.hayditrkiyeleri.Util.RVItemGenerator;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeGoster.Item_DenemeGoster_inner;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeGoster.Item_DenemeGoster_outer;
import com.example.acer.hayditrkiyeleri.Util.ViewModels.DenemeGosterViewModel;

import java.util.ArrayList;

public class FragmentMenuDenemeGoster extends Fragment {


    private final int deneme_id;

    public FragmentMenuDenemeGoster(int deneme_id){
        this.deneme_id=deneme_id;
    }

    private DenemeGosterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_denemegoster_silinebilir, container, false);
        viewModel= ViewModelProviders.of(this).get(DenemeGosterViewModel.class);
        Repository myRepo=new Repository();
        myRepo.setDao(((ThisApplication)getActivity().getApplication()).get_dao());
        viewModel.setMyRepo(myRepo);


        RecyclerView RV= v.findViewById(R.id.RV_denemegoster_outer);
        RV.setHasFixedSize(true);
        RV.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        DenemeGosterAdapter outer_adapter= new DenemeGosterAdapter();

        RV.setAdapter(outer_adapter);

        viewModel.get_denemeLive(deneme_id).observe(this, new Observer<DenemeEntity>() {
            @Override
            public void onChanged(DenemeEntity denemeEntity) {
                ArrayList<Item_DenemeGoster_outer> outer_items=RVItemGenerator.pump_ItemGoster(denemeEntity);
                Log.i("deneme", ""+outer_items.size());
                outer_adapter.setOuter_items(outer_items);
                outer_adapter.notifyDataSetChanged();

            }
        });



        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }












    private class DenemeGosterAdapter extends RecyclerView.Adapter<DenemeGosterAdapter.DenemeGosterViewHolder>{

        private ArrayList<Item_DenemeGoster_outer> outer_items=new ArrayList<>(0);


        public void setOuter_items(ArrayList<Item_DenemeGoster_outer> outer_items) {
            this.outer_items = outer_items;
        }



        @NonNull
        @Override
        public DenemeGosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_denemegoster_dersitem_silinebilir, parent, false);

            return new DenemeGosterViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DenemeGosterViewHolder holder, int position) {
            Item_DenemeGoster_outer out_item= outer_items.get(position);

            holder.dersisim.setText(out_item.getDers_isim());

            //Initialize inner recyclerview
            if(out_item.getInner_items()!=null){

                DenemeGosterAdapter_inner inner_adapter=new DenemeGosterAdapter_inner();
                inner_adapter.setInner_items(out_item.getInner_items());

                holder.dersdogru.setText(out_item.getDers_dogru());
                holder.dersyanlis.setText(out_item.getDers_yanlis());

                holder.rv_inner.setHasFixedSize(true);
                holder.rv_inner.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
                holder.rv_inner.setAdapter(inner_adapter);

                inner_adapter.notifyDataSetChanged();
            }else{
                holder.dersdogru.setText(out_item.getDers_dogru());
                holder.dersyanlis.setText(out_item.getDers_yanlis());

            }

        }

        @Override
        public int getItemCount() {
            return outer_items.size();
        }

        class DenemeGosterViewHolder extends RecyclerView.ViewHolder {


            TextView dersisim, dersdogru, dersyanlis;
            RecyclerView rv_inner;

            public DenemeGosterViewHolder(@NonNull View itemView) {
                super(itemView);

                dersisim=itemView.findViewById(R.id.denemegoster_ders_isim);
                dersdogru=itemView.findViewById(R.id.denemegoster_dersdogru);
                dersyanlis=itemView.findViewById(R.id.denemegoster_dersyanlis);
                rv_inner=itemView.findViewById(R.id.RV_denemegoster_inner);
            }
        }
    }


    private class DenemeGosterAdapter_inner extends RecyclerView.Adapter<DenemeGosterAdapter_inner.DenemeGosterViewHolder_inner>{

        private ArrayList<Item_DenemeGoster_inner> inner_items;

        public void setInner_items(ArrayList<Item_DenemeGoster_inner> inner_items) {
            this.inner_items = inner_items;
        }

        @NonNull
        @Override
        public DenemeGosterViewHolder_inner onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_denemegoster_konuitem_silinebilir
                                                                        , parent, false);

            return new DenemeGosterViewHolder_inner(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DenemeGosterViewHolder_inner holder, int position) {

            Item_DenemeGoster_inner inner_item = inner_items.get(position);

            holder.konu_isim.setText(inner_item.getKonu_isim());
            holder.konu_dogru.setText(inner_item.getKonu_dogru());
            holder.konu_yanlis.setText(inner_item.getKonu_yanlis());
        }

        @Override
        public int getItemCount() {
            return inner_items.size();
        }

        class DenemeGosterViewHolder_inner extends RecyclerView.ViewHolder {

            TextView konu_isim, konu_dogru, konu_yanlis;

            public DenemeGosterViewHolder_inner(@NonNull View itemView) {
                super(itemView);

                konu_isim= itemView.findViewById(R.id.denemegoster_konu_isim);
                konu_dogru= itemView.findViewById(R.id.denemegoster_konu_dogru);
                konu_yanlis= itemView.findViewById(R.id.denemegoster_konu_yanlis);

            }
        }
    }
}
