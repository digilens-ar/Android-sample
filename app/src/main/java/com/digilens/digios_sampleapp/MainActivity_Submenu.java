/*==============================================================================
Copyright (c) 2023 Digilens Inc.

All rights reserved.

DigiLens Proprietary and Confidential.
==============================================================================*/
package com.digilens.digios_sampleapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;
import java.util.Date;

public class MainActivity_Submenu extends AppCompatActivity {

    Date currentTime;

    BatteryManager batteryManager;
    int batteryPercentage;
    String batteryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes title bar and status bar from the current window, allowing for full screen experience
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_submenu);

        SetupImageButtonandTextView(findViewById(R.id.imageButton1s_1),findViewById(R.id.textView1s_1),R.drawable.about_s,R.drawable.about);
        findViewById(R.id.imageButton1s_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTime = Calendar.getInstance().getTime();
                MAKE_A_TOAST("Current Time: "+currentTime.toString());
            }
        });

        SetupImageButtonandTextView(findViewById(R.id.imageButton1s_2),findViewById(R.id.textView1s_2),R.drawable.launcher_settings_s,R.drawable.launcher_settings);
        findViewById(R.id.imageButton1s_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the instance of the BatteryManager class
                batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
                // Get the battery percentage
                batteryPercentage = (int) batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
                batteryText = "Battery Percentage: " + batteryPercentage + "%";
                MAKE_A_TOAST(batteryText);
            }
        });
    }

    private void SetupImageButtonandTextView(ImageButton imageButton, TextView textView, int focused_image, int unfocused_image) {
        imageButton.setDefaultFocusHighlightEnabled(false);
        imageButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    imageButton.setImageResource(focused_image);
                    ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) imageButton.getLayoutParams();
                    lp.width = 120;
                    lp.height = 120;
                    imageButton.setLayoutParams(lp);
                    textView.setTextColor(Color.parseColor("#009FA0"));
                } else {
                    imageButton.setImageResource(unfocused_image);
                    ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) imageButton.getLayoutParams();
                    lp.width = 90;
                    lp.height = 90;
                    imageButton.setLayoutParams(lp);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                }
            }
        });
    }

    //Show a pop-up message with the given input text
    private void MAKE_A_TOAST(String text) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
            }
        });
    }

}