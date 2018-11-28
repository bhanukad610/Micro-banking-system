package com.example.bhanuka.micro_bankingsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    DatabaseHelper myDB;
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx){
        context = ctx;
        myDB = new DatabaseHelper(ctx);
    }



    public String doInBackground(String... params) {

        String type = params[0];
        String sendToTransactions_url = "https://gringottsapp.000webhostapp.com/sendToTransactions.php";
        String getFromAccounts_url = "https://gringottsapp.000webhostapp.com/getFromAccounts.php";

        String out = null;
        if (type.equals("sendToTransactions")){
            try {
                String account_number = params[1];
                String agent_id = params[2];
                String transactionType = params[3];
                String date = params[4];
                String time = params[5];
                String amount = params[6];
                String details = params[7];
                String charges = params[8];
                URL url = new URL(sendToTransactions_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("account_number","UTF-8")+"="+URLEncoder.encode(account_number,"UTF-8")+"&"
                        +URLEncoder.encode("agent_id","UTF-8")+"="+URLEncoder.encode(agent_id,"UTF-8")+"&"
                        +URLEncoder.encode("transactionType","UTF-8")+"="+URLEncoder.encode(transactionType,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+"&"
                        +URLEncoder.encode("amount","UTF-8")+"="+URLEncoder.encode(amount,"UTF-8")+"&"
                        +URLEncoder.encode("details","UTF-8")+"="+URLEncoder.encode(details,"UTF-8")+"&"
                        +URLEncoder.encode("charges","UTF-8")+"="+URLEncoder.encode(charges,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (type.equals("getFromAccounts")){

            try {

                String agent_id = params[1];

                URL url = new URL(getFromAccounts_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("agent_id","UTF-8")+"="+URLEncoder.encode(agent_id,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                myDB.DeleteAll("transactions");
                myDB.DeleteAll("accounts");
                myDB.insertToAccounts(result);
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();

            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("From Central Server");

    }

    @Override
    protected void onPostExecute(String result) {
        if (result == null){
            alertDialog.setMessage("connection error");
        }
        else {
            alertDialog.setMessage(result);
        }

        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
