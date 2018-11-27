package com.example.bhanuka.micro_bankingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import android.database.Cursor;

import static android.widget.Toast.makeText;





public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText edit_account_number, edit_amount, edit_details;
    CheckBox diposit, withdraw;
    Button submit, viewdata, viewAccounts,delete,refresh;
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
        submit = (Button) findViewById(R.id.button_submit);
        delete = (Button) findViewById(R.id.button_delete);
        viewdata = (Button) findViewById(R.id.button_viewdata);
        viewAccounts = (Button) findViewById(R.id.button_accounts);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup_type);

        AddData();
        ViewData();
        ViewAccounts();
        DeleteData();

        GetFromAccounts();


        //AddToAccounts("160137", "962232531v","children", "ok","5000.00","good");

        myDB.insertToMinimumBalance("children","0");
        myDB.insertToMinimumBalance("teen","500");
        myDB.insertToMinimumBalance("adult","1000");
        myDB.insertToMinimumBalance("senior","1000");
        myDB.insertToMinimumBalance("joint","5000");
        //AddToAccounts();

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
                         radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                         String type = (String) radioButton.getText();
                         String account_number = edit_account_number.getText().toString();
                         String amount = edit_amount.getText().toString();
                         String details = edit_details.getText().toString();
                         String agent_id = "00001"; //hardcoded agent_id
                         String charges =  CalculateCharges(account_number,amount);
                        if (charges.equals("0")){
                            if (myDB.UpdateCurrentBalance(account_number,amount,type)){
                                boolean isInserted = myDB.insertToTransactions(account_number, type, date, time, amount, details, charges);
                                if (isInserted = true){
                                    makeText(MainActivity.this, "Data inserted "+(String.valueOf(myDB.CountTransactions())), Toast.LENGTH_LONG).show();
                                }
                                else {
                                    makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                                }

                                if (myDB.CountTransactions() >= 5){
                                    Cursor result = myDB.getAllData();
                                    while (result.moveToNext()){
                                        SendToTransactions(result.getString(1),agent_id, result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
                                    }
                                    myDB.DeleteAll("transactions");
                                    //GetFromAccounts();
                                }
                            }
                            else{
                                makeText(MainActivity.this, "Account balance is not enough", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            SendToTransactions(account_number,agent_id, type, time, date, amount, details, charges);
                        }
                    }

    }
        );
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
                            buffer.append("Type : " + result.getString(1) + "\n");
                            buffer.append("Status :" + result.getString(2) + "\n");
                            buffer.append("Current balance :" + result.getString(3) + "\n");
                            buffer.append("Account details :" + result.getString(4) + "\n\n");
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

    public void SendToTransactions(String account_number,String agent_id, String transactionType, String date, String time, String amount, String transactionDetails, String charges){
        String type = "sendToTransactions";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, account_number, agent_id, transactionType, date, time, amount, transactionDetails, charges);
    }

    public void GetFromAccounts(){
        String type = "getFromAccounts";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        String data = String.valueOf(backgroundWorker.execute(type));
        System.out.println("from mainactivity: "+data);

    }

    public String CalculateCharges(String account_number, String amount){
        if (myDB.CheckAccountNumber(account_number)){
            return String.valueOf(Integer.valueOf(amount)*0.01);
        }
        return String.valueOf(0);
    }
}
