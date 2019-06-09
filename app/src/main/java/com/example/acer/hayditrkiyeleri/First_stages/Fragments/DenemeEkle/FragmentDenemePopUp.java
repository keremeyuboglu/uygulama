package com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.acer.hayditrkiyeleri.R;

import org.greenrobot.eventbus.EventBus;

// PopUp for Diger
public class FragmentDenemePopUp extends DialogFragment implements View.OnClickListener{

    Button yesButton, noButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deneme_ekle1_popup1,null);
        yesButton = view.findViewById(R.id.nongeneric_ekle2);
        noButton = view.findViewById(R.id.generic_ekle2);

        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);


        return view;
    }


    public void onClick(View v){
        if(v.getId() == R.id.nongeneric_ekle2){
            EventBus.getDefault().post(new EventTransfer.popUp(1));
            dismiss();

        }else{
            EventBus.getDefault().post(new EventTransfer.popUp(0));
            dismiss();
        }

    }


}
