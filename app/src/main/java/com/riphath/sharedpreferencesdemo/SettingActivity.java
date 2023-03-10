package com.riphath.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    EditText editTextUsername;
    RadioGroup radioGroupColors;
    Button buttonSave;
    RadioButton radioGreen, radioBlue, radioYellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        editTextUsername = findViewById(R.id.editTextTextName);
        radioGroupColors=findViewById(R.id.radioGroupColors);
        buttonSave = findViewById(R.id.buttonSave);
        radioBlue=findViewById(R.id.radioBlue);
        radioGreen=findViewById(R.id.radioGreen);
        radioYellow=findViewById(R.id.radioYellow);


        class SaveLsitener implements View.OnClickListener
        {
            @Override
            public void onClick(View view) {
                //get user selected data
                String uname = editTextUsername.getText().toString();
                int colorValue = 0;
                if(radioGroupColors.getCheckedRadioButtonId() == R.id.radioBlue)
                {
                    colorValue = 1; //for blue
                }
                if(radioGroupColors.getCheckedRadioButtonId() == R.id.radioYellow)
                {
                    colorValue = 2; //for yellow
                }
                if(radioGroupColors.getCheckedRadioButtonId() == R.id.radioGreen)
                {
                    colorValue = 3; //for green
                }
                //save above data in SharePreferences

                SharedPreferences sp = getSharedPreferences("appsettings", MODE_PRIVATE);

                SharedPreferences.Editor editor = sp.edit();

                editor.putString("username", uname);
                editor.putInt("color_code", colorValue);
                editor.commit();

                Toast.makeText(SettingActivity.this, "Settings Saved....", Toast.LENGTH_SHORT).show();

                finish();
            }
        }

        buttonSave.setOnClickListener( new SaveLsitener());

    }//eof onCreate

    @Override
    protected void onStart()
    {
        super.onStart();
        //get saved data from shared preference
        SharedPreferences sp = getSharedPreferences("appsettings", MODE_PRIVATE);
        //get username
        String uname = sp.getString("username", "not set");
        editTextUsername.setText( uname);

        int colorCode = sp.getInt("color_code", 0);
        if(colorCode==1){
            radioBlue.setChecked(true);
        }
        if(colorCode==2){
            radioYellow.setChecked(true);
        }
        if(colorCode==3){
            radioGreen.setChecked(true);
        }

    }

}