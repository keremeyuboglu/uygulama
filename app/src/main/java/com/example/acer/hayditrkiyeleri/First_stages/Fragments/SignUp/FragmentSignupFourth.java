package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.MainActivity;
import com.example.acer.hayditrkiyeleri.R;

public class FragmentSignupFourth extends Fragment {

    private RecyclerView mPrimaryRecyclerView;
    private String[] mMoviesGenre, mActionMovies;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMoviesGenre = new String[]{
                "Matematik", "Fen", "Sosyal"
        };

        mActionMovies = new String[]{"Sözcükte Anlam","Cümlede Anlam","Paragraf",
                "Yazim Kurallari ve Yarralar"
        };

        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup4, container, false);



        // Creating the primary recycler view adapter
        PrimaryAdapter adapter = new PrimaryAdapter(mMoviesGenre);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false
        );

        mPrimaryRecyclerView = view.findViewById(R.id.srecycler_view);
        mPrimaryRecyclerView.setLayoutManager(layoutManager);
        mPrimaryRecyclerView.setAdapter(adapter);

        Button finishSignup = view.findViewById(R.id.button4);

        finishSignup.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finishAffinity();
            }


        });

        return view;
    }

    private class PrimaryViewHolder extends RecyclerView.ViewHolder {
        private TextView mPrimaryMovieGenre;
        private RecyclerView mSecondaryRecyclerView;


        public PrimaryViewHolder(View itemView) {
            super(itemView);
            //  Log.d(TAG, "onClick: PrimaryViewHolder");
            mPrimaryMovieGenre = itemView.findViewById(R.id.soru_row);
            mSecondaryRecyclerView = itemView.findViewById(R.id.soru_asil_item);
        }

        // This get called in PrimaryAdapter onBindViewHolder method
        public void bindViews(String genre, int position) {
            mPrimaryMovieGenre.setText(genre);
            //    Log.d(TAG, "onClick: bindViewsPrimary");
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    getActivity(),
                    RecyclerView.VERTICAL,
                    false
            );

            mSecondaryRecyclerView.setLayoutManager(linearLayoutManager);
            mSecondaryRecyclerView.setAdapter(getSecondaryAdapter(position));
        }
    /*    public void setBoolean(boolean x){
            isExpanded = x;
        }*/
    }

    private class PrimaryAdapter extends RecyclerView.Adapter<PrimaryViewHolder> {
        private String[] mMovieGenre;

        public PrimaryAdapter(String[] moviesGenre) {
            //   Log.d(TAG, "onClick: PrimaryAdapter");
            mMovieGenre = moviesGenre;

        }

        @NonNull
        @Override
        public PrimaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            //     Log.d(TAG, "onClick: onCreateViewHolder");
            View view = inflater.inflate(R.layout.fragment_signup4_ders, parent, false);
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

    private class SecondaryViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private Integer state = 0;

        public SecondaryViewHolder(View view) {
            super(view);
            mTextView = itemView.findViewById(R.id.soru_item);
            view.setOnClickListener(new View.OnClickListener() {
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

        public void bindView(String name) {
            mTextView.setText(name);

        }
    }

    private class SecondaryAdapter extends RecyclerView.Adapter<SecondaryViewHolder> {
        private String[] mMovies;

        public SecondaryAdapter(String[] movies) {
            mMovies = movies;
        }

        @Override
        public SecondaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.fragment_signup4_konu, parent, false);
            return new SecondaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SecondaryViewHolder holder, int position) {
            //    Log.d(TAG, "onClick: onBindViewHolder");
            String name = mMovies[position];
            holder.bindView(name);

        }

        @Override
        public int getItemCount() {
            return mMovies.length;
        }
    }

    private SecondaryAdapter getSecondaryAdapter(int position) {

        SecondaryAdapter adapter;

        return new SecondaryAdapter(mActionMovies); //

    }
}
