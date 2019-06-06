package com.example.acer.hayditrkiyeleri;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_konu;
import com.example.acer.hayditrkiyeleri.Database.Repository;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_inner;
import com.example.acer.hayditrkiyeleri.Util.RVItems.DenemeEkle.Item_DenemeEkle2_outer;
import com.example.acer.hayditrkiyeleri.Util.ViewModels.DenemeEkle2ViewModel;
import com.example.acer.hayditrkiyeleri.Util.MyTask;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FragmentDenemeEkleSecondGeneric extends Fragment {

    private int mExpandedPosition = -1;
//    private int mExpandedPosition2 = -1;
    private RecyclerView mPrimaryRecyclerView;
    private DenemeEkle2ViewModel viewModel;
    private DenemeEntity deneme=null;


    public FragmentDenemeEkleSecondGeneric(DenemeEntity deneme){
        //Burda derslerin doğru yanlış sayısı alınacak


        this.deneme=deneme;
    }

    public FragmentDenemeEkleSecondGeneric(){} //Empty Default constructor

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deneme_ekle2_generic, container, false);

        viewModel= ViewModelProviders.of(this).get(DenemeEkle2ViewModel.class);
        Repository repo=new Repository();
        repo.setDao(((ThisApplication)getActivity().getApplication()).get_dao());
        viewModel.set_repo(repo);

        // Creating the primary recycler view adapter
        PrimaryAdapter adapter = new PrimaryAdapter();
        adapter.setOuter_items(viewModel.get_itemsLive()); //It is not live yet

        LinearLayoutManager layoutManager = new LinearLayoutManager(//
                getActivity(),
                RecyclerView.VERTICAL,
                false
        );

        mPrimaryRecyclerView = view.findViewById(R.id.Srecycler_view);
        mPrimaryRecyclerView.setLayoutManager(layoutManager);
        mPrimaryRecyclerView.setAdapter(adapter);


        // Butona basinca aktivite bitsin
        Button button = view.findViewById(R.id.button_generic_submit);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                MyTask task=new MyTask(()->{

                    final int huge_number=300;

                    final ArrayList<Item_DenemeEkle2_outer> out_items= viewModel.get_itemsLive(); //It is not live yet
                    ArrayList<Deneme_konu> veriler= new ArrayList<>(huge_number);
                    Deneme_konu temp_veri;
                    ArrayList<Item_DenemeEkle2_inner> temp_innerlist;
                    int temp_toplam;
                    int temp_yanlisbos;


                    for(Item_DenemeEkle2_outer out: out_items){

                        temp_innerlist= out.getInnerItems();

                        for(Item_DenemeEkle2_inner inner: temp_innerlist){
                            temp_veri=new Deneme_konu();
                            temp_veri.setDers_isim(out.getDers_isim());

                            temp_veri.setKonu_isim(inner.getKonu_isim());
                            temp_toplam=Integer.parseInt(inner.getKonu_toplam());
                            temp_yanlisbos=Integer.parseInt(inner.getKonu_yanlisbos());

                            temp_veri.setKonu_dogru(temp_toplam);
                            temp_veri.setKonu_yanlis(temp_yanlisbos);

                            veriler.add(temp_veri);
                        }



                    }

                    veriler.trimToSize();
                    deneme.setVeriler_konu(veriler);

                    viewModel.insert_deneme(deneme);
                    Log.i("olley", "deneme gömüldü: "+deneme.deneme_id);
                });

                task.execute();

                getActivity().finish();
            }
        });




        return view;
    }


    private class PrimaryAdapter extends RecyclerView.Adapter<PrimaryAdapter.PrimaryViewHolder> {
        private ArrayList<Item_DenemeEkle2_outer> outer_items;

        public void setOuter_items(ArrayList<Item_DenemeEkle2_outer> outer_items) {
            this.outer_items = outer_items;
        }

        @Override
        public PrimaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            //     Log.d(TAG, "onClick: onCreateViewHolder");
            View view = inflater.inflate(R.layout.fragment_deneme_ekle2_generic_soru_item, parent, false);
            return new PrimaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PrimaryViewHolder holder, final int position) {
            //      Log.d(TAG, "onClick: onBindViewHolderPrimary");

            Item_DenemeEkle2_outer item= outer_items.get(position);
            holder.mDersisim.setText(item.getDers_isim());
            holder.tv_dersdogru.setText(item.getDers_dogru());
            holder.tv_dersyanlis.setText(item.getDers_yanlisbos());
            holder.mSecondaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            holder.mSecondaryRecyclerView.setHasFixedSize(true);

            SecondaryAdapter sAdapter=new SecondaryAdapter(item, position, item.getInnerItems(), holder.tv_dersdogru, holder.tv_dersyanlis);

            holder.mSecondaryRecyclerView.setAdapter(sAdapter);

            holder.tv_dersdogru.setText(item.getDers_dogru());
            holder.tv_dersyanlis.setText(item.getDers_yanlisbos());

            //Expandable logic
            final boolean isExpanded = (position == mExpandedPosition);
            holder.mSecondaryRecyclerView.setVisibility(isExpanded?View.VISIBLE:View.GONE);
            holder.mSecondaryTitle.setVisibility(isExpanded?View.VISIBLE:View.GONE);
            holder.itemView.setActivated(isExpanded);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mExpandedPosition = isExpanded ? -1:position;
//                    mExpandedPosition2 = isExpanded ? position:-1;
                    notifyDataSetChanged();
                }
            });
            //Expandable logic end

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
               //Log.d(TAG, "onClick: PrimaryViewHolder");
                mDersisim = itemView.findViewById(R.id.ders_isim);
                mSecondaryRecyclerView = itemView.findViewById(R.id.soru_asil_item); //Inner elements
                mSecondaryTitle = itemView.findViewById(R.id.soru_item_linear); //This is stable
                tv_dersdogru= itemView.findViewById(R.id.ders_dogru);
                tv_dersyanlis= itemView.findViewById(R.id.ders_yanlisbos);


            }
        }
    }



    private class SecondaryAdapter extends RecyclerView.Adapter<SecondaryAdapter.SecondaryViewHolder> {
        private final Item_DenemeEkle2_outer out;
        private final int out_index;
        private ArrayList<Item_DenemeEkle2_inner> inner_items;
        private TextView tv_toplam, tv_yanlisbos;
        private CardView mIpucu;
        private CardView Exp;


        public SecondaryAdapter(Item_DenemeEkle2_outer out, int out_index, ArrayList<Item_DenemeEkle2_inner> inner_items, TextView tv_dogru, TextView tv_yanlisbos) {
            this.out = out;
            this.out_index = out_index;
            this.inner_items = inner_items;
            this.tv_toplam = tv_dogru;
            this.tv_yanlisbos = tv_yanlisbos;
        }

        @Override
        public SecondaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.fragment_deneme_ekle2_generic_soru_alt_item, parent, false);
            return new SecondaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SecondaryViewHolder holder,final int position) {
            //    Log.d(TAG, "onClick: onBindViewHolder");
            Item_DenemeEkle2_inner item= inner_items.get(position);
            holder.tv_konuisim.setText(item.getKonu_isim());
            holder.edit_toplamsoru.setText(item.getKonu_toplam());
            holder.edit_yanlisbos.setText(item.getKonu_yanlisbos());


            holder.Exp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Initialize a new instance of LayoutInflater service
                    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                    // Inflate the custom layout/view
                    View customView = inflater.inflate(R.layout.fragment_deneme_ekle2_generic_tip,null);



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
                }
            });

        }

        @Override
        public int getItemCount() {
            return inner_items.size();
        }

        private class SecondaryViewHolder extends RecyclerView.ViewHolder {

            TextView tv_konuisim;
            EditText edit_toplamsoru;
            EditText edit_yanlisbos;
            CardView mIpucu;
            CardView Exp;


            public SecondaryViewHolder(View view) {
                super(view);
                tv_konuisim = itemView.findViewById(R.id.soru_item);
                edit_toplamsoru =  itemView.findViewById(R.id.toplam_soru);
                edit_yanlisbos = itemView.findViewById(R.id.yanlis_bos);
                Exp =  itemView.findViewById(R.id.soru_tip);


                //Anında değişme şeysi eklencek

                edit_toplamsoru.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence yeni, int i, int i1, int i2) {

                        Log.i("degisim toplamsoru", yeni.toString());


                        Item_DenemeEkle2_inner inner= inner_items.get(getAdapterPosition());
                        final int toplam_int= Integer.parseInt(inner.getKonu_toplam());
                        int simdiki_deger;


                        Log.i("degisim", "toplamint: "+toplam_int);

                        simdiki_deger= 0;

                        if(!yeni.toString().equals("")){
                            simdiki_deger=Integer.parseInt(yeni.toString());
                        }

                        Log.i("degisim", "simdiki_deger: "+simdiki_deger);

                        inner.setKonu_toplam(String.valueOf(simdiki_deger));
                        int fark= simdiki_deger-toplam_int;

                        Log.i("degisim", "fark: "+fark);

                        int toplam=Integer.parseInt(tv_toplam.getText().toString());

                        Log.i("degisim", "toplam: "+toplam);

                        toplam=toplam+fark;

                        Log.i("degisim", "dogru tobeset: "+toplam);

                        String new_toplam=String.valueOf(toplam);
                        tv_toplam.setText(new_toplam);

                        out.setDers_dogru(new_toplam);

                        Log.i("degisim", "finitoooo\n");
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                edit_yanlisbos.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                        Log.i("degisim yanlisbos", charSequence.toString());

                        Item_DenemeEkle2_inner inner= inner_items.get(getAdapterPosition());
                        final int yanlisbos_int= Integer.parseInt(inner.getKonu_yanlisbos());


                        Log.i("degisim", "yanlisbos_int "+ yanlisbos_int);
                        int simdiki_deger=0;

                        if(!charSequence.toString().equals(""))
                            simdiki_deger=Integer.parseInt(charSequence.toString());

                        inner.setKonu_yanlisbos(charSequence.toString());
                        Log.i("degisim", "simdiki_deger: "+simdiki_deger);

                        inner.setKonu_yanlisbos(String.valueOf(simdiki_deger));
                        int fark= simdiki_deger-yanlisbos_int;

                        Log.i("degisim", "fark: "+fark);

                        int yanlis= Integer.parseInt(tv_yanlisbos.getText().toString());

                        Log.i("degisim", "yanlis: "+yanlis);

                        String yanlisstr=String.valueOf(yanlis+fark);

                        tv_yanlisbos.setText(yanlisstr);
                        out.setDers_yanlisbos(yanlisstr);


                        Log.i("degisim", "finitoooo\n");
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });



            }


        }
    }


}
