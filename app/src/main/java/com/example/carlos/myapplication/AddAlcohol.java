package com.example.carlos.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddAlcohol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alcohol);
    }

    Globals g = Globals.getInstance();

    private class addAlcohol extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... v) {
            EditText textName = findViewById(R.id.textName);
            EditText textVolume = findViewById(R.id.textVolume);
            EditText textPrice = findViewById(R.id.textPrice);

            String name = textName.getText().toString();
            String volume = textVolume.getText().toString();
            String price = textPrice.getText().toString();

            StringBuilder sb = new StringBuilder();

            try {
                String query = String.format(g.getServer()+"/Application/addAlcohol");
                URL url = new URL(query);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();

                // Parámetros envío
                String urlParameters = "name=" + name + "&volume=" + volume + "&price=" + price;
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                // Respuesta
                InputStream stream = conn.getInputStream();
                BufferedReader reader;
                reader = new BufferedReader(new InputStreamReader(stream));
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                // Escape early if cancel() is called
                //if (isCancelled()) break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        /*protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }*/

        protected void onPostExecute(String messageServer) {
            /*TextView textView = findViewById(R.id.textView);
            textView.setText(messageServer);*/
        }
    }

    private class buttonJSON extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... v) {

            StringBuilder sb = new StringBuilder();

            try {
                String query = String.format(g.getServer());
                URL url = new URL(query);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                //conn.setDoOutput(true);
                conn.connect();

                /* Parámetros envío
                String urlParameters = "name=" + name + "&volume=" + volume + "&price=" + price;
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();*/

                // Respuesta
                InputStream stream = conn.getInputStream();
                BufferedReader reader;
                reader = new BufferedReader(new InputStreamReader(stream));
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        /*protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }*/

        protected void onPostExecute(String messageServer) {
            Log.i("JSON",messageServer);
            Gson gson = new Gson();
            Alcohol a = gson.fromJson(messageServer,Alcohol.class);
            g.addAlcohol(a);
        }
    }

    public void buttonAddAlcohol(View view){
        new AddAlcohol.addAlcohol().execute();
    }

    public void buttonJSON(View view){
        new AddAlcohol.buttonJSON().execute();
    }
}
