package com.example.acer.hayditrkiyeleri.First_stages;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp.FragmentSignupFirst;
import com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp.UserInfo;
import com.example.acer.hayditrkiyeleri.R;

public class ActivitySignup extends AppCompatActivity {

    private UserInfo userinfo= new UserInfo();
    private int scrolltyt = 0;
    private int scrollayt = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if(null == savedInstanceState) {
            FragmentManager fragmentManager2 = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();

            FragmentSignupFirst fragment2 = new FragmentSignupFirst(userinfo);
            fragmentTransaction2.add(R.id.signupContainer, fragment2);
            fragmentTransaction2.commit();
        }




    }

    void signupend(View view){} //Must be implemented

    public int getScroll(){
        return scrolltyt;
    }
    public void setScroll(){
        scrolltyt = scrolltyt + 1;
    }

    public int getScrollayt() {
        return scrollayt;
    }

    public void setScrollayt() {
        scrollayt = scrollayt + 1;
    }
}
