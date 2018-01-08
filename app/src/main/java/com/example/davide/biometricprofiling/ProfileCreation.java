package com.example.davide.biometricprofiling;


import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileCreation extends AppCompatActivity  implements MainFragment.OnListItemClickListener{
    EditText textmsg;
    static final int READ_BLOCK_SIZE = 100;
    public static String nome;
    public static ArrayList<String> Lines = new ArrayList<String>();
    public static String[] biometric_names ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        textmsg=(EditText)findViewById(R.id.edit_name);
        if (savedInstanceState == null) {
            MainFragment fragment = new MainFragment();
            Bundle bundle = new Bundle();


       biometric_names=(getResources().getStringArray(R.array.biometric_array));
          Lines.addAll(Arrays.asList(biometric_names));

            bundle.putStringArrayList("biometric_names",Lines);
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
            /*
            Bundle bundle = new Bundle();
            bundle.putStringArray("nomi", getResources().getStringArray(R.array.biometric_array));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
                    */
        }
/*
        try {
            FileOutputStream fOut = openFileOutput("caso",Context.MODE_PRIVATE);
            Log.d("filepath",fOut.toString());
            OutputStreamWriter outputWriter=new OutputStreamWriter(fOut);
            String str = "test data";
            outputWriter.write(textmsg.getText().toString());
            fOut.write(str.getBytes());
            fOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

    }

    /*    final Button btn = (Button) findViewById(R.id.button1);*/



    public void WriteBtn(View v) {


        // add-write text into file
        try {
            nome=textmsg.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putStringArray("nomi", getResources().getStringArray(R.array.biometric_array));
            ;


            MainFragment fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
            fragment.setArguments(bundle);


            /*
            FileOutputStream fileout=openFileOutput(nome, MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.close();
            */

/*
           Fragment fragment = new RecyclerListFragment();
            bundle.putStringArray("nomi", getResources().getStringArray(R.array.biometric_array));
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
            fragment.setArguments(bundle);
*/
           // startActivity(new Intent(ProfileCreation.this, Biometrics_features.class));
            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ReadBtn(View v) {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput(nome);
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            textmsg.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Delete(View v){
        File mydir = getFilesDir(); //get your internal directory
        File myFile = new File(mydir, textmsg.getText().toString());
        myFile.delete();

    }

    @Override
    public void onListItemClick(int position) {
        Fragment fragment = null;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }


}
