package com.aliif.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }catch (NullPointerException ignored){
        }

        setContentView(R.layout.activity_main);

        button = findViewById(R.id.tombol);

        Fragment fragment = ProductInfo.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.)

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        ProductInfo productInfo = new ProductInfo();
//        fragmentTransaction.add(R.id.fragment, productInfo).commit();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment
//                if(!isFirst){
//                    changeFragment();
//                }else {
//                    displayFragment();
//                }
            }
        });
    }

//    private void changeFragment(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        ProductBenefit productBenefit = ProductBenefit.newInstance();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, productBenefit).commit();
//
//        isFirst = false;
//    }
//
//    private void displayFragment(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        ProductBenefit productBenefit = (ProductBenefit) fragmentManager.findFragmentById(R.id.fragment);
//
//        if(productBenefit != null){
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.fragment, new ProductBenefit()).commit();
//        }
//
//        isFirst = true;
//    }
}