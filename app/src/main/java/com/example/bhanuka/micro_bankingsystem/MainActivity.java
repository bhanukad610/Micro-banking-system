package com.example.bhanuka.micro_bankingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.database.Cursor;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.makeText;





public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText edit_account_number, edit_amount, edit_details, edit_charges, edit_type;
    CheckBox diposit, withdraw;
    Button submit, viewdata, viewAccounts,delete;
    RadioGroup radioGroup;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);
        edit_account_number = (EditText) findViewById(R.id.editText_account_number);
        edit_amount = (EditText) findViewById(R.id.editText_amount);
        edit_details = (EditText) findViewById(R.id.editText_details);
        edit_charges = (EditText) findViewById(R.id.editText_charges);
        edit_type = (EditText) findViewById(R.id.editText_type);
        submit = (Button) findViewById(R.id.button_submit);
        delete = (Button) findViewById(R.id.button_delete);
        viewdata = (Button) findViewById(R.id.button_viewdata);
        viewAccounts = (Button) findViewById(R.id.button_accounts);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup_type);



        AddData();
        ViewData();
        ViewAccounts();
        DeleteData();


        AddToAccounts("1234", "962232531v","savings", "ok","45000","good account");
        AddToAccounts("9632", "975423641v","fixed", "ok","23000","good account");
        AddToAccounts("4789", "962145213x","joint", "ok","10000","good account");
        //SendDataToAccounts("160101", "962232531V","teen", "ok","5000.00","good account","0001");

    }

    public void AddData() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        final String date = mdformat.format(calendar.getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        final String time = dateFormat.format(calendar.getTime());



        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                         String account_number = edit_account_number.getText().toString();
                         String type = edit_type.getText().toString();
                         String amount = edit_amount.getText().toString();
                         String details = edit_details.getText().toString();
                         String charges = edit_charges.getText().toString();
                         String agent_id = "00001"; //hardcoded agent_id
                        SendToTransactions(account_number,agent_id, type, date, time, amount, details, charges);
                        /*if (Integer.parseInt(charges) == 0){
                            SendToTransactions(account_number,agent_id, type, date, time, amount, details, charges);
                        }*/


                        boolean isInserted = myDB.insertToTransactions(account_number, type, date, time, amount, details, charges);

                        if (isInserted = true){
                            makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        }
                        else {
                            makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        }
                        /*get the number of entries in the transactions table
                            long transactionsCount = myDB.getTransactionsCount();
                        int limit = 5;



                            //if count == 5 send to transactions in central server
                            if (transactionsCount == (long) limit){

                            }*/


                    }

    }
        );
    }


    public void AddToAccounts(String account_number, String customer_NIC, String account_type, String status, String current_balance, String account_details){
        boolean inserted = myDB.insertToAccounts(account_number, customer_NIC, account_type, status, current_balance, account_details);
        if (inserted = true)
            System.out.println("successfully added to accounts");
        else
            System.out.println("error occurred");
    }


    public void ViewData(){
        viewdata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDB.getAllData();
                        if (result.getCount() == 0) {
                            showMessage("Error", "Nothing found");

                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()){
                            buffer.append("Account number : " + result.getString(1) + "\n");
                            buffer.append("Type : " + result.getString(2) + "\n");
                            buffer.append("Date :" + result.getString(3) + "\n");
                            buffer.append("Time :" + result.getString(4) + "\n");
                            buffer.append("Amount :" + result.getString(5) + "\n");
                            buffer.append("Details :" + result.getString(6) + "\n");
                            buffer.append("charges :" + result.getString(7) + "\n\n");
                        }

                        showMessage("Data",buffer.toString());
                    }

                }
        );
    }


    public void ViewAccounts(){
        viewAccounts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDB.getAccounts();
                        if (result.getCount() == 0) {
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()) {
                            buffer.append("Account number : " + result.getString(0) + "\n");
                            buffer.append("Customer NIC : " + result.getString(1) + "\n");
                            buffer.append("Type : " + result.getString(2) + "\n");
                            buffer.append("Status :" + result.getString(3) + "\n");
                            buffer.append("Current balance :" + result.getString(4) + "\n");
                            buffer.append("Account details :" + result.getString(5) + "\n\n");
                        }

                        showMessage("Accounts",buffer.toString());
                    }

                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void DeleteData(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDB.DeleteData();
                        makeText(MainActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void SendDataToAccounts(String account_number, String customer_NIC, String account_type, String status, String current_balance, String account_details, String branch_number){
        String type = "sendToAccounts";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, account_number, customer_NIC, account_type, status, current_balance, account_details, branch_number);
    }

    public void SendToTransactions(String accountNumber, String account_number, String transactionType, String date, String time, String amount, String transactionDetails, String charges){
        String type = "sendToTransactions";
        String agent_id = "00001"; //hardcoded agent_id
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, account_number, agent_id, transactionType, date, time, amount, transactionDetails, charges);
    }


}
