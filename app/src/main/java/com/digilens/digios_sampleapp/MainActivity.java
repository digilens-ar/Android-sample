/*==============================================================================
Copyright (c) 2023 Digilens Inc.

All rights reserved.

DigiLens Proprietary and Confidential.
==============================================================================*/
package com.digilens.digios_sampleapp;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes title bar and status bar from the current window, allowing for full screen experience
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Set up ImageButton and TextView to change size and color when the icon is focused
        SetupImageButtonandTextView(findViewById(R.id.imageButton1_1),findViewById(R.id.textView1_1),R.drawable.launcher_appdrawer_s,R.drawable.launcher_appdrawer);

        // Open the next activity when the button is pressed
        findViewById(R.id.imageButton1_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Open_Activity(MainActivity2.class);
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_MENU:
                    // This key event is triggered when the scroll wheel is pressed for 1.5 seconds
                    Open_Activity(MainActivity_Submenu.class);
                    return true;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    // This key event is triggered when the scroll wheel is rotated towards the user
                    //TODO : Do your task here
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    // This key event is triggered when the scroll wheel is rotated away from the user
                    //TODO : Do your task here
                    break;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private void Open_Activity(Class ActivityName) {
        if (ActivityName != null) {
            Intent myIntent = new Intent(getApplicationContext(),ActivityName);
            myIntent.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(myIntent);
        }
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
}