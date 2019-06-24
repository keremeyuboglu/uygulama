package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.R;
import com.example.acer.hayditrkiyeleri.ThisApplication;


public class FragmentSignupFirst extends Fragment {

    private EditText username, password, password2, email;
    static UserInfo userinfo=new UserInfo();


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup1, container, false);

        username= view.findViewById(R.id.Username);
        password= view.findViewById(R.id.pass);
        password2= view.findViewById(R.id.password2);
        email= view.findViewById(R.id.Email);

        Button changeFragment2 = view.findViewById(R.id.button2);

        setRetainInstance(true);

        changeFragment2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String user= username.getText().toString();
                String pass1= password.getText().toString();
                String pass2= password2.getText().toString();
                String mail= email.getText().toString();

                if(!assertEntriesAreGood(user, pass1, pass2, mail)){
                    setError();
                }else{
                    userinfo.setUsername(user);
                    userinfo.setPassword(pass1);
                    userinfo.setEmail(mail);
                }


                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                FragmentSignupSecond newGamefragment = new FragmentSignupSecond();
                fragmentTransaction.replace(R.id.signupContainer, newGamefragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                ((ThisApplication)getActivity().getApplication()).initialize_altbasliklar();
            }


        });

        return view;
    }


    private boolean assertEntriesAreGood(String user, String pass1, String pass2, String email){
        //Must be implemented
        return true;
    }

    private void setError(){
        //Must be implemented
    }
}