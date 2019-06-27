package com.example.acer.hayditrkiyeleri.First_stages.Fragments.SignUp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.acer.hayditrkiyeleri.R;
import com.google.android.material.textfield.TextInputLayout;


public class FragmentSignupFirst extends Fragment {

    private TextInputLayout username, password, password2, email;
    private CheckBox checkBox;
    static UserInfo userinfo=new UserInfo();


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup1, container, false);

        username= view.findViewById(R.id.userText);
        password= view.findViewById(R.id.passtext);
        password2= view.findViewById(R.id.pass2text);
        email= view.findViewById(R.id.Email);
        checkBox = view.findViewById(R.id.checkBox);


        Button changeFragment2 = view.findViewById(R.id.button2);
        Button showSozlesme = view.findViewById(R.id.button1);

        setRetainInstance(true);

        showSozlesme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment2.setEnabled(true);
                FragmentKullaniciSozlesmesi fragmentKullaniciSozlesmesi = new FragmentKullaniciSozlesmesi();
                fragmentKullaniciSozlesmesi.show(getFragmentManager(),"kullanicisozlesmesi");
            }
        });
        changeFragment2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String user= username.getEditText().getText().toString().trim();;
                String pass1= password.getEditText().getText().toString().trim();
                String pass2= password2.getEditText().getText().toString().trim();
                String mail= email.getEditText().getText().toString().trim();

                // Şifreler eşleşmezse sonrasında null için de hata verdilmeli
                if(!pass1.equals(pass2)){
                    password2.setError("Şifreler eşleşmiyor");
                }
                if(pass1.length() > 16)
                {
                    password.setError("Çok uzun şifre girildi.");
                }
                // E maili check etmek için
                /*else if(!isValidEmail(mail)){
                    password.setError("Mail adresi geçerli değil");
                }*/
                else {
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
                }
            }


        });

        return view;
    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean assertEntriesAreGood(String user, String pass1, String pass2, String email){
        //Must be implemented
        return true;
    }

    private void setError(){
        //Must be implemented
    }
}