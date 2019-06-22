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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.DersBilgileri.Ders_Bilgi;
import com.example.acer.hayditrkiyeleri.DersBilgileri.TYT_Bilgi;
import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.EventTransfer;
import com.example.acer.hayditrkiyeleri.MainActivity;
import com.example.acer.hayditrkiyeleri.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FragmentSignupFourth extends Fragment {

    private int globalVariableforEventBus,checkfonks;
    HashMap<String,String> hashMap;
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
        //buraya global bir int koyup sürekşi veri akışını keseceğim
        hashMap = konuIcerigi.getHashMap();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        mKonular = resources.getStringArray(R.array.turkce);
        checkfonks = 1;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            ders = bundle.getInt("kalan");
        }
        else{
            ders = 10;
        }


        mDersler = new String[]{
                "Din","Felsefe", "Coğrafya",  "Tarih", "Biyoloji",  "Kimya","Fizik", "Geometri" , "Matematik", "Türkçe"
        };

        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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


                // Burayı da fragmentDialog yapacağım
                if(ders == 1) {

                    // Initialize a new instance of LayoutInflater service
                    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                    // Inflate the custom layout/view
                    View customView = inflater.inflate(R.layout.fragment_signup4_popup, null);


                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(v.getContext());
                    mBuilder.setView(customView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    // Get a reference for the custom view close button
                    ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                    // Set a click listener for the popup window close button
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Dismiss the popup window
                            dialog.dismiss();
                        }
                    });

                    CardView card_view = (CardView) customView.findViewById(R.id.ayt_n); // creating a CardView and assigning a value.

                    card_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finishAffinity();
                        }
                    });

                    CardView card_view2 = (CardView) customView.findViewById(R.id.ayt_y); // creating a CardView and assigning a value.

                    card_view2.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            Toast.makeText(getContext(), "Ayrıntılı", Toast.LENGTH_SHORT).show();
                            FragmentSignupFifth newGamefragment = new FragmentSignupFifth();
                            fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                            fragmentTransaction.commit();
                            dialog.dismiss();
                        }
                    });
                }

                else {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    Toast.makeText(getContext(), "Ayrıntılı", Toast.LENGTH_SHORT).show();
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("kalan",ders - 1); // Teker teker dersler :ddd
                    FragmentSignupFourth newGamefragment = new FragmentSignupFourth();
                    newGamefragment.setArguments(mBundle);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                    fragmentTransaction.commit();
                }
            }


        });

        return view;
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
