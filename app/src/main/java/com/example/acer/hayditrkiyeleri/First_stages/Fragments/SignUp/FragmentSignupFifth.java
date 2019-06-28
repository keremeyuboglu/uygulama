package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_ders;
import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_konu;
import com.example.acer.hayditrkiyeleri.Database.Entities.EsasVeriEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.Stat;
import com.example.acer.hayditrkiyeleri.Database.Repository;
import com.example.acer.hayditrkiyeleri.First_stages.ActivitySignup;
import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.EventTransfer;
import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.FragmentDenemeEkleFirst;
import com.example.acer.hayditrkiyeleri.FragmentMenuDenemeGoster;
import com.example.acer.hayditrkiyeleri.R;
import com.example.acer.hayditrkiyeleri.SiralamaSample;
import com.example.acer.hayditrkiyeleri.ThisApplication;
import com.example.acer.hayditrkiyeleri.TytDersler.AYTOgrenciDil;
import com.example.acer.hayditrkiyeleri.TytDersler.AYTOgrenciMF;
import com.example.acer.hayditrkiyeleri.TytDersler.AYTOgrenciTM;
import com.example.acer.hayditrkiyeleri.TytDersler.AYTOgrenciTS;
import com.example.acer.hayditrkiyeleri.TytDersler.KonuTakip;
import com.example.acer.hayditrkiyeleri.TytDersler.KonuTakipDil;
import com.example.acer.hayditrkiyeleri.TytDersler.KonuTakipMF;
import com.example.acer.hayditrkiyeleri.TytDersler.KonuTakipTM;
import com.example.acer.hayditrkiyeleri.TytDersler.KonuTakipTS;
import com.example.acer.hayditrkiyeleri.TytDersler.NetHesabi;
import com.example.acer.hayditrkiyeleri.TytDersler.TYTOgrenci;
import com.example.acer.hayditrkiyeleri.Util.MyTask;
import com.example.acer.hayditrkiyeleri.Util.RVItemGenerator;
import com.example.acer.hayditrkiyeleri.Util.RVItems.Denemelerim.Item_Denemelerim;
import com.example.acer.hayditrkiyeleri.Util.ViewModels.SignUpThirdViewModel;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static android.content.ContentValues.TAG;

public class FragmentSignupFifth extends Fragment {
    private ArrayList<DenemeEntity> denemeler = new ArrayList<>();
    public static HashMap<String, EsasVeriEntity> esas_verimap = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_signup5, container, false);


        SignUpThirdViewModel viewModel = ViewModelProviders.of(this).get(SignUpThirdViewModel.class); //Initilazing viewModel
        Repository myRepo = new Repository();
        myRepo.setDao(((ThisApplication) getActivity().getApplication()).get_dao());
        viewModel.setMyRepo(myRepo);
        //Burası karışık gözüküyor ama olan tek şey viewModel initialize ediliyor.


        Button finishSignup = v.findViewById(R.id.ayt_son);

        final CardView denemeEkle = v.findViewById(R.id.ayt_denemeEklecard);
        denemeEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                /*Intent myIntent = new Intent(getActivity(), ActivityDenemeEkle.class); // Deneme Ekleme Ekrani Aktivitesi yap
                myIntent.putExtra("key", 5); //Optional parameters
                getActivity().startActivity(myIntent);*/

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.signupContainer, new FragmentDenemeEkleFirst(viewModel.getItems(), denemeler));
                fragmentTransaction.addToBackStack("seko");
                fragmentTransaction.commit();
            }
        });

        setRetainInstance(true);


        finishSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MyTask task = new MyTask(() -> {

                    //Burası ayt ye göre değiştirilmeli
                    viewModel.insert_items(denemeler);

                    EsasVeriEntity temp_esasveri;
                    ArrayList<Deneme_ders> temp_veriler_ders;
                    ArrayList<Deneme_konu> temp_veriler_konu;

                    for (DenemeEntity deneme : denemeler) {

                        temp_veriler_ders = deneme.getVeriler_ders();

                        for (Deneme_ders ders : temp_veriler_ders) {

                            temp_esasveri = esas_verimap.get(ders.getDers_isim());

                            if (temp_esasveri == null) {

                                temp_esasveri = new EsasVeriEntity(ders.getDers_isim(), true, false);
                                esas_verimap.put(ders.getDers_isim(), temp_esasveri);

                            }

                            temp_esasveri.add_stat(new Stat(ders.getDers_dogru() + ders.getDers_yanlis(), ders.getDers_yanlis()));
                            viewModel.insert_esasVeri(temp_esasveri);
                        }


                        temp_veriler_konu = deneme.getVeriler_konu();

                        if (temp_veriler_konu != null) {
                            for (Deneme_konu konu : temp_veriler_konu) {

                                temp_esasveri = esas_verimap.get(konu.getKonu_isim());

                                if (temp_esasveri == null) {

                                    temp_esasveri = new EsasVeriEntity(konu.getKonu_isim(), false, false);
                                    esas_verimap.put(konu.getKonu_isim(), temp_esasveri);

                                }

                                temp_esasveri.add_stat(new Stat(konu.getKonu_dogru() + konu.getKonu_yanlis(), konu.getKonu_yanlis()));
                                viewModel.insert_esasVeri(temp_esasveri);

                            }
                        }
                    }
                });

                task.execute();
                // TYT Öğrencinin obpsiz puanı ve obpsi gelmeli
                AYTOgrenciTS aytOgrenciTS = new AYTOgrenciTS(300.0,87.87,true);
                AYTOgrenciTM aytOgrenciTM = new AYTOgrenciTM(300.0,87.87,true);
                AYTOgrenciMF aytOgrenciMF = new AYTOgrenciMF(300.0,87.87,true);
                AYTOgrenciDil aytOgrenciDil = new AYTOgrenciDil(300.0,87.87,true);

                Resources resources = getResources();

                //Sıralama obpsiz
                List<SiralamaSample> obpsizSıralamaArray = new ArrayList<>();
                InputStream is = resources.openRawResource(R.raw.obpsizsiralama);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, Charset.forName("UTF-8"))
                );
                String line = "";
                try {
                    while ((line = reader.readLine()) != null){
                        String[] tokens = line.split(",");

                        SiralamaSample sample = new SiralamaSample();
                        sample.setPuan(Integer.parseInt(tokens[0]));
                        sample.setTytsira(Double.parseDouble(tokens[1]));
                        sample.setSozelsira(Double.parseDouble(tokens[2]));
                        sample.setSayisalsira(Double.parseDouble(tokens[3]));
                        sample.setEsitagirliksira(Double.parseDouble(tokens[4]));
                        sample.setDilsira(Double.parseDouble(tokens[5]));

                        obpsizSıralamaArray.add(sample);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                //Sıralama obpsiz end

                //Sıralama obpli
                List<SiralamaSample> obpliSiralamaArray = new ArrayList<>();
                InputStream isobp = resources.openRawResource(R.raw.obplisiralam);
                BufferedReader readerobp = new BufferedReader(
                        new InputStreamReader(isobp, Charset.forName("UTF-8"))
                );
                String lineobp = "";
                try {
                    while ((lineobp = readerobp.readLine()) != null){
                        String[] tokensobp = lineobp.split(",");

                        SiralamaSample sample = new SiralamaSample();
                        sample.setPuan(Integer.parseInt(tokensobp[0]));
                        sample.setTytsira(Double.parseDouble(tokensobp[1]));
                        sample.setSozelsira(Double.parseDouble(tokensobp[2]));
                        sample.setSayisalsira(Double.parseDouble(tokensobp[3]));
                        sample.setEsitagirliksira(Double.parseDouble(tokensobp[4]));
                        sample.setDilsira(Double.parseDouble(tokensobp[5]));

                        obpliSiralamaArray.add(sample);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                //Sıralama obpli end
                String bölüm = "TS"; // Kayıt olurken seçtiği bölüm olmalı
                LinkedHashMap<String,String> hashMapDersler= new LinkedHashMap<String, String>();
                if(bölüm == "TS"){
                    // Netleri setle
                    aytOgrenciTS.setCografya1(11.5);
                    aytOgrenciTS.setCografya2(11.5);
                    aytOgrenciTS.setEdebiyat(11.5);
                    aytOgrenciTS.setDin(11.5);
                    aytOgrenciTS.setFelsefe(11.5);
                    aytOgrenciTS.setTarih1(11.5);
                    aytOgrenciTS.setTarih2(11.5);
                    aytOgrenciTS.aytPuan();

                    // KonuTakip classında da netleri setle
                    // Bu sayede hashmap renkleri belli olsun
                    KonuTakipTS konuTakip = new KonuTakipTS(aytOgrenciTS);
                    // Arrayi çek ve hashMapte kırmızıya eşitle
                    String[] arrayKonular = resources.getStringArray(R.array.TSkonular);
                    for (int i = 0; i < arrayKonular.length; i++) {
                        hashMapDersler.put(arrayKonular[i],"Kırmızı");
                    }
                    hashMapDersler = konuTakip.cografya2Takip(hashMapDersler);
                    hashMapDersler = konuTakip.cografya1Takip(hashMapDersler);
                    hashMapDersler = konuTakip.edebiyatTakip(hashMapDersler);
                    hashMapDersler = konuTakip.dinTakip(hashMapDersler);
                    hashMapDersler = konuTakip.felsefeTakip(hashMapDersler);
                    hashMapDersler = konuTakip.tarih1Takip(hashMapDersler);
                    hashMapDersler = konuTakip.tarih2Takip(hashMapDersler);


                    Log.d("mesaj", "Puanı bu" + aytOgrenciTS.getObpsizPuan());
                    double obpsizSira = aytOgrenciTS.siralamaHesabı(aytOgrenciTS.getObpsizPuan(),obpsizSıralamaArray);
                    Log.d("mesaj","bu + " + obpsizSira);
                    aytOgrenciTS.setObpsizSiralama(obpsizSira);
                    // obpli yap
                    Log.d("mesaj", "Puanı bu" + aytOgrenciTS.getObpliPuan());
                    double obpliSira = aytOgrenciTS.obpsiralamaHesabı(aytOgrenciTS.getObpliPuan(),obpliSiralamaArray);
                    Log.d("mesaj","bu + " + obpliSira);
                    aytOgrenciTS.setObpliSiralama(obpliSira);

                } else if(bölüm == "TM"){
                    aytOgrenciTM.setCografya1(11.5);
                    aytOgrenciTM.setEdebiyat(11.5);
                    aytOgrenciTM.setMatematik(11.5);
                    aytOgrenciTM.setTarih1(11.5);
                    aytOgrenciTM.aytPuan();

                    KonuTakipTM konuTakip = new KonuTakipTM(aytOgrenciTM);
                    String[] arrayKonular = resources.getStringArray(R.array.TMkonular);
                    for (int i = 0; i < arrayKonular.length; i++) {
                        hashMapDersler.put(arrayKonular[i],"Kırmızı");
                    }
                    hashMapDersler = konuTakip.matematikTakip(hashMapDersler);
                    hashMapDersler = konuTakip.cografya1Takip(hashMapDersler);
                    hashMapDersler = konuTakip.edebiyatTakip(hashMapDersler);
                    hashMapDersler = konuTakip.tarih1Takip(hashMapDersler);

                    Log.d("mesaj", "Puanı bu" + aytOgrenciTM.getObpsizPuan());
                    double obpsizSira = aytOgrenciTM.siralamaHesabı(aytOgrenciTM.getObpsizPuan(),obpsizSıralamaArray);
                    Log.d("mesaj","bu + " + obpsizSira);
                    aytOgrenciTM.setObpsizSiralama(obpsizSira);
                    // obpli yap
                    Log.d("mesaj", "Puanı bu" + aytOgrenciTM.getObpliPuan());
                    double obpliSira = aytOgrenciTM.obpsiralamaHesabı(aytOgrenciTM.getObpliPuan(),obpliSiralamaArray);
                    Log.d("mesaj","bu + " + obpliSira);
                    aytOgrenciTM.setObpliSiralama(obpliSira);

                }else if(bölüm == "MF"){
                    aytOgrenciMF.setBiyoloji(11.5);
                    aytOgrenciMF.setFizik(11.5);
                    aytOgrenciMF.setKimya(11.5);
                    aytOgrenciMF.setMatematik(11.5);
                    aytOgrenciMF.aytPuan();

                    KonuTakipMF konuTakip = new KonuTakipMF(aytOgrenciMF);
                    String[] arrayKonular = resources.getStringArray(R.array.MFkonular);
                    for (int i = 0; i < arrayKonular.length; i++) {
                        hashMapDersler.put(arrayKonular[i],"Kırmızı");
                    }
                    hashMapDersler = konuTakip.matematikTakip(hashMapDersler);
                    hashMapDersler = konuTakip.fizikTakip(hashMapDersler);
                    hashMapDersler = konuTakip.kimyaTakip(hashMapDersler);
                    hashMapDersler = konuTakip.biyolojiTakip(hashMapDersler);

                    Log.d("mesaj", "Puanı bu" + aytOgrenciMF.getObpsizPuan());
                    double obpsizSira = aytOgrenciMF.siralamaHesabı(aytOgrenciMF.getObpsizPuan(),obpsizSıralamaArray);
                    Log.d("mesaj","bu + " + obpsizSira);
                    aytOgrenciMF.setObpsizSiralama(obpsizSira);
                    // obpli yap
                    Log.d("mesaj", "Puanı bu" + aytOgrenciMF.getObpliPuan());
                    double obpliSira = aytOgrenciMF.obpsiralamaHesabı(aytOgrenciMF.getObpliPuan(),obpliSiralamaArray);
                    Log.d("mesaj","bu + " + obpliSira);
                    aytOgrenciMF.setObpliSiralama(obpliSira);

                }else {
                    aytOgrenciDil.setDil(11.5);
                    aytOgrenciDil.aytPuan();

                    KonuTakipDil konuTakip = new KonuTakipDil(aytOgrenciDil);
                    String[] arrayKonular = resources.getStringArray(R.array.Dilkonular);
                    for (int i = 0; i < arrayKonular.length; i++) {
                        hashMapDersler.put(arrayKonular[i],"Kırmızı");
                    }
                    hashMapDersler = konuTakip.dilTakip(hashMapDersler);

                    Log.d("mesaj", "Puanı bu" + aytOgrenciDil.getObpsizPuan());
                    double obpsizSira = aytOgrenciDil.siralamaHesabı(aytOgrenciDil.getObpsizPuan(),obpsizSıralamaArray);
                    Log.d("mesaj","bu + " + obpsizSira);
                    aytOgrenciDil.setObpsizSiralama(obpsizSira);
                    // obpli yap
                    Log.d("mesaj", "Puanı bu" + aytOgrenciDil.getObpliPuan());
                    double obpliSira = aytOgrenciDil.obpsiralamaHesabı(aytOgrenciDil.getObpliPuan(),obpliSiralamaArray);
                    Log.d("mesaj","bu + " + obpliSira);
                    aytOgrenciDil.setObpliSiralama(obpliSira);

                }

                EventBus.getDefault().postSticky(new EventTransfer.konuIcerigiAYT(hashMapDersler,bölüm));
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.signupContainer, new FragmentSignupSix());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }


        });

        AtomicInteger tane = new AtomicInteger();
        RecyclerView recyclerView = v.findViewById(R.id.ayt_RV_deneme_kayit);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        DenemelerAdapter adapter = new DenemelerAdapter();
        adapter.setItems(new ArrayList<>(0)); //In order to prevent errors
        recyclerView.setAdapter(adapter);

        viewModel.getItems().observe(getActivity(), denemeEntities -> {
            ArrayList<Item_Denemelerim> items = RVItemGenerator.pump_Item_denemelerim(denemeEntities);
            adapter.setItems(items);
            tane.set(items.size());
            adapter.notifyDataSetChanged();

            int scroll = tane.intValue();

            if(scroll == 0){
                recyclerView.setVisibility(View.GONE);
            }
            else if(scroll == 1){
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.getLayoutParams().height = 200;
            }
            else if(scroll == 2){
                recyclerView.getLayoutParams().height = 400;
            }
            else{
                recyclerView.getLayoutParams().height = 600;
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Toast.makeText(getActivity(), "Note deleted id=" + adapter.get_denemeid(position), Toast.LENGTH_SHORT).show();

                denemeler.remove(position);
                viewModel.getItems().setValue(denemeler);
            }
        }).attachToRecyclerView(recyclerView);


        return v;
    }


    //FragmentMenuDenemelerim deki aynı adapter
    private class DenemelerAdapter extends RecyclerView.Adapter<DenemelerAdapter.DenemelerViewHolder> {

        private ArrayList<Item_Denemelerim> items;

        public void setItems(ArrayList<Item_Denemelerim> items) {
            this.items = items;
        }

        public int get_denemeid(int position) {
            return items.get(position).getDeneme_id();
        }

        public ArrayList<Item_Denemelerim> getItems() {
            return items;
        }

        @NonNull
        @Override
        public DenemelerAdapter.DenemelerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_signup5_deneme_item, parent, false);

            return new DenemelerAdapter.DenemelerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DenemelerAdapter.DenemelerViewHolder holder, int position) {

            Item_Denemelerim item = items.get(position);

            holder.btn.setText(item.getDeneme_isim());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class DenemelerViewHolder extends RecyclerView.ViewHolder {

            Button btn;

            public DenemelerViewHolder(@NonNull View itemView) {
                super(itemView);

                btn = itemView.findViewById(R.id.btn_deneme_signup5);


                btn.setOnClickListener((View view) -> {

                    Item_Denemelerim this_item = items.get(getAdapterPosition());
                    int denemeid = this_item.getDeneme_id();

                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    FragmentMenuDenemeGoster denemeGoster = new FragmentMenuDenemeGoster(denemeler.get(getAdapterPosition()));
                    fragmentTransaction.replace(R.id.signupContainer, denemeGoster);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                });
            }
        }
    }
}
