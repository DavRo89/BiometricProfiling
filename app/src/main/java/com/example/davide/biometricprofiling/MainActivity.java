package com.example.davide.biometricprofiling;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        String yourFilePath = getApplicationContext().getFilesDir() + "/" + "prova.txt";

        File yourFile = new File( yourFilePath );

        Log.d("myTag", yourFile.toString());
*/

        if(DisplayMessageActivity.choice==0){

    Intent intent = new Intent(this, DisplayMessageActivity.class);
    startActivity(intent);
}

    setContentView(R.layout.activity_main);

        Button btn2 = (Button)findViewById(R.id.button2);
    /*    final Button btn = (Button) findViewById(R.id.button1);*/

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when play is clicked show stop button and hide play button

                startActivity(new Intent(MainActivity.this, ProfileManager.class));
            }
        });


        Button btn = (Button)findViewById(R.id.button);
    /*    final Button btn = (Button) findViewById(R.id.button1);*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when play is clicked show stop button and hide play button

                startActivity(new Intent(MainActivity.this, ProfileCreation.class));
            }
        });

    }



/*
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Load XML for parsing.
                AssetManager assetManager = getAssets();
                InputStream inputStream = null;
                try {
                    inputStream = assetManager.open("Biometric.xml");
                } catch (IOException e) {
                    Log.e("tag", e.getMessage());
                }

                String s = readTextFile(inputStream);
                TextView tv = (TextView)findViewById(R.id.textView1);
                tv.setText(s);
            }
        });


*/

}


