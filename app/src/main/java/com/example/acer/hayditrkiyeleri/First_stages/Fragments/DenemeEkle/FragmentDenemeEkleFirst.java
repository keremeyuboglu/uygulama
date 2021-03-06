package com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_ders;
import com.example.acer.hayditrkiyeleri.Database.Repository;
import com.example.acer.hayditrkiyeleri.FragmentDenemeEkleSecondGeneric;
import com.example.acer.hayditrkiyeleri.R;
import com.example.acer.hayditrkiyeleri.ThisApplication;
import com.example.acer.hayditrkiyeleri.Util.MyTask;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle1;
import com.example.acer.hayditrkiyeleri.Util.ViewModels.DenemeEkle1ViewModel;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


import android.app.AlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class FragmentDenemeEkleFirst extends Fragment {
    @Nullable

    boolean goBack = true;
    private PopupWindow mPopupWindow;
    private ScrollView mScrollView;
    int mPopupHeight;
    int mPopupWidth;

    DenemeEntity new_deneme;
    private Repository myRepo=new Repository();

    // Register to eventBus
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    // Unregister when fragment stops
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // Take data from popUpFragment for Diger using object
    @Subscribe
    public void onPopUp(EventTransfer.popUp popUp){
        int popUpNum = popUp.getNum();
        setPopUp(popUpNum);
    }

    public void setPopUp(int popUpNum){
        if(popUpNum == 1){
            new_deneme.setAyrintili(true); //Ayrıntılı
            //Ders doğru yanlış işlemleri yapılması lazım

            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            Toast.makeText(getContext(), "Ayrıntılı", Toast.LENGTH_SHORT).show();
            FragmentDenemeEkleSecondGeneric newGamefragment = new FragmentDenemeEkleSecondGeneric(new_deneme);
            fragmentTransaction.replace(R.id.deneme_container, newGamefragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else {
            new_deneme.setAyrintili(false); //Ayrıntısız
            MyTask task=new MyTask(()->{
                myRepo.insert_deneme(new_deneme); //Denemeyi veritabanına gömüyoruz

            });

            task.execute(); //It will insert item in another thread
            Toast.makeText(getContext(), "Ayrıntılı İstemirosam Tükürdüm", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }

    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_deneme_ekle1_ygs, container, false);

        final ScrollView mScrollView = view.findViewById(R.id.scroll);
        Button changeFragment = view.findViewById(R.id.buttonEkle1);

        myRepo.setDao(((ThisApplication)getActivity().getApplication()).get_dao());
        DenemeEkle1ViewModel viewModel= ViewModelProviders.of(getActivity()).get(DenemeEkle1ViewModel.class);


        //Creating rv
        RecyclerView recyclerView= view.findViewById(R.id.RV_denemeEkle1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        DenemeEkleAdapter rv_adapter=new DenemeEkleAdapter();
        rv_adapter.setItems(viewModel.get_rvitems());

        recyclerView.setAdapter(rv_adapter);
        rv_adapter.notifyDataSetChanged();
        //rv_end

        // Spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerEkle1);

        final List<String> categories = new ArrayList<String>();

        categories.add("Diğer");
        categories.add("Karekök");

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,categories);     /// Sqlle değişecek yerler

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        container.post(new Runnable(){
            public void run(){
                mPopupHeight = container.getHeight();
                mPopupWidth = container.getWidth();
            }
        });




        changeFragment.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(goBack){

                    //basically passing rv_items and denemeid to pump_item and it will pump a new denemeEntity
                    new_deneme= pump_deneme(viewModel.get_rvitems(), ((ThisApplication)getActivity().getApplication()).get_numberofdeneme());

                    // create popUpFragment for diger
                    FragmentDenemePopUp fragmentDenemePopUp = new FragmentDenemePopUp();
                    fragmentDenemePopUp.show(getFragmentManager(),"dialog");
                }

                else{

                    // create popUpFragment for Karekok
                    FragmentKarekokPopUp fragmentKarekokPopUp = new FragmentKarekokPopUp();
                    fragmentKarekokPopUp.show(getFragmentManager(),"karekok");
                }
            }
        });

        // Spinner item selection

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //    String selected = parentView.getItemAtPosition(position).toString();

                switch (position){
                    case 0:
                        goBack = true;  // Bu diğer denemeler
                        break;
                    case 1:
                        goBack = false; // Bu anlaşmalı olanlar
                        break;
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });





        return view;
    }



    private class DenemeEkleAdapter extends RecyclerView.Adapter<DenemeEkleAdapter.DenemeEkleViewHolder>{

        private ArrayList<Item_DenemeEkle1> items;

        public void setItems(ArrayList<Item_DenemeEkle1> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public DenemeEkleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_deneme_ekle1_item, parent, false);

            return new DenemeEkleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DenemeEkleViewHolder holder, int position) {

            Item_DenemeEkle1 item=items.get(position);

            holder.ders_isim.setText(item.getDers_isim());
            holder.dogru.setValue(item.getDogru());
            holder.yanlis.setValue(item.getYanlis());

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class DenemeEkleViewHolder extends RecyclerView.ViewHolder{

            TextView ders_isim;

            NumberPicker dogru, yanlis;


            public DenemeEkleViewHolder(@NonNull View itemView) {
                super(itemView);


                ders_isim=itemView.findViewById(R.id.deneme_ekle_dersisim);

                dogru=itemView.findViewById(R.id.numpick_dersdogru);
                yanlis=itemView.findViewById(R.id.numpick_dersyanlis);
                
                dogru.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        // UI
                        yanlis.setMaxValue(dogru.getMaxValue() - newVal);
                        //
                        Item_DenemeEkle1 item= items.get(getAdapterPosition());

                        item.setDogru(newVal);
                    }
                });


                yanlis.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        Item_DenemeEkle1 item= items.get(getAdapterPosition());

                        item.setYanlis(newVal);
                    }
                });
            }
        }
    }



    private DenemeEntity pump_deneme(ArrayList<Item_DenemeEkle1> items, int denemeid){

        ArrayList<Deneme_ders> veriler_ders=new ArrayList<>();
        Deneme_ders temp_ders;

        for(Item_DenemeEkle1 item: items){
            temp_ders=new Deneme_ders();
            temp_ders.setDers_isim(item.getDers_isim());
            temp_ders.setDers_dogru(item.getDogru());
            temp_ders.setDers_yanlis(item.getYanlis());

            veriler_ders.add(temp_ders);
        }

        veriler_ders.trimToSize();

        DenemeEntity new_deneme=new DenemeEntity();
        new_deneme.setVeriler_ders(veriler_ders);
        new_deneme.setDeneme_id(denemeid);

        return new_deneme;
    }




































/*
    private DenemeEntity pump_deneme(int deneme_id){
        //Su an recyclerview olmadığı için edittextler manuel olarak alındı.
        //bu entity eğer adam konuları girmeyecekse direkt gömülecek yoksa resetlenecek
        //Niye böyle olduğunu anlatırım anlamadıysanız


        DenemeEntity new_deneme= new DenemeEntity();
        new_deneme.setDeneme_id(deneme_id);

        ArrayList<Deneme_ders> veriler_ders=new ArrayList<>();

        Deneme_ders ilk=new Deneme_ders();
        ilk.setDers_isim("Türkçe");
        ilk.setDers_dogru(Integer.parseInt(turkD.getText().toString()));
        ilk.setDers_yanlis(Integer.parseInt(turkY.getText().toString()));

        veriler_ders.add(ilk);

        Deneme_ders uc=new Deneme_ders();
        uc.setDers_isim("Sosyal");
        uc.setDers_dogru(Integer.parseInt(sosD.getText().toString()));
        uc.setDers_yanlis(Integer.parseInt(sosY.getText().toString()));

        veriler_ders.add(uc);

        Deneme_ders iki=new Deneme_ders();
        iki.setDers_isim("Matematik");
        iki.setDers_dogru(Integer.parseInt(matD.getText().toString()));
        iki.setDers_yanlis(Integer.parseInt(matY.getText().toString()));

        veriler_ders.add(iki);


        Deneme_ders dort=new Deneme_ders();
        dort.setDers_isim("Fen");
        dort.setDers_dogru(Integer.parseInt(fenD.getText().toString()));
        dort.setDers_yanlis(Integer.parseInt(fenY.getText().toString()));

        veriler_ders.add(dort);



        new_deneme.setVeriler_ders(veriler_ders);


        return new_deneme;

    }*/
}
