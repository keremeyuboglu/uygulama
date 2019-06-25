package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.acer.hayditrkiyeleri.R;

public class FragmentKullaniciSozlesmesi extends DialogFragment {

    Button button;
    ListView listView;
    String[] konuicerigi ;
    String[] konuicerig2i = {"asdnaklsndklansdklanslkdnaklsndkalnsdklansdna","aslmdaşlmdlşmwdşlmaşldmaşlsmdlşamsd","salşmdşlmsdşlamşlsdmaşlsdma"} ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deneme_ekle2_generic_tip,null);
        listView = v.findViewById(R.id.listem);
        button = v.findViewById(R.id.buttonum);
        Resources resources = getResources();
        konuicerigi = resources.getStringArray(R.array.sozlesme);
        ArrayAdapter<String> customAdapter = new ArrayAdapter<String>(getContext(),R.layout.custom_layout,R.id.txt,konuicerigi);
        listView.setAdapter(customAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return v;
    }
}
