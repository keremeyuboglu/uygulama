package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
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
import com.example.acer.hayditrkiyeleri.MainActivity;
import com.example.acer.hayditrkiyeleri.R;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FragmentSignupFourth extends Fragment {

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
            ders = TYT_Bilgi.getDersSayisi();

        Log.d(TAG, "onCreate: " + ders);

        mDersler = new String[]{
                "Biyoloji","Kimya", "Fizik",  "Geometri", "Matematik",  "Din","Felsefe", "Coğrafya" , "Tarih", "Türkçe"
        };

        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup4, container, false);
        TextView ders_ismi = view.findViewById(R.id.soru_row);
        ders_ismi.setText(mDersler[ders-1]);


        Ders_Bilgi dersim = TYT_Bilgi.get_dersbilgi(mDersler[ders - 1]);

        String[] array = dersim.getKonu_isimler().toArray(new String[dersim.getKonu_isimler().size()]);
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
                            fragmentTransaction.addToBackStack(null);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Aq", Toast.LENGTH_SHORT).show();
                    if(state == 0){
                        v.findViewById(R.id.signup4konu).setBackgroundColor(Color.parseColor("#E52E00")); // Green
                        state = 1;}
                    else if(state == 1) {
                        v.findViewById(R.id.signup4konu).setBackgroundColor(Color.parseColor("#DB8D00")); // Red  #AC5200
                        state = 2;
                    }
                    else if(state == 2) {
                        v.findViewById(R.id.signup4konu).setBackgroundColor(Color.parseColor("#BFD200"));
                        state = 3;
                    }else{
                        v.findViewById(R.id.signup4konu).setBackgroundColor(Color.parseColor("#04BF00")); // Green
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
            View view = inflater.inflate(R.layout.fragment_signup4_konu, parent, false);
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
