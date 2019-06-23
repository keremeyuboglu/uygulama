package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.R;
import com.llollox.androidprojects.compoundbuttongroup.CompoundButtonGroup;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;


public class FragmentSignupSecond extends Fragment {

    private UserInfo userInfo;
    private CompoundButtonGroup bolum;
    private CompoundButtonGroup sinif;

    public FragmentSignupSecond(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_signup2, container, false);

        // Diğer fraga geçmek için
        Button changeFragment = v.findViewById(R.id.devamButonu);
        // Sıralama mı yoksa Üni mi girecek
        Button buttonSwitch = v.findViewById(R.id.buttonSwitch);

        // Kaç bin istediği
        EditText hedefSayisi = v.findViewById(R.id.hedef_sayilar);
        // OBP notu
        EditText obpEdit = v.findViewById(R.id.obpedit);

        // Cardview'da çizgiyi yok etmek için
        CardView cardView = v.findViewById(R.id.card2);
        cardView.setCardElevation(0);

        // Bölüm ve Sınıf seçenekleri
        bolum = v.findViewById(R.id.radio_bolum);
        sinif = v.findViewById(R.id.radio_sinif);

        // Hedef üni autocomplete
        AutoCompleteTextView uniler = v.findViewById(R.id.hedef_uni); // Bir yerden datalari aliriz artik
        // Üni isimlerini yerleştirme kodları
        String[] uniadları = getResources().getStringArray(R.array.uniarray);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,uniadları);
        uniler.setAdapter(adapter);
        setRetainInstance(true);


        // Sıralama ya da okul butonu
        buttonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonSwitch.getText() == "Okul/Bölüm"){
                    buttonSwitch.setText("Sıralama");
                    uniler.setVisibility(View.VISIBLE);
                    hedefSayisi.setVisibility(View.GONE);
                }
                else{
                    buttonSwitch.setText("Okul/Bölüm");
                    uniler.setVisibility(View.GONE);
                    hedefSayisi.setVisibility(View.VISIBLE);
                }
            }
        });

        LinearLayout mezunExtra = v.findViewById(R.id.mezun_expand);

        sinif.setOnButtonSelectedListener(new CompoundButtonGroup.OnButtonSelectedListener() {
            @Override
            public void onButtonSelected(int position, String value, boolean isChecked) {
                if(position == 1)
                    mezunExtra.setVisibility(View.VISIBLE);
                else
                    mezunExtra.setVisibility(GONE);
            }
        });

        // Devam butonu
        changeFragment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Obp nullsa normalde -1'e atayp izin vermiyordu
                // Ancak şimdi  kolaylık olsun diye 20 dedim nullken de geçebiliriz
                String temp = obpEdit.getText().toString();
                if(obpEdit.getText().toString().trim().length() == 0)
                    temp = "20";

                double no = Double.parseDouble(temp);

                if(no < 0.0 || no > 100.0){
                    Toast.makeText(getActivity(),"OBP 0-100 aralığında olmalıdır",Toast.LENGTH_SHORT).show();
                }

                else {
                    List<Integer> bol= bolum.getCheckedPositions();
                    List<Integer> sin= sinif.getCheckedPositions();

                    if(bol.size() != 0){ //If a radio selected
                        userInfo.setBolum(bol.get(0));
                    }else{
                        //ERROR!!!!
                    }


                    if(sin.size() != 0){ //If a radio selected
                        userInfo.setSinif(sin.get(0));
                    }else{
                        //ERROR!!!!
                    }

                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    FragmentSignupThird newGamefragment = new FragmentSignupThird();
                    fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                }

        });

        return v;
    }

}
