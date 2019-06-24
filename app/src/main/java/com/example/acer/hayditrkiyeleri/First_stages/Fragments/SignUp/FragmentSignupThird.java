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
import com.example.acer.hayditrkiyeleri.ThisApplication;
import com.example.acer.hayditrkiyeleri.TytDersler.KonuTakip;
import com.example.acer.hayditrkiyeleri.TytDersler.NetHesabi;
import com.example.acer.hayditrkiyeleri.TytDersler.TYTOgrenci;
import com.example.acer.hayditrkiyeleri.Util.MyTask;
import com.example.acer.hayditrkiyeleri.Util.RVItemGenerator;
import com.example.acer.hayditrkiyeleri.Util.RVItems.Denemelerim.Item_Denemelerim;
import com.example.acer.hayditrkiyeleri.Util.ViewModels.SignUpThirdViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FragmentSignupThird extends Fragment {

    private ArrayList<DenemeEntity> denemeler = new ArrayList<>();
    public static HashMap<String, EsasVeriEntity> esas_verimap = new HashMap<>();

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
                    TYTOgrenci tytOgrenci = new TYTOgrenci("", FragmentSignupFirst.userinfo.getIstercih()==0, 87.87);



                    tytOgrenci.setTurkce(NetHesabi.dersneti(viewModel.get_esasveri("Türkçe").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setMatematik(NetHesabi.dersneti(viewModel.get_esasveri("Matematik").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setFizik(NetHesabi.dersneti(viewModel.get_esasveri("Fizik").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setKimya(NetHesabi.dersneti(viewModel.get_esasveri("Kimya").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setBiyoloji(NetHesabi.dersneti(viewModel.get_esasveri("Biyoloji").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setCografya(NetHesabi.dersneti(viewModel.get_esasveri("Coğrafya").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setTarih(NetHesabi.dersneti(viewModel.get_esasveri("Tarih").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setFelsefe(NetHesabi.dersneti(viewModel.get_esasveri("Felsefe").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));
                    tytOgrenci.setDin(NetHesabi.dersneti(viewModel.get_esasveri("Din").getStatlar().size(), viewModel.get_esasveri("Türkçe").getStatlar()));

                    Resources resources = getResources();
                    String[] arrayTurkce = resources.getStringArray(R.array.turkce);
                    LinkedHashMap<String,String> hashMapDersler= new LinkedHashMap<String, String>();

                    for (int i = 0; i < arrayTurkce.length; i++) {
                        hashMapDersler.put(arrayTurkce[i],"Kırmızı");
                    }


                    KonuTakip konuTakip = new KonuTakip(tytOgrenci);
                    tytOgrenci.setPuan(tytOgrenci.tytPuan());
                    hashMapDersler = konuTakip.turkcetakip(hashMapDersler);
                    hashMapDersler = konuTakip.matematiktakip(hashMapDersler);
                    hashMapDersler = konuTakip.fiziktakip(hashMapDersler);
                    hashMapDersler = konuTakip.kimyatakip(hashMapDersler);
                    hashMapDersler = konuTakip.biyolojitakip(hashMapDersler);
                    hashMapDersler = konuTakip.tarihtakip(hashMapDersler);
                    hashMapDersler = konuTakip.cografyatakip(hashMapDersler);
                    hashMapDersler = konuTakip.felsefetakip(hashMapDersler);
                    hashMapDersler = konuTakip.dintakip(hashMapDersler);


                    EventBus.getDefault().postSticky(new EventTransfer.konuIcerigi(hashMapDersler));
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.signupContainer, new FragmentSignupFourth());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                });

                task.execute();


            }


        });


        RecyclerView recyclerView = v.findViewById(R.id.RV_deneme_kayit);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        DenemelerAdapter adapter = new DenemelerAdapter();
        adapter.setItems(new ArrayList<>(0)); //In order to prevent errors
        recyclerView.setAdapter(adapter);

        viewModel.getItems().observe(getActivity(), denemeEntities -> {
            ArrayList<Item_Denemelerim> items = RVItemGenerator.pump_Item_denemelerim(denemeEntities);
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
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


