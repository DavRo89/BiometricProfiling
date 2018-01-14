package com.example.davide.biometricprofiling;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;

/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import android.app.Activity;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProfileManager extends ActionBarActivity implements MainFragment.OnListItemClickListener {
    private final List<String> mItems = new ArrayList<>();

 // public   String bio="";
  private static   ArrayList<String> scripts = new ArrayList<String>();

   private static List<String> collection = new ArrayList<String>();
    private static   ArrayList<String> ListCollection = new ArrayList<String>();
    private File[] files2;
private List<File> filesNoFolder= new ArrayList<>();
    JSONObject obj = new JSONObject();
 public   String nomeProfilo;
   public int indiceAssoluto;


    public  List<String> Biom=new ArrayList<String>();//lista biometrie del profilo letto


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);





        try {
            getProfilesName();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
      //  FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
       // btn.setImageIcon(R.drawable.ic_add_circle_outline_black_24dp);
        setContentView(R.layout.activity_profile_manager);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));



        if (savedInstanceState == null ) {
            MainFragment fragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("nomi", scripts);
            Log.d("vediamo", ListCollection.toString());
            bundle.putStringArrayList("nomiB", ListCollection);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }
        final View actionB = findViewById(R.id.action_b);

        FloatingActionButton actionC = new FloatingActionButton(getBaseContext());
        actionC.setTitle("Hide/Show Action above");
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(actionC);



        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.white));


        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionA.setTitle("Action A clicked");
            }
        });


        FloatingActionButton addedOnce = new FloatingActionButton(this);
        addedOnce.setTitle("Added once");


        FloatingActionButton addedTwice = new FloatingActionButton(this);
        addedTwice.setTitle("Added twice");



    }

    @Override
    public void onListItemClick(int position) {
        Fragment fragment = null;

        int posizione;
        switch (position) {
            case 0:
                posizione =0;
                indiceAssoluto=0;
                fragment = new RecyclerListFragment();

                try {

                    ReadProfiles(posizione);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("nomi", scripts);
                    fragment.setArguments(bundle);


                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;

        }

        switch (position) {
            case 1:
                indiceAssoluto=1;
                posizione =1;
                fragment = new RecyclerListFragment();

                try {
                    ReadProfiles(posizione);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("nomi", scripts);
                    fragment.setArguments(bundle);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                break;

        }

        switch (position) {
            case 2:
                indiceAssoluto=2;
                posizione =2;
                fragment = new RecyclerListFragment();
                try {
                    ReadProfiles(posizione);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("nomi", scripts);
                    fragment.setArguments(bundle);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                break;

        }


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void ReadProfiles(int indice) throws PackageManager.NameNotFoundException, FileNotFoundException {
        BufferedReader reader = null;
scripts.clear();
        String[] temp;
        PackageManager m = getPackageManager();

        String s = getPackageName();
        PackageInfo p = null;
        try {
            p = m.getPackageInfo(s, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        s = p.applicationInfo.dataDir+"/files";


        try {

         //   FileInputStream fis = new FileInputStream(files2[(indice)].toString());
            Log.d("contenuto",files2[indice].toString());
            nomeProfilo=files2[indice].toString();

            reader = new BufferedReader(

                    new FileReader(filesNoFolder.get(indice)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                temp = mLine.replace("[","").replace("]","").split(",");
             //   bio=bio+mLine.replace("[","").replace("]","");

              //  scripts.add(mLine.replace(",", " "));
               scripts.addAll(Arrays.asList(temp));

                if (temp.length > 0) {

                }
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
     //   Log.d("myTag", bio);
    }



    public void getProfilesName() throws IOException, PackageManager.NameNotFoundException {
        PackageManager m = getPackageManager();
        String s = getPackageName();
        PackageInfo p = m.getPackageInfo(s, 0);
        s = p.applicationInfo.dataDir+"/files";
        Log.d("profili", s);
        File directory = new File(s);

        files2 = directory.listFiles();

        Log.d("Files", "Size: "+ files2.length);

        collection.clear();
        ListCollection.clear();
        for(int i=0;i<files2.length;i++){

            if(!files2[i].isDirectory()){
filesNoFolder.add(files2[i]);
        collection.add(files2[i].toString().replace(s+"/",""));
        ListCollection.add(files2[i].toString().replace(s+"/",""));
        }}


Log.d("file", collection.toString());
        Log.d("file2", ListCollection.toString());
    }



    public void  getList(List<String> lista){
        Biom.clear();
        Biom.addAll(lista);

    }

    public void Savez(View v) throws JSONException {

        obj.put("Sessione", Biom);
       Log.d("biom", Biom.get(0));
       System.out.println(obj);
        Log.d("menu", scripts.get(0));

        boolean isFileCreated = create(ProfileManager.this, collection.get(indiceAssoluto), obj.getString("Sessione"));
        if(isFileCreated) {
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } else {
            //show error or try again.
        }

    }


    private boolean create(Context context, String fileName, String jsonString){

        try {
            FileOutputStream fos = openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }
}


