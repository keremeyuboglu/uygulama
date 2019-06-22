package com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.R;

import static android.content.ContentValues.TAG;

public class FragmentDenemeEkleSecondKarekok extends Fragment {


    private RecyclerView mPrimaryRecyclerView;
    private String[] mMoviesGenre, mActionMovies;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMoviesGenre = new String[]{
                "Türkçe", "Matematik", "Fen", "Sosyal"
        };

        mActionMovies = new String[] {
                "1","2","3","4","5","6","7","8","21"
        };

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deneme_ekle2_karekok, container, false);

        Button button = view.findViewById(R.id.button5);

        // Creating the primary recycler view adapter
        PrimaryAdapter adapter = new PrimaryAdapter(mMoviesGenre);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false
        );

        mPrimaryRecyclerView = (RecyclerView) view.findViewById(R.id.deneme3r);
        mPrimaryRecyclerView.setLayoutManager(layoutManager);
        mPrimaryRecyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

    private class PrimaryViewHolder extends RecyclerView.ViewHolder {
        private TextView mPrimaryMovieGenre;
        private RecyclerView mSecondaryRecyclerView;

        public PrimaryViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "onClick: PrimaryViewHolder");
            mPrimaryMovieGenre = (TextView) itemView.findViewById(R.id.recyclerviewparenttext);
            mSecondaryRecyclerView = (RecyclerView) itemView.findViewById(R.id.deneme2r);
        }

        // This get called in PrimaryAdapter onBindViewHolder method
        public void bindViews(String genre, int position) {
            mPrimaryMovieGenre.setText(genre);
            Log.d(TAG, "onClick: bindViewsPrimary");

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    getActivity(),
                    RecyclerView.HORIZONTAL,
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
            Log.d(TAG, "onClick: PrimaryAdapter");
            mMovieGenre = moviesGenre;

        }

        @Override
        public PrimaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            Log.d(TAG, "onClick: onCreateViewHolder");
            View view = inflater.inflate(R.layout.fragment_deneme_ekle2_karekok_item, parent, false);
            return new PrimaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PrimaryViewHolder holder, final int position) {
            Log.d(TAG, "onClick: onBindViewHolderPrimary");


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
        Integer state = 0; // 0 green,1 gray, 2 red

        public SecondaryViewHolder(View view) {
            super(view);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Aq", Toast.LENGTH_SHORT).show();
                    if(state == 0){
                        v.findViewById(R.id.damn).setBackgroundColor(Color.rgb(211, 211, 211)); // Gray
                        state = 1;}
                    else if(state == 1) {
                        v.findViewById(R.id.damn).setBackgroundColor(Color.rgb(255, 0, 0)); // Red
                        state = 2;
                    }else{
                        v.findViewById(R.id.damn).setBackgroundColor(Color.parseColor("#ff99cc00")); // Green
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
            View view = inflater.inflate(R.layout.fragment_deneme_ekle2_karekok_alt_item, parent, false);
            return new SecondaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SecondaryViewHolder holder, int position) {
            Log.d(TAG, "onClick: onBindViewHolder");
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