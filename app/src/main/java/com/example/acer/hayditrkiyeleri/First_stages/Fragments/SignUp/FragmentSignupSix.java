package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.acer.hayditrkiyeleri.MainActivity;
import com.example.acer.hayditrkiyeleri.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FragmentSignupSix extends Fragment {


    private RecyclerView mPrimaryRecyclerView;
    private String[] mDersler, mKonular;
    public int ders;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            ders = bundle.getInt("kalan");

        }
        else
            ders = AYT_Bilgi.getDersSayisi();


        //

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedString = sharedPref.getString("bolum_key","MF");
        Log.d(TAG, savedString);

        mDersler = new String[]{
                "Biyoloji-2","Kimya-2", "Fizik-2", "Matematik-2"
        };

        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup6, container, false);
        TextView ders_ismi = view.findViewById(R.id.ayt_soru_row);
        ders_ismi.setText(mDersler[ders-1]);


        Ders_Bilgi dersim = AYT_Bilgi.get_dersbilgi(mDersler[ders - 1]);

        String[] array = dersim.getKonu_isimler().toArray(new String[dersim.getKonu_isimler().size()]);
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

    private class PrimaryViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private Integer state = 0;


        public PrimaryViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.ayt_soru_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Aq", Toast.LENGTH_SHORT).show();
                    if(state == 0){
                        v.findViewById(R.id.signup6konu).setBackgroundColor(Color.parseColor("#E52E00")); // Green
                        state = 1;}
                    else if(state == 1) {
                        v.findViewById(R.id.signup6konu).setBackgroundColor(Color.parseColor("#DB8D00")); // Red  #AC5200
                        state = 2;
                    }
                    else if(state == 2) {
                        v.findViewById(R.id.signup6konu).setBackgroundColor(Color.parseColor("#BFD200"));
                        state = 3;
                    }else{
                        v.findViewById(R.id.signup6konu).setBackgroundColor(Color.parseColor("#04BF00")); // Green
                        state = 0;}
                }
            });
        }

        // This get called in PrimaryAdapter onBindViewHolder method
        public void bindViews(String genre, int position) {
            mTextView.setText(genre);
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
}
