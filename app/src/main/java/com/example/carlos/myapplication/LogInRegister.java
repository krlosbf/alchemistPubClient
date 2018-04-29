package com.example.carlos.myapplication;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogInRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_register);
    }

    Globals g = Globals.getInstance();

    private class logIn extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... v) {
            EditText textUser = findViewById(R.id.textUser);
            EditText textPassword = findViewById(R.id.textPassword);
            String user = textUser.getText().toString();
            String password = textPassword.getText().toString();
            StringBuilder sb = new StringBuilder();

            try {
                String query = String.format(g.getServer()+"/Application/logIn");
                URL url = new URL(query);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();

                // Parámetros envío
                String urlParameters = "user=" + user + "&password=" + password;
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
            TextView textView = findViewById(R.id.textView);
            textView.setText(messageServer);
        }
    }

    private class registerUser extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... v) {
            EditText textUser = findViewById(R.id.textUser);
            EditText textPassword = findViewById(R.id.textPassword);
            String user = textUser.getText().toString();
            String password = textPassword.getText().toString();
            StringBuilder sb = new StringBuilder();

            try {
                String query = String.format(g.getServer()+"/Application/registerUser");
                URL url = new URL(query);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();

                // Parámetros envío
                String urlParameters = "user="+user+"&password="+password;
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
            TextView textView = findViewById(R.id.textView);

            if(messageServer.equals("false")){
                textView.setText("Cannot register the user");
            }
            else if(messageServer.equals("true")){
                textView.setText("User registered");
            }
        }
    }

    public void buttonLogIn(View view){
        new logIn().execute();

    }

    public void buttonRegister(View view){

        EditText textPassword = findViewById(R.id.textPassword);
        EditText textRPassword = findViewById(R.id.textRPassword);

        String pass1 = textPassword.getText().toString();
        String pass2 = textRPassword.getText().toString();
        if(pass1.equals(pass2)) {
            new registerUser().execute();
        }
        else {
            // Pop up passwords no coincideixen als TextView
        }
    }

    /*
    public void buttonLogIn(View view){

        Log.i("Debug","Entro en evento click de LogIn");

        new Thread(new Runnable() {

            EditText textUser = findViewById(R.id.textUser);
            EditText textPassword = findViewById(R.id.textPassword);
            //EditText textRPassword = findViewById(R.id.textRPassword);
            TextView textView = findViewById(R.id.textView);

            String user = textUser.getText().toString();
            String password = textPassword.getText().toString();

            InputStream stream = null;
            String str = "";
            String result = null;
            Handler handler = new Handler();
            public void run() {

                try {


                    String query = String.format("http://192.168.1.42:9000/Application/logIn?user="+user+"&password="+password);
                    //String query = String.format("http://192.168.1.42:9000/Application/logIn?user=Pepe&password=pass");
                    Log.i("Debug","Envío la query");
                    URL url = new URL(query);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000); // milliseconds
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    stream = conn.getInputStream();

                    BufferedReader reader = null;

                    StringBuilder sb = new StringBuilder();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    result = sb.toString();

                    //Codi correcte
                    Log.i("Missatge del servidor", result);
                    handler.post(new Runnable() {
                        public void run() {
                            textView.setText(result);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    */
}
