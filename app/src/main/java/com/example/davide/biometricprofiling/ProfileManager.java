package com.example.davide.biometricprofiling;


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


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ProfileManager extends ActionBarActivity implements MainFragment.OnListItemClickListener {
    private final List<String> mItems = new ArrayList<>();

  public   String bio="";
  public    static   ArrayList<String> scripts = new ArrayList<String>();

   public static List<String> collection = new ArrayList<String>();
    public    static   ArrayList<String> ListCollection = new ArrayList<String>();
public static int posizione;
public File[] files2;
public List<File> filesNoFolder= new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_profile_manager);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        if (savedInstanceState == null) {
            MainFragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }



    }

    @Override
    public void onListItemClick(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                posizione=0;

                fragment = new RecyclerListFragment();

                try {
                    ReadProfiles(posizione);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("nomi", scripts);
                    bundle.putStringArrayList("biometric_names", ListCollection);
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
                posizione=1;
                fragment = new RecyclerListFragment();

                try {
                    ReadProfiles(posizione);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("nomi", scripts);
                    bundle.putStringArrayList("biometric_names", ListCollection);
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
                posizione=2;
                fragment = new RecyclerListFragment();
                try {
                    ReadProfiles(posizione);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("nomi", scripts);
                    bundle.putStringArrayList("biometric_names", ListCollection);
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
            reader = new BufferedReader(

                    new FileReader(filesNoFolder.get(indice)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                temp = mLine.split(",");
                bio=bio+mLine;

                scripts.add(mLine.replace(",", " "));
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
        Log.d("myTag", bio);



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

        for(int i=0;i<files2.length;i++){

            if(!files2[i].isDirectory()){
filesNoFolder.add(files2[i]);
        collection.add(files2[i].toString().replace(s+"/",""));
        ListCollection.add(files2[i].toString().replace(s+"/",""));
        }}

/*
        AssetManager assetManager = getApplicationContext().getAssets();
         files = assetManager.list("Profiles");
        collection = new ArrayList<String>(Arrays.asList(files));
        */

Log.d("file", collection.toString());
        Log.d("file2", ListCollection.toString());
    }


}


