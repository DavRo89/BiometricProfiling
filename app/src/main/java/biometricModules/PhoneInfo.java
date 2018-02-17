package biometricModules;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;



public class PhoneInfo extends AppCompatActivity implements Recognizable  {
    public static int choice=0;
    private  Context sContext;
    private  Activity sActivity;

    public PhoneInfo(Context context) throws ClassNotFoundException {
        this.sContext=context;
    }
    public PhoneInfo(Activity activity) throws ClassNotFoundException {
        this.sActivity=activity;
    }
    @Override
    public void exec()
    {
    //    PhoneInfo.class.newInstance();
        Log.d("exec","va");
       Intent intento=new Intent(sActivity, DisplayMessageActivity.class);
        sActivity.startActivity(intento);
    }
    private void Privacy(){
        AlertDialog alertDialog = new AlertDialog.Builder(sActivity).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Informativa sulla privacy");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NON ACCETTO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        choice=1;
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ACCETTO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        choice=2;
                        dialog.dismiss();

                    }
                });

        alertDialog.show();

    }
}
