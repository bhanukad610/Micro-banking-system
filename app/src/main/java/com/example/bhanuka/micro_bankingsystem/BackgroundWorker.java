package com.example.bhanuka.micro_bankingsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx){
        context = ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String sendToAccount_url = "https://polaapp.000webhostapp.com/sendToAccounts.php";

        //SendDataToAccounts
        if (type.equals("sendToAccounts")){
            try {
                String account_number = params[1];
                String customer_NIC = params[2];
                String account_type = params[3];
                String status = params[4];
                String current_balance = params[5];
                String account_details = params[6];
                String branch_number = params[7];
                URL url = new URL(sendToAccount_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("account_number","UTF-8")+"="+URLEncoder.encode(account_number,"UTF-8")+"&"
                +URLEncoder.encode("customer_NIC","UTF-8")+"="+URLEncoder.encode(customer_NIC,"UTF-8")+"&"
                +URLEncoder.encode("account_type","UTF-8")+"="+URLEncoder.encode(account_type,"UTF-8")+"&"
                +URLEncoder.encode("status","UTF-8")+"="+URLEncoder.encode(status,"UTF-8")+"&"
                +URLEncoder.encode("current_balance","UTF-8")+"="+URLEncoder.encode(current_balance,"UTF-8")+"&"
                +URLEncoder.encode("account_details","UTF-8")+"="+URLEncoder.encode(account_details,"UTF-8")+"&"
                +URLEncoder.encode("branch_number","UTF-8")+"="+URLEncoder.encode(branch_number,"UTF-8");
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


        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Sending data status");

    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
