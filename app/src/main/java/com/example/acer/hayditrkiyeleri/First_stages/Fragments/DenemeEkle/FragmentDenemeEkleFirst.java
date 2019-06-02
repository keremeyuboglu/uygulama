package com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.FragmentDenemeEkleSecondGeneric;
import com.example.acer.hayditrkiyeleri.R;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_outer;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;


import android.app.AlertDialog;


public class FragmentDenemeEkleFirst extends Fragment {
    @Nullable

    boolean goBack = true;
    private PopupWindow mPopupWindow;
    private ScrollView mScrollView;
    int mPopupHeight;
    int mPopupWidth;
    private String[] mDersler;
    private RecyclerView mPrimaryRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recycler

        mDersler = new String[]{
                "Türkçe", "Matematik", "Fen", "Sosyal"
        };

        //
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_deneme_ekle1_ygs, container, false);

        PrimaryAdapter madapter = new PrimaryAdapter(mDersler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false
        );

        mPrimaryRecyclerView = (RecyclerView) view.findViewById(R.id.ygs_reycle);
        mPrimaryRecyclerView.setLayoutManager(layoutManager);
        mPrimaryRecyclerView.setAdapter(madapter);

        final ScrollView mScrollView = view.findViewById(R.id.scroll);
        Button changeFragment = view.findViewById(R.id.buttonEkle1);

        // Spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerEkle1);

        final List<String> categories = new ArrayList<String>();

        categories.add("Diğer");
        categories.add("Karekök");

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,categories);     /// Sqlle değişecek yerler

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
    /*    NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.turkD);
        Log.d(TAG, "onCreateView: numberPicker.getDisplayedValues()"); */


        ;

        container.post(new Runnable(){
            public void run(){
                mPopupHeight = container.getHeight();
                mPopupWidth = container.getWidth();
            }
        });



        changeFragment.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                if(goBack){

                    // Initialize a new instance of LayoutInflater service
                    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                    // Inflate the custom layout/view
                    View customView = inflater.inflate(R.layout.fragment_deneme_ekle1_popup1,null);



                    AlertDialog.Builder mBuilder  = new AlertDialog.Builder(v.getContext());
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

                    CardView card_view = (CardView) customView.findViewById(R.id.generic_ekle2); // creating a CardView and assigning a value.

                    card_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "İstemirosam Tükürdüm", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            dialog.dismiss();
                        }
                    });

                    CardView card_view2 = (CardView) customView.findViewById(R.id.nongeneric_ekle2); // creating a CardView and assigning a value.

                    card_view2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            Toast.makeText(getContext(), "Yayınevi cooperation inbound", Toast.LENGTH_SHORT).show();
                            FragmentDenemeEkleSecondGeneric newGamefragment = new FragmentDenemeEkleSecondGeneric();
                            fragmentTransaction.replace(R.id.deneme_container, newGamefragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            dialog.dismiss();
                        }
                    });

                }



                else{
                    String[] deneme = new String[] {
                            "Deneme #2222222222222451",
                            "Deneme #2",
                            "Deneme #3",
                            "Deneme #4",
                            "Deneme #4",
                            "Deneme #4"
                            ,
                            "Deneme #4",
                            "Deneme #4",
                            "Deneme #4",
                            "Deneme #4",
                            "Deneme #4",
                            "Deneme #4",
                            "Deneme #4",
                            "Deneme #4",
                            "Deneme #4",


                    };


                    // Initialize a new instance of LayoutInflater service
                    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                    // Inflate the custom layout/view
                    View customView = inflater.inflate(R.layout.fragment_deneme_ekle1_popup2,null);


                    // Getting object reference to listview of main.xml
                    ListView listView = (ListView) customView.findViewById(R.id.listview);

                    // Instantiating array adapter to populate the listView
                    // The layout android.R.layout.simple_list_item_single_choice creates radio button for each listview item
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice,deneme);

                    listView.setAdapter(adapter);

                    AlertDialog.Builder mBuilder  = new AlertDialog.Builder(v.getContext());
                    mBuilder.setView(customView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                    // Set a click listener for the popup window close button
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Dismiss the popup window
                            dialog.dismiss();
                        }
                    });

                    Button button = (Button) customView.findViewById(R.id.popup2button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            Toast.makeText(getContext(), "Yayınevi cooperation inbound", Toast.LENGTH_SHORT).show();
                            FragmentDenemeEkleSecondKarekok newGamefragment = new FragmentDenemeEkleSecondKarekok();
                            fragmentTransaction.replace(R.id.deneme_container, newGamefragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            dialog.dismiss();
                        }
                    });

                    CardView card_view = (CardView) customView.findViewById(R.id.generic_ekle2); // creating a CardView and assigning a value.
                }
            }
        });


        // Spinner item selection

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //    String selected = parentView.getItemAtPosition(position).toString();

                switch (position){
                    case 0:
                        goBack = true;  // Bu diğer denemeler
                        break;
                    case 1:
                        goBack = false; // Bu anlaşmalı olanlar
                        break;
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });





        return view;
    }

    // Recycler

    private class PrimaryViewHolder extends RecyclerView.ViewHolder {
        private TextView mDers;
        private NumberPicker mDogru;
        private NumberPicker mYanlis;

        public PrimaryViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "onClick: PrimaryViewHolder");
            mDers = (TextView) itemView.findViewById(R.id.ders_adi);
            mDogru = (NumberPicker) itemView.findViewById(R.id.dersD);
            mYanlis = (NumberPicker) itemView.findViewById(R.id.dersY);
        }

        // This get called in PrimaryAdapter onBindViewHolder method
        public void bindViews(String genre, int position) {
            mDers.setText(genre);
            Log.d(TAG, "onClick: bindViewsPrimary");
            mDogru.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    mYanlis.setMaxValue(mDogru.getMaxValue() - newVal);
                    // mDogru.getValue(); Bunla o anki valueyı alıyoruz artık nerede storelanır bilmiyorum
                    // mYanlis.getValue();
                }
            });

        }
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
            View view = inflater.inflate(R.layout.fragment_deneme_ekle1_item, parent, false);
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






    /*

    private class PrimaryAdapter extends RecyclerView.Adapter<PrimaryAdapter.PrimaryViewHolder> {
        private ArrayList<Item_DenemeEkle2_outer> outer_items;

        public void setOuter_items(ArrayList<Item_DenemeEkle2_outer> outer_items) {
            this.outer_items = outer_items;
        }

        @Override
        public PrimaryAdapter.PrimaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            //     Log.d(TAG, "onClick: onCreateViewHolder");
            View view = inflater.inflate(R.layout.fragment_deneme_ekle2_generic_soru_item, parent, false);
            return new PrimaryAdapter.PrimaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PrimaryAdapter.PrimaryViewHolder holder, final int position) {
            //      Log.d(TAG, "onClick: onBindViewHolderPrimary");

            Item_DenemeEkle2_outer item= outer_items.get(position);
            holder.mDersisim.setText(item.getDers_isim());
            holder.tv_dersdogru.setText(item.getDers_dogru());
            holder.tv_dersyanlis.setText(item.getDers_yanlisbos());
            holder.mSecondaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            holder.mSecondaryRecyclerView.setHasFixedSize(true);


        }

        @Override
        public int getItemCount() {
            return outer_items.size();
        }


        private class PrimaryViewHolder extends RecyclerView.ViewHolder {
            private TextView mDersisim;
            private RecyclerView mSecondaryRecyclerView;
            private LinearLayout mSecondaryTitle;
            private TextView tv_dersdogru, tv_dersyanlis;

            public PrimaryViewHolder(View itemView) {
                super(itemView);
//            Log.d(TAG, "onClick: PrimaryViewHolder");
                mDersisim = itemView.findViewById(R.id.ders_isim);
                mSecondaryRecyclerView = itemView.findViewById(R.id.soru_asil_item); //Inner elements
                mSecondaryTitle = itemView.findViewById(R.id.soru_item_linear); //This is stable
                tv_dersdogru= itemView.findViewById(R.id.ders_dogru);
                tv_dersyanlis= itemView.findViewById(R.id.ders_yanlisbos);

            }
        }
    }
    */

}
