package com.example.orderapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    public static final String inet = "10.0.0.170";
    public static final String port = "8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.sendToServer);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToServer();
            }
        });
    }

    private String formatJSON(){
        JSONObject almond = new JSONObject();
        JSONObject milk = new JSONObject();
        JSONObject user = new JSONObject();
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        EditText alm = (EditText) findViewById(R.id.editText);
        String almondCount = alm.getText().toString();
        long aCount;
        try{
            aCount = Long.valueOf(almondCount);
            almond.put("productName", "Local Almond");
            almond.put("count", aCount);
            array.put(almond);
        }catch(Exception e){
            aCount = 0;
        }

        EditText mil = (EditText) findViewById(R.id.editText2);
        String milkCount = mil.getText().toString();
        long mCount;
        try{
            mCount = Long.valueOf(milkCount);
            milk.put("productName", "Friendly Farm Milk");
            milk.put("count", mCount);
            array.put(milk);

        }catch(Exception e){
            mCount = 0;
        }

        if (aCount == 0 && mCount == 0){
            return null;
        }

        try {
            json.put("products", array);
            json.put("email", "user6@gmail.com");

            return json.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendDataToServer() {

        final String json = formatJSON();

        new  AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params){
                return getServerResponse(json);
            }

//            @Override
//            protected void onPostExecute(String result){
                TextView textView = (TextView)findViewById(R.id.output);
//                textView.setText(json);
//            }

        }.execute();
    }

    private String getServerResponse(String json) {

        try{
            URL url= new URL("http://"+inet+":"+port+"/order");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//              conn.connect();


            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            Log.i("JSON", json);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            //Code to debug HTTP errors
            int responseCode= conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in=new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";
                while((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();

                return json;

                //return sb.toString();
            }
            else {
                return new String("false : "+responseCode);
            }
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }
}






