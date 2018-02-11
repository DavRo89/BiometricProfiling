package com.example.davide.biometricprofiling;



import android.content.Context;
import android.util.Log;


import biometricModules.Recognizable;
import dalvik.system.DexFile;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;


public class ModuleReader {
    private  Context sContext;

   public static ArrayList<String> moduli=new ArrayList<String>();
    public ModuleReader(Context context) throws ClassNotFoundException {
        this.sContext=context;
    }



//metodo per ottenere i moduli esterni cercando l'interfaccia Recognizable

  public ArrayList<String> getClassesOfPackage(String packageName) {


      String compare;
      String PackageName="biometricModules";
      try {
          DexFile df = new DexFile(sContext.getPackageCodePath());

          for (Enumeration<String> iter = df.entries(); iter.hasMoreElements();) {
              compare = iter.nextElement();
          //    Log.d("filezzz",s);
if(compare.contains(PackageName)&& !compare.contains("biometricModules.Recognizable")){
    Log.d("Classe",compare);
if(Recognizable.class.isAssignableFrom(Class.forName(compare))){
    moduli.add(compare);
    Log.d("interfaccia",moduli.toString());
}

}

/*
              Reflections reflections = new Reflections("com.example.davide.biometricprofiling");
              Log.d("2222",reflections.toString());
              Set<Class<? extends Recognizable>> classes = reflections.getSubTypesOf(Recognizable.class);
            Log.d("classi",classes.toString());
*/
          }
      } catch (IOException e) {
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
      Log.d("filezz2",sContext.getPackageName());
      return moduli;
    }




}





