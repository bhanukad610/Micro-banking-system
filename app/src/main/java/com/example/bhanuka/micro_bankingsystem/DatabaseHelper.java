package com.example.bhanuka.micro_bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mobileDB";
    public static final String ACCOUNTS_TABLE = "accounts";
    public static final String TRANSACTIONS_TABLE = "transactions";

    public static final String COL_1 = "account_number";
    public static final String COL_2 = "customer_NIC";
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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ACCOUNTS_TABLE + "(" +
                "account_number INTEGER PRIMARY KEY, " +
                "customer_NIC TEXT, " +
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ACCOUNTS_TABLE );
        db.execSQL("drop table if exists " + TRANSACTIONS_TABLE );
        onCreate(db);
    }

    public boolean insertToAccounts(String account_number, String customer_NIC, String account_Type, String status, String current_balance, String account_details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, account_number);
        contentValues.put(COL_2, customer_NIC);
        contentValues.put(COL_3, account_Type);
        contentValues.put(COL_4, status);
        contentValues.put(COL_5, current_balance);
        contentValues.put(COL_6, account_details);
        long isInsert = db.insert(ACCOUNTS_TABLE, null, contentValues);
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

    public Cursor CountTransactions(){
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT count(transaction_id) FROM " + TRANSACTIONS_TABLE;
        Cursor mCursor = db.rawQuery(q, null);
        return mCursor;
    }

    public long getTransactionsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TRANSACTIONS_TABLE);
        db.close();
        return count;
    }


}
