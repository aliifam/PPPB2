package com.aliif.utsp3b2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    private Button button;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }catch (NullPointerException ignored){
        }
        setContentView(R.layout.activity_splash);

        button = findViewById(R.id.tombol);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Intro1 productInfo = new Intro1();
        fragmentTransaction.add(R.id.fragment, productInfo).commit();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFirst){
                    changeFragment();
                }else {
                    masuk();
                }
            }
        });
    }

    private void changeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Intro2 intro2 = Intro2.newInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, intro2).commit();


        button.setText("Lanjut Masuk Aplikasi");
        isFirst = false;
    }

    private void masuk(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}