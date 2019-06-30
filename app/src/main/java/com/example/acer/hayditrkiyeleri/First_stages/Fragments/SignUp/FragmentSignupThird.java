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
import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.EventTransfer;
import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.FragmentDenemeEkleFirst;
import com.example.acer.hayditrkiyeleri.FragmentMenuDenemeGoster;
import com.example.acer.hayditrkiyeleri.R;
import com.example.acer.hayditrkiyeleri.SiralamaSample;
import com.example.acer.hayditrkiyeleri.ThisApplication;
import com.example.acer.hayditrkiyeleri.TytDersler.KonuTakip;
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

public class FragmentSignupThird extends Fragment {

    private ArrayList<DenemeEntity> denemeler = new ArrayList<>();
    public static HashMap<String, EsasVeriEntity> esas_verimap = new HashMap<>();
    boolean nodeneme = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_signup3, container, false);


        SignUpThirdViewModel viewModel = ViewModelProviders.of(getActivity()).get(SignUpThirdViewModel.class); //Initilazing viewModel
        Repository myRepo = new Repository();
        myRepo.setDao(((ThisApplication) getActivity().getApplication()).get_dao());
        viewModel.setMyRepo(myRepo);
        //Burası karışık gözüküyor ama olan tek şey viewModel initialize ediliyor.


        Button finishSignup = v.findViewById(R.id.son);

        final CardView denemeEkle = v.findViewById(R.id.denemeEklecard);
        denemeEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                /*Intent myIntent = new Intent(getActivity(), ActivityDenemeEkle.class); // Deneme Ekleme Ekrani Aktivitesi yap
                myIntent.putExtra("key", 5); //Optional parameters
                getActivity().startActivity(myIntent);*/

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                Log.i("hmmmmmm", "thirdden giden: "+denemeler);

                fragmentTransaction.replace(R.id.signupContainer, new FragmentDenemeEkleFirst(viewModel.getItems(), denemeler));
                fragmentTransaction.addToBackStack("seko");
                fragmentTransaction.commit();
            }
        });

        setRetainInstance(true);


        finishSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MyTask task = new MyTask(() -> {

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

                    //Userinfo dan gelecek

                    //Bolum ve sinif bir yerlerde kullanılmıyor o yüzden şimdilik böyle kalsın
                    TYTOgrenci tytOgrenci = new TYTOgrenci(FragmentSignupFirst.userinfo.getIstercih()==0, 87.87);



                    tytOgrenci.setTurkce(NetHesabi.dersneti(viewModel.get_esasveri("Türkçe").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setMatematik(NetHesabi.dersneti(viewModel.get_esasveri("Matematik").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setFizik(NetHesabi.dersneti(viewModel.get_esasveri("Fizik").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setKimya(NetHesabi.dersneti(viewModel.get_esasveri("Kimya").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setBiyoloji(NetHesabi.dersneti(viewModel.get_esasveri("Biyoloji").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setCografya(NetHesabi.dersneti(viewModel.get_esasveri("Coğrafya").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setTarih(NetHesabi.dersneti(viewModel.get_esasveri("Tarih").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setFelsefe(NetHesabi.dersneti(viewModel.get_esasveri("Felsefe").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setDin(NetHesabi.dersneti(viewModel.get_esasveri("Din").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    Log.d("mesaj", "Puanı bu" + tytOgrenci.getTurkce());


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

                    // Alt konu başlıklarını çek
                    String[] arrayTurkce = resources.getStringArray(R.array.turkce);
                    LinkedHashMap<String,String> hashMapDersler= new LinkedHashMap<String, String>();

                    for (int i = 0; i < arrayTurkce.length; i++) {
                        hashMapDersler.put(arrayTurkce[i],"Kırmızı");
                    }
                    KonuTakip konuTakip = new KonuTakip(tytOgrenci);
                    hashMapDersler = konuTakip.turkcetakip(hashMapDersler);
                    hashMapDersler = konuTakip.matematiktakip(hashMapDersler);
                    hashMapDersler = konuTakip.fiziktakip(hashMapDersler);
                    hashMapDersler = konuTakip.kimyatakip(hashMapDersler);
                    hashMapDersler = konuTakip.biyolojitakip(hashMapDersler);
                    hashMapDersler = konuTakip.tarihtakip(hashMapDersler);
                    hashMapDersler = konuTakip.cografyatakip(hashMapDersler);
                    hashMapDersler = konuTakip.felsefetakip(hashMapDersler);
                    hashMapDersler = konuTakip.dintakip(hashMapDersler);
                    // Tek tek renklere bağla

                    // Puan hesapla
                    tytOgrenci.tytPuan();

                    // Obpsiz puan ve sıra
                    Log.d("mesaj", "Puanı bu" + tytOgrenci.getObpsizPuan());
                    double obpsizSira = tytOgrenci.siralamaHesabı(tytOgrenci.getObpsizPuan(),obpsizSıralamaArray);
                    Log.d("mesaj","bu + " + obpsizSira);
                    tytOgrenci.setObpsizSiralama(obpsizSira);
                    // obpli yap
                    Log.d("mesaj", "Puanı bu" + tytOgrenci.getObpliPuan());
                    double obpliSira = tytOgrenci.obpsiralamaHesabı(tytOgrenci.getObpliPuan(),obpliSiralamaArray);
                    Log.d("mesaj","bu + " + obpliSira);
                    tytOgrenci.setobpliSiralama(obpliSira);



                    EventBus.getDefault().postSticky(new EventTransfer.konuIcerigi(hashMapDersler));
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.signupContainer, new FragmentSignupFourth());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                });

                task.execute();


            }


        });

        //Recyclerview start
        AtomicInteger mReyclerViewSize = new AtomicInteger();


        RecyclerView recyclerView = v.findViewById(R.id.RV_deneme_kayit);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        DenemelerAdapter adapter = new DenemelerAdapter();
        adapter.setItems(new ArrayList<>(0)); //In order to prevent errors
        recyclerView.setAdapter(adapter);

        int height = recyclerView.getLayoutParams().height;

        if(nodeneme)
            recyclerView.setVisibility(View.GONE);

            viewModel.getItems().observe(getActivity(), denemeEntities -> {
            ArrayList<Item_Denemelerim> items = RVItemGenerator.pump_Item_denemelerim(denemeEntities);
            adapter.setItems(items);
            mReyclerViewSize.set(items.size());
            adapter.notifyDataSetChanged();
            int mReyclerViewSizeInt = mReyclerViewSize.intValue();
            nodeneme = false;

            if(mReyclerViewSizeInt == 1){
                recyclerView.getLayoutParams().height = height;
            }
            else if(mReyclerViewSizeInt == 2){
                recyclerView.getLayoutParams().height = height * 2;
            }
            else
                recyclerView.getLayoutParams().height = height * 3;
            Log.d(TAG, "onCreateView: " + height + mReyclerViewSizeInt);
        });

        //Recyclerview end


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
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_signup3_deneme_item, parent, false);

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

                btn = itemView.findViewById(R.id.btn_deneme_signup3);


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


