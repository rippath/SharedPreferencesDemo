package com.riphath.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewWelcome;
    Button buttonSettings;
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonSettings = findViewById(R.id.buttonSettings);
        mainLayout = findViewById(R.id.mainLayout);

        class  SettingListener implements View.OnClickListener
        {
            @Override
            public void onClick(View view) {
                Intent inSetting = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(inSetting );
            }
        }

        buttonSettings.setOnClickListener(new SettingListener());

    }//eof onCreate

    //Apply saved settings

    @Override
    protected void onStart()
    {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("appsettings", MODE_PRIVATE);
        String uname = sp.getString("username","not set");
        int colorCode = sp.getInt("color_code", 0);

        textViewWelcome.setText("Welcome "+uname);

        if(colorCode==1){
            mainLayout.setBackgroundColor(Color.BLUE);
        }

        if(colorCode==2){
            mainLayout.setBackgroundColor(Color.YELLOW);
        }

        if(colorCode==3){
            mainLayout.setBackgroundColor(Color.GREEN);
        }
    }
}