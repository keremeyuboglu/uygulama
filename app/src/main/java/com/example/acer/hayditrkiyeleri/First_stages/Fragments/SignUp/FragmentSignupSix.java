package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.DersBilgileri.AYT_Bilgi;
import com.example.acer.hayditrkiyeleri.DersBilgileri.Ders_Bilgi;
import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.EventTransfer;
import com.example.acer.hayditrkiyeleri.MainActivity;
import com.example.acer.hayditrkiyeleri.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FragmentSignupSix extends Fragment {

    private int globalVariableforEventBus = 1;
    LinkedHashMap<String,String> hashMap;
    String bölüm;
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
    @Subscribe(sticky = true)
    public void AYTKonuIcerigi(EventTransfer.konuIcerigiAYT konuIcerigiAYT){
        if(globalVariableforEventBus == 1){
            bölüm = konuIcerigiAYT.getBolum();
            hashMap = konuIcerigiAYT.getHashMap();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        if(bölüm == "TS"){
            mKonular = resources.getStringArray(R.array.TSkonular);

        }else if(bölüm == "TM"){
            mKonular = resources.getStringArray(R.array.TMkonular);

        }else if(bölüm == "MF"){
            mKonular = resources.getStringArray(R.array.MFkonular);
        }else{
            mKonular = resources.getStringArray(R.array.Dilkonular);
        }
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            ders = bundle.getInt("kalan");
            globalVariableforEventBus = 0;
        }
        else{
            if(bölüm == "TS"){
                ders = 7;
            }else if(bölüm == "TM"){
                ders = 4;
            }else if(bölüm == "MF"){
                ders = 4;
            }else{
                ders = 1;
            }
        }


        /*SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedString = sharedPref.getString("bolum_key","MF");
        Log.d(TAG, savedString);*/

        if(bölüm == "TS"){
            mDersler = new String[]{
                    "Din","Felsefe", "Coğrafya2",  "Tarih2", "Coğrafya1",  "Tarih1","Edebiyat"
            };
        }else if(bölüm == "TM"){
            mDersler = new String[]{
                    "Coğrafya1",  "Tarih1", "Matematik", "Edebiyat"
            };
        }else if(bölüm == "MF"){
            mDersler = new String[]{
                     "Biyoloji",  "Kimya","Fizik", "Matematik"
            };
        }else{
            mDersler = new String[]{
                    "Dil"
            };
        }


        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // View model yok mu :(


        View view = inflater.inflate(R.layout.fragment_signup6, container, false);
        TextView ders_ismi = view.findViewById(R.id.ayt_soru_row);
        ders_ismi.setText(mDersler[ders-1]);


        /*Ders_Bilgi dersim = AYT_Bilgi.get_dersbilgi(mDersler[ders - 1]);

        String[] array = dersim.getKonu_isimler().toArray(new String[dersim.getKonu_isimler().size()]);*/

        startstoppoints(bölüm);
        Log.d("mesaj","startstop" + startPoint + stopPoint);
        array = new String[stopPoint-startPoint+1];
        arr = new int[stopPoint-startPoint+1];
        Log.d("mesaj","startstop" + array.length);

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

        mPrimaryRecyclerView = view.findViewById(R.id.ayt_soru_asil_item);
        mPrimaryRecyclerView.setLayoutManager(layoutManager);
        mPrimaryRecyclerView.setAdapter(adapter);

        Button finishSignup = view.findViewById(R.id.button6);

        finishSignup.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {


                if(ders == 1) {

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finishAffinity();
                }

                else {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    Toast.makeText(getContext(), "Ayrıntılı", Toast.LENGTH_SHORT).show();
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("kalan",ders - 1); // Teker teker dersler :ddd
                    FragmentSignupSix newGamefragment = new FragmentSignupSix();
                    newGamefragment.setArguments(mBundle);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                    fragmentTransaction.commit();
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
            mTextView = itemView.findViewById(R.id.ayt_soru_item);
        }

        // This get called in PrimaryAdapter onBindViewHolder method
        public void bindViews(String genre, int position) {
            mTextView.setText(genre);
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
            //     Log.d(TAG, "onClick: onCreateViewHolder");
            View view = inflater.inflate(R.layout.fragment_signup6_konu, parent, false);
            return new PrimaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PrimaryViewHolder holder, final int position) {
            //     Log.d(TAG, "onClick: onBindViewHolderPrimary");


            String genre = mMovieGenre[position];
            holder.bindViews(genre, position);

        }

        @Override
        public int getItemCount() {
            return mMovieGenre.length;
        }
    }

    public void startstoppoints(String bolum){

        if(bolum == "TS"){
            switch (ders){
                case 7 :
                    startPoint = 0;
                    stopPoint = 11;
                    break;
                case 6 :
                    startPoint = 12;
                    stopPoint = 18;
                    break;
                case 5 :
                    startPoint = 19;
                    stopPoint = 24;
                    break;
                case 4 :
                    startPoint = 25;
                    stopPoint = 33;
                    break;
                case 3 :
                    startPoint = 34;
                    stopPoint = 42;
                    break;
                case 2 :
                    startPoint = 43;
                    stopPoint = 51;
                    break;
                case 1 :
                    startPoint = 52;
                    stopPoint = 58;
                    break;
            }

        }else if(bolum == "TM"){

            switch (ders){
                case 4 :
                    startPoint = 0;
                    stopPoint = 1;
                    break;
                case 3 :
                    startPoint = 2;
                    stopPoint = 9;
                    break;
                case 2 :
                    startPoint = 10;
                    stopPoint = 15;
                    break;
                case 1 :
                    startPoint = 16;
                    stopPoint = 22;
                    break;
            }


        }else if(bolum == "MF"){
            switch (ders){
                case 4 :
                    startPoint = 0;
                    stopPoint = 1;
                    break;
                case 3 :
                    startPoint = 2;
                    stopPoint = 9;
                    break;
                case 2 :
                    startPoint = 10;
                    stopPoint = 15;
                    break;
                case 1 :
                    startPoint = 16;
                    stopPoint = 22;
                    break;
            }

        }else {
            startPoint = 0;
            stopPoint = 8;
        }
    }
}
