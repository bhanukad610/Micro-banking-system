package com.example.bhanuka.micro_bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mobileDB";
    public static final String ACCOUNTS_TABLE = "accounts";
    public static final String TRANSACTIONS_TABLE = "transactions";
    public static final String MINIMUM_BALANCE = "minimum_balance";

    public static final String COL_1 = "account_number";
    public static final String COL_3 = "account_Type";
    public static final String COL_4 = "status";
    public static final String COL_5 = "current_balance";
    public static final String COL_6 = "account_details";

    public static final String COL_7 = "transaction_id";
    public static final String COL_8 = "type";
    public static final String COL_9 = "transaction_date";
    public static final String COL_10 = "time";
    public static final String COL_11 = "transaction_amount";
    public static final String COL_12 = "transaction_details";
    public static final String COL_13 = "transaction_chargers";

    public static final String COL_14 = "account_type";
    public static final String COL_15 = "amount";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ACCOUNTS_TABLE + "(" +
                "account_number INTEGER PRIMARY KEY, " +
                "account_type TEXT, " +
                "status TEXT, " +
                "current_balance DECIMAL, " +
                "account_details TEXT)"
        );

        db.execSQL("create table " + TRANSACTIONS_TABLE + "(" +
                "transaction_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "account_number INTEGER REFERENCES accounts(account_number) NOT NULL, " +
                "type TEXT NOT NULL, " +
                "transaction_date TEXT NOT NULL, " +
                "time TEXT NOT NULL, " +
                "transaction_amount INTEGER, " +
                "transaction_details TEXT, " +
                "transaction_chargers INTEGER" +
                ")");

        db.execSQL("create table " + MINIMUM_BALANCE + "(" +
                "account_type TEXT PRIMARY KEY NOT NULL REFERENCES accounts(account_type)," +
                "amount INTEGER NOT NULL)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ACCOUNTS_TABLE );
        db.execSQL("drop table if exists " + TRANSACTIONS_TABLE );
        db.execSQL("drop table if exists " + MINIMUM_BALANCE );
        onCreate(db);
    }

    public boolean insertToAccounts(String data) {
        System.out.println("from databasehelper :"+data);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try {
            JSONObject jsonObj = new JSONObject(data);
            JSONArray contacts = jsonObj.getJSONArray("accounts");
            System.out.println("from databasehelper :"+contacts);

            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);

                String account_number = c.getString("accountNumber");

                System.out.println("from databasehelper :"+account_number);
                String accountType = c.getString("accountType");
                String status = c.getString("status");
                String currentBalance = c.getString("currentBalance");
                String accountDetails = c.getString("accountDetails");

                contentValues.put(COL_1, account_number);
                contentValues.put(COL_3, accountType);
                contentValues.put(COL_4, status);
                contentValues.put(COL_5, currentBalance);
                contentValues.put(COL_6, accountDetails);
                long isInsert = db.insert(ACCOUNTS_TABLE, null, contentValues);
                return isInsert != -1;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return false;
    }

    public boolean insertToMinimumBalance(String account_type, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_14, account_type);
        contentValues.put(COL_15, amount);
        long isInsert = db.insert(MINIMUM_BALANCE, null, contentValues);
        return isInsert != -1;
    }


    public boolean insertToTransactions(String account_number, String type, String time, String date, String amount, String details, String charges){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, account_number);
        contentValues.put(COL_8, type);
        contentValues.put(COL_9, date);
        contentValues.put(COL_10, time);
        contentValues.put(COL_11, amount);
        contentValues.put(COL_12, details);
        contentValues.put(COL_13, charges);
        long isInsert = db.insert(TRANSACTIONS_TABLE, null, contentValues);
        return isInsert != -1;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM " + TRANSACTIONS_TABLE;
        Cursor mCursor = db.rawQuery(q, null);
        return mCursor;
    }

    public Cursor getAccounts() {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM " + ACCOUNTS_TABLE;
        Cursor mCursor = db.rawQuery(q, null);
        return mCursor;
    }

    public void DeleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from transactions where transaction_id = (select max(transaction_id) from transactions)");
    }

    public boolean CheckAccountNumber(String account_number) {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "Select * from " + ACCOUNTS_TABLE + " where " + COL_1 + " = " + account_number;
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean UpdateCurrentBalance(String account_number, String amount, String type){
        float value = Float.valueOf(amount);
        SQLiteDatabase db = this.getWritableDatabase();
        String get_current_balance = "select current_balance ,amount from accounts natural join minimum_balance where account_number = " + account_number;
        Cursor cursor = db.rawQuery(get_current_balance, null);

        while (cursor.moveToNext()){
            float current_balance = Float.valueOf(cursor.getString(0));
            float minimum_balance = Float.valueOf(cursor.getString(1));
            if (type.equals("deposit")){
                current_balance = current_balance + value;
                if (current_balance >= minimum_balance){
                String query = "UPDATE " + ACCOUNTS_TABLE + " SET " + COL_5 + "=" + current_balance + " WHERE account_number = "+account_number;
                db.execSQL(query);
                return true;
                }
            }
            if (type.equals("withdraw")){
                current_balance = current_balance - value;
                if (current_balance >= minimum_balance){
                    String query = "UPDATE " + ACCOUNTS_TABLE + " SET " + COL_5 + "=" + current_balance + " WHERE account_number = "+account_number;
                    db.execSQL(query);
                    return true;
                }
                else{
                    return false;
                }
            }
        }

        return false;
    }

    public int CountTransactions() {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT count(*) FROM " + TRANSACTIONS_TABLE;
        Cursor cursor = db.rawQuery(q, null);
        int count = 0;
        if(null != cursor){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
        cursor.close();
        }

    db.close();
    return count;
    }

    public void DeleteAll(String table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + table_name;
        db.execSQL(query);
    }

    public Cursor getMinimumBalance(String account_number){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select current_balance ,amount from accounts natural join minimum_balance where account_number = " + account_number;
        Cursor mCursor = db.rawQuery(query, null);
        return mCursor;
    }

}
