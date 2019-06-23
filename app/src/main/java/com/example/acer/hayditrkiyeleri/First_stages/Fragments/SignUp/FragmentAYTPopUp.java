package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle.EventTransfer;
import com.example.acer.hayditrkiyeleri.R;

import org.greenrobot.eventbus.EventBus;

public class FragmentAYTPopUp extends DialogFragment implements View.OnClickListener{

    Button yesButton, noButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup4_popup,null);
        yesButton = view.findViewById(R.id.nongeneric_ekle2);
        noButton = view.findViewById(R.id.generic_ekle2);

        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);


        return view;
    }


    public void onClick(View v){
        if(v.getId() == R.id.nongeneric_ekle2){
            EventBus.getDefault().post(new EventTransfer.AYTpopUp(1));
            dismiss();

        }else{
            EventBus.getDefault().post(new EventTransfer.AYTpopUp(0));
            dismiss();
        }

    }
}
