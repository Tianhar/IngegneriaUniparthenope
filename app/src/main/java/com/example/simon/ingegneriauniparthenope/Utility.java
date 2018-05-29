package com.example.simon.ingegneriauniparthenope;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Classe Utility.
 */
public class Utility {
    /**
     * New facebook intent intent.
     *
     * @param pm  the pm
     * @param url the url
     * @return the intent
     */
    /**
     * Connessione a Facebook
     */

    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);

        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /**
     * Verifica connessione
     *
     * @param context the context
     * @return the int
     */
    public static int checkConnectivity(Context context) {
        boolean enabled = true;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        int internet;
        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            internet = 0;//sin connessione
            enabled = false;
        } else {
            internet = 1;//connessione
        }

        return internet;
    }

    /**
     * Metodo save e get ArrayList
     *
     * @param context
     * @param arrayList
     * @param nomefile
     */

    public static void saveArrayList(Context context, ArrayList<String> arrayList, String nomefile) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(nomefile, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(arrayList);
            out.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param context
     * @param nomefile
     * @return
     */
    public static ArrayList<String> getSavedArrayList(Context context, String nomefile) {
        ArrayList<String> savedArrayList = null;

        try {
            FileInputStream inputStream = context.openFileInput(nomefile);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            savedArrayList = (ArrayList<String>) in.readObject();
            in.close();
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return savedArrayList;
    }
}
