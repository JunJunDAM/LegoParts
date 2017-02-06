package com.example.dam.legoparts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by DAM on 2/2/17.
 */

public class Descarrega extends AsyncTask<Void, String, Boolean>{

    private Context context;
    public  Descarrega (Context context){
        this.context = context;
    }

    private ProgressDialog pDialog;

    @Override protected void onPreExecute(){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Descarregant fitxers...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        pDialog.setTitle("Esperi siusplau");
        pDialog.setMessage("Continua esperant siusplau");
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        int count;
        try{
            URL url = new URL("https://rebrickable.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            int lenghtOfFile = connection.getContentLength();
            pDialog.setMax(lenghtOfFile);
            InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte data[] = new byte[1024];
            long total = 0;
            while ((count = inputStream.read(data)) != -1){
                total += count;
                publishProgress("" + (int)((total * 100) / lenghtOfFile));
                outputStream.write(data, 0, count);
            }
            inputStream.close();
            outputStream.flush();
            String xml = new String(outputStream.toByteArray());
        }catch (Exception e){
            Log.e("Error: ", e.getMessage());
            return false;
        }
        return true;
    }

    protected void onProgressUpdate(String... progress){
        pDialog.setProgress(Integer.parseInt(progress[0]));
    }

}
