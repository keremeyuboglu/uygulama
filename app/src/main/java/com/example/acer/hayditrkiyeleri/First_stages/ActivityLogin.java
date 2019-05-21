package com.example.acer.hayditrkiyeleri.First_stages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.acer.hayditrkiyeleri.R;

public class ActivityLogin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }
    public void onClick(View v){
        // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_start, new FragmentSignupFirst()).commit();
        //  return;
        startActivity(new Intent(ActivityLogin.this , ActivitySignup.class));

    }
}
