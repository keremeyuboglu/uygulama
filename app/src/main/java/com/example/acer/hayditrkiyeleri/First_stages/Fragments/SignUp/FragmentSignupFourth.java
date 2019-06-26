package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.Database.Entities.AltBaslikEntity;
import com.example.acer.hayditrkiyeleri.Database.Repository;
import com.example.acer.hayditrkiyeleri.DersBilgileri.Ders_Bilgi;
import com.example.acer.hayditrkiyeleri.DersBilgileri.TYT_Bilgi;
import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.EventTransfer;
import com.example.acer.hayditrkiyeleri.MainActivity;
import com.example.acer.hayditrkiyeleri.R;
import com.example.acer.hayditrkiyeleri.ThisApplication;
import com.example.acer.hayditrkiyeleri.Util.MyTask;
import com.example.acer.hayditrkiyeleri.Util.ViewModels.SignUpFourthViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FragmentSignupFourth extends Fragment {

    // Pop up yapacağım
    // fragment_signup_popup4 xmlini değiştirdim o yüzden hata veriyor
    // Eventbus koyupo yemi frgament oluşturmam gerekecek
    private int globalVariableforEventBus = 1;
    LinkedHashMap<String,String> hashMap;
    Resources resources;
    private RecyclerView mPrimaryRecyclerView;
    private String[] mDersler, mKonular,array;
    public int ders;
    private int[] arr;
    private int startPoint,stopPoint;


    //Eventbusa giriş
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    //Eventbustan çıkış
    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);

    }


    //hashmap transferi
    @Subscribe (sticky = true)
    public void holdingKonuIcerigi(EventTransfer.konuIcerigi konuIcerigi){
        if(globalVariableforEventBus == 1)
            hashMap = konuIcerigi.getHashMap();
    }

    // finishAffinityde bir sıkıntı var
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe
    public void popUp(EventTransfer.AYTpopUp AYTpopUp){
        if(AYTpopUp.getNum() == 0){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finishAffinity();
        }
        else{
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            Toast.makeText(getContext(), "Ayrıntılı", Toast.LENGTH_SHORT).show();
            FragmentSignupFifth newGamefragment = new FragmentSignupFifth();
            fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
            fragmentTransaction.commit();
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        mKonular = resources.getStringArray(R.array.turkce);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            ders = bundle.getInt("kalan");
            globalVariableforEventBus = 0;
        }
        else
            ders = 10;


        mDersler = new String[]{
                "Din","Felsefe", "Coğrafya",  "Tarih", "Biyoloji",  "Kimya","Fizik", "Geometri" , "Matematik", "Türkçe"
        };

        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        SignUpFourthViewModel viewModel= ViewModelProviders.of(this).get(SignUpFourthViewModel.class);
        Repository myRepo = new Repository();
        myRepo.setDao(((ThisApplication) getActivity().getApplication()).get_dao());
        viewModel.setMyRepo(myRepo);
        //Burası karışık gözüküyor ama olan tek şey viewModel initialize ediliyor.


        View view = inflater.inflate(R.layout.fragment_signup4, container, false);
        TextView ders_ismi = view.findViewById(R.id.soru_row);
        ders_ismi.setText(mDersler[ders-1]);

        // Hangi dersin arrayde hangi aralıkta olduğu [konuisimleri.xml]
        switch (ders){
            case 10 :
                startPoint = 0;
                stopPoint = 18;
                break;
            case 9 :
                startPoint = 19;
                stopPoint = 32;
                break;
            case 8 :
                startPoint = 33;
                stopPoint = 39;
                break;
            case 7 :
                startPoint = 40;
                stopPoint = 47;
                break;
            case 6 :
                startPoint = 48;
                stopPoint = 58;
                break;
            case 5 :
                startPoint = 59;
                stopPoint = 72;
                break;
            case 4 :
                startPoint = 73;
                stopPoint = 82;
                break;
            case 3 :
                startPoint = 83;
                stopPoint = 91;
                break;
            case 2 :
                startPoint = 91;
                stopPoint = 105;
                break;
            case 1 :
                startPoint = 106;
                stopPoint = 111;
                break;
        }


        // iki array yaratıyorum biri String biri int olacak şekilde
        // String konu isimlerini tutacak
        // Int olan da renk kodlarını (Kırmızıysa 0, Yeşilse 3 falan
        array = new String[stopPoint-startPoint+1];
        arr = new int[stopPoint-startPoint+1];
        for(int i = 0; i < array.length ; i++ ){

            // Tek tek konuları aldık, hashmapten renk kodlarını da alıp arr arrayine koyduk
            array[i] = mKonular[i+startPoint];
            String temp = hashMap.get(mKonular[i+startPoint]);
            if(temp == "Kırmızı")
                arr[i] = 0;
            else if(temp == "Turuncu")
                arr[i] = 1;
            else if(temp == "Sarı")
                arr[i] = 2;
            else
                arr[i] = 3;
        }


        // Creating the primary recycler view adapter
        PrimaryAdapter adapter = new PrimaryAdapter(array);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false
        );

        mPrimaryRecyclerView = view.findViewById(R.id.soru_asil_item);
        mPrimaryRecyclerView.setLayoutManager(layoutManager);
        mPrimaryRecyclerView.setAdapter(adapter);

        Button finishSignup = view.findViewById(R.id.button4);

        finishSignup.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {

                if(ders == 1) {
                    FragmentAYTPopUp fragmentAYTPopUp = new FragmentAYTPopUp();
                    fragmentAYTPopUp.show(getFragmentManager(),"ayt");}

                else{

                    MyTask task=new MyTask(()->{



                        int altKonuSayisi = 5;
                        int temp;
                        for(int i = 0; i < array.length ; i++ ){
                            // Şu an array[i]'nin bir işlevi yok

                            List<AltBaslikEntity> basliklar= viewModel.get_altBasliksByKonuisim(array[i]);

                            altKonuSayisi=basliklar.size();
                            temp = altKonuFonksiyon(array[i],arr[i],altKonuSayisi);

                            if(temp>altKonuSayisi){

                                Log.i("hata", "alt başlık sayısından daha fazla tik atmak gerekiyor");
                            }else{

                                for(int a=0; a<temp; a++){

                                    basliklar.get(a).setBiliniyor(true);
                                }

                                viewModel.insert_altbasliks(basliklar);

                            }
                        }

                    });

                    task.execute();


                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    Toast.makeText(getContext(), "Ayrıntılı", Toast.LENGTH_SHORT).show();
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("kalan",ders - 1); // Teker teker dersler :ddd
                    int altKonuSayisi = 5;
                    int temp;
                    for(int i = 0; i < array.length ; i++ ){
                        // Şu an array[i]'nin bir işlevi yok
                        temp = altKonuFonksiyon(array[i],arr[i],altKonuSayisi);
                    }
                    FragmentSignupFourth newGamefragment = new FragmentSignupFourth();
                    newGamefragment.setArguments(mBundle);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                    fragmentTransaction.commit();
                    fragmentTransaction.addToBackStack(null);
                }
            }
        });

        return view;
    }

    private int altKonuFonksiyon(String s, int i, int altKonuSayisi) {

        if(i == 0){
            return 0;

        }else if (i == 1){
            int temp = altKonuSayisi/2;
            if(temp >2){
                temp--;
            }

            return temp;

        }else if (i == 2){

            int temp = altKonuSayisi/2;
            if(temp >2){
                temp++;
            }
            return temp;

        }else if (i == 3){
            return altKonuSayisi;
        }
        return 0;
    }

    private class PrimaryViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private Integer state = 0;


        public PrimaryViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.soru_item);
        }

        // This get called in PrimaryAdapter onBindViewHolder method
        public void bindViews(String genre, int position) {

            // Texti al setle
            mTextView.setText(genre);

            // Arrda koyduğum renk değerlerine göre background ata
            if(arr[position] == 0)
                mTextView.setBackgroundColor(Color.parseColor("#E52E00"));
            else if(arr[position] == 1)
                mTextView.setBackgroundColor(Color.parseColor("#DB8D00"));
            else if(arr[position] == 2)
                mTextView.setBackgroundColor(Color.parseColor("#BFD200"));
            else
                mTextView.setBackgroundColor(Color.parseColor("#04BF00"));


            // textviewa her tıklandığında rengi değiştir ve arr değerini
            // değiştir bu sayede yukarı aşağı yaptığımızda listedeki rnekler değişmeyecek
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(arr[position] == 0){
                        mTextView.setBackgroundColor(Color.parseColor("#DB8D00")); // Green
                        arr[position] = 1;}
                    else if(arr[position] == 1) {
                        mTextView.setBackgroundColor(Color.parseColor("#BFD200")); // Green
                        arr[position] = 2;
                    }
                    else if(arr[position] == 2) {
                        mTextView.setBackgroundColor(Color.parseColor("#04BF00")); // Red  #AC5200
                        arr[position] = 3;
                    }else{
                        mTextView.setBackgroundColor(Color.parseColor("#E52E00"));
                        arr[position] = 0;}
                }
            });
        }
    }

    private class PrimaryAdapter extends RecyclerView.Adapter<PrimaryViewHolder> {
        private String[] mMovieGenre;

        public PrimaryAdapter(String[] moviesGenre) {
            mMovieGenre = moviesGenre;
        }

        @NonNull
        @Override
        public PrimaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.fragment_signup4_konu, parent, false);
            return new PrimaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PrimaryViewHolder holder, final int position) {
            String genre = mMovieGenre[position];
            holder.bindViews(genre, position);
        }

        @Override
        public int getItemCount() {
            return mMovieGenre.length;
        }
    }
}




