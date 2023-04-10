package com.aliif.week6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView inputText;
    TextView price;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelp();
            }
        });

        inputText = findViewById(R.id.input_text);
        price = findViewById(R.id.total_price);
        submit = findViewById(R.id.btn_submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int harga = Integer.parseInt(inputText.getText().toString());
                int totalHarga = harga*100;

                // get the current locale
                Locale current = getResources().getConfiguration().locale;
                // get the currency format for the current locale
                String format = NumberFormat.getCurrencyInstance(current).format(totalHarga);
                price.setText(format);
            }
        });

//        String formDate = DateFormat.getDateInstance().format(expired);
//        TextView expiredText = findViewById(R.id.date);
//        expiredText.setText(formDate);
    }

    private void showHelp()
    {
        Intent help = new Intent(this, HelpActivity.class);

        startActivity(help);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_help)
        {
            Intent intent = new Intent( this, HelpActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_language)
        {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}