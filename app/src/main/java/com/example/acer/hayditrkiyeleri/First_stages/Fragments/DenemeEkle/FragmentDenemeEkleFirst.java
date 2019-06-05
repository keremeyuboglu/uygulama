package com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.Database.Entities.DenemeEntity;
import com.example.acer.hayditrkiyeleri.Database.Entities.Deneme_ders;
import com.example.acer.hayditrkiyeleri.Database.Repository;
import com.example.acer.hayditrkiyeleri.FragmentDenemeEkleSecondGeneric;
import com.example.acer.hayditrkiyeleri.R;
import com.example.acer.hayditrkiyeleri.ThisApplication;
import com.example.acer.hayditrkiyeleri.Util.MyTask;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


import android.app.AlertDialog;


public class FragmentDenemeEkleFirst extends Fragment {
    @Nullable

    boolean goBack = true;
    private PopupWindow mPopupWindow;
    private ScrollView mScrollView;
    int mPopupHeight;
    int mPopupWidth;
    EditText turkY, turkD, matY, matD, sosY, sosD, fenY, fenD;
    DenemeEntity new_deneme;
    private Repository myRepo=new Repository();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_deneme_ekle1, container, false);

        final ScrollView mScrollView = view.findViewById(R.id.scroll);
        Button changeFragment = view.findViewById(R.id.buttonEkle1);

        myRepo.setDao(((ThisApplication)getActivity().getApplication()).get_dao());

        turkY= view.findViewById(R.id.turkY);
        turkD= view.findViewById(R.id.turkD);
        matY= view.findViewById(R.id.matY);
        matD= view.findViewById(R.id.matD);
        sosY= view.findViewById(R.id.sosyalY);
        sosD= view.findViewById(R.id.sosyalD);
        fenY= view.findViewById(R.id.fenY);
        fenD= view.findViewById(R.id.fenD);





        // Spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerEkle1);

        final List<String> categories = new ArrayList<String>();

        categories.add("Diğer");
        categories.add("Karekök");

        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,categories);     /// Sqlle değişecek yerler

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        container.post(new Runnable(){
            public void run(){
                mPopupHeight = container.getHeight();
                mPopupWidth = container.getWidth();
            }
        });



        changeFragment.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(goBack){

                    new_deneme= pump_deneme(((ThisApplication)getActivity().getApplication()).get_numberofdeneme());

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
                            new_deneme.setAyrintili(false); //Ayrıntısız
                            MyTask task=new MyTask(()->{
                                myRepo.insert_deneme(new_deneme); //Denemeyi veritabanına gömüyoruz

                            });

                            task.execute(); //It will insert item in another thread
                            Toast.makeText(getContext(), "Ayrıntılı İstemirosam Tükürdüm", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            dialog.dismiss();
                        }
                    });

                    CardView card_view2 = (CardView) customView.findViewById(R.id.nongeneric_ekle2); // creating a CardView and assigning a value.

                    card_view2.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {


                            new_deneme.setAyrintili(true); //Ayrıntılı
                            //Ders doğru yanlış işlemleri yapılması lazım

                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            Toast.makeText(getContext(), "Ayrıntılı", Toast.LENGTH_SHORT).show();
                            FragmentDenemeEkleSecondGeneric newGamefragment = new FragmentDenemeEkleSecondGeneric(new_deneme);
                            fragmentTransaction.replace(R.id.deneme_container, newGamefragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            dialog.dismiss();
                        }
                    });

                }



                else{

                    //Ne bu?
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


    private DenemeEntity pump_deneme(int deneme_id){
        //Su an recyclerview olmadığı için edittextler manuel olarak alındı.
        //bu entity eğer adam konuları girmeyecekse direkt gömülecek yoksa resetlenecek
        //Niye böyle olduğunu anlatırım anlamadıysanız


        DenemeEntity new_deneme= new DenemeEntity();
        new_deneme.setDeneme_id(deneme_id);

        ArrayList<Deneme_ders> veriler_ders=new ArrayList<>();

        Deneme_ders ilk=new Deneme_ders();
        ilk.setDers_isim("Türkçe");
        ilk.setDers_dogru(Integer.parseInt(turkD.getText().toString()));
        ilk.setDers_yanlis(Integer.parseInt(turkY.getText().toString()));

        veriler_ders.add(ilk);

        Deneme_ders uc=new Deneme_ders();
        uc.setDers_isim("Sosyal");
        uc.setDers_dogru(Integer.parseInt(sosD.getText().toString()));
        uc.setDers_yanlis(Integer.parseInt(sosY.getText().toString()));

        veriler_ders.add(uc);

        Deneme_ders iki=new Deneme_ders();
        iki.setDers_isim("Matematik");
        iki.setDers_dogru(Integer.parseInt(matD.getText().toString()));
        iki.setDers_yanlis(Integer.parseInt(matY.getText().toString()));

        veriler_ders.add(iki);


        Deneme_ders dort=new Deneme_ders();
        dort.setDers_isim("Fen");
        dort.setDers_dogru(Integer.parseInt(fenD.getText().toString()));
        dort.setDers_yanlis(Integer.parseInt(fenY.getText().toString()));

        veriler_ders.add(dort);



        new_deneme.setVeriler_ders(veriler_ders);


        return new_deneme;

    }
}
