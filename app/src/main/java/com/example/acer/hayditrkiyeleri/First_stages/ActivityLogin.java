package com.example.acer.hayditrkiyeleri.First_stages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.acer.hayditrkiyeleri.MainActivity;
import com.example.acer.hayditrkiyeleri.R;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    public void onClick(View v){
        // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_start, new FragmentSignupFirst()).commit();
        //  return;

        startActivity(new Intent(ActivityLogin.this , ActivitySignup.class));
    }
}