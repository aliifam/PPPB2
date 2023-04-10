package com.aliif.week6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HelpActivity extends AppCompatActivity {

    private static final String TAG = HelpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneNumber = getString(R.string.support_phone);
                callSupportCenter(PhoneNumber);
            }
        });
    }

    private void callSupportCenter(String phoneNumber)
    {
        String formatNUmber = String.format("tel: %s", phoneNumber);

        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(formatNUmber));

        if(dialIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(dialIntent);
        }else
        {
            Log.e(TAG, getString(R.string.dial_log_message));
        }
    }
}