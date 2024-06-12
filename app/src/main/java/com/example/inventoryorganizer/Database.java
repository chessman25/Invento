package com.example.inventoryorganizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "InventoryOrganizer";
    private static final int DATABASE_VERSION = 1;

    //USER_ACCOUNTS
    private static final String USER_TABLE_NAME = "USER_ACCOUNTS";
    private static final String USER_COL_1 = "EMAIL";
    private static final String USER_COL_2 = "USERNAME";
    private static final String USER_COL_3 = "PASSWORD";

    //PRODUCTS
    protected static final String PRODUCT_TABLE_NAME = "PRODUCTS";
    protected static final String PRODUCT_COL_1 = "PRODUCT_ID";
    protected static final String PRODUCT_COL_2 = "PRODUCT_NAME";
    protected static final String PRODUCT_COL_3 = "CATEGORY";
    protected static final String PRODUCT_COL_4 = "PRICE";
    protected static final String PRODUCT_COL_5 = "QUANTITY";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //USER_ACCOUNTS TABLE
        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + " (" + USER_COL_1 + " TEXT PRIMARY KEY, " + USER_COL_2 + " TEXT, " + USER_COL_3 + " TEXT)");

        //PRODUCTS_TABLE
        db.execSQL("CREATE TABLE IF NOT EXISTS " + PRODUCT_TABLE_NAME + " (" + PRODUCT_COL_1 + " TEXT PRIMARY KEY, " + PRODUCT_COL_2 + " TEXT, " + PRODUCT_COL_3 + " TEXT, " + PRODUCT_COL_4 + " REAL, " + PRODUCT_COL_5 + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        onCreate(db);
    }

    //USER_ACCOUNTS METHODS
    public boolean insertUser(String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_1, email);
        contentValues.put(USER_COL_2, username);
        contentValues.put(USER_COL_3, password);
        long result = db.insert(USER_TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean validateUserLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_COL_1 + " = ? AND " + USER_COL_3 + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }

    //PRODUCT METHODS
    public boolean insertProduct(String productID, String productName, String category, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_COL_1, productID);
        contentValues.put(PRODUCT_COL_2, productName);
        contentValues.put(PRODUCT_COL_3, category);
        contentValues.put(PRODUCT_COL_4, price);
        contentValues.put(PRODUCT_COL_5, quantity);
        long result = db.insert(PRODUCT_TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean updateProduct(String id, String name, String category, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_COL_1, id);
        contentValues.put(PRODUCT_COL_2, name);
        contentValues.put(PRODUCT_COL_3, category);
        contentValues.put(PRODUCT_COL_4, price);
        contentValues.put(PRODUCT_COL_5, quantity);
        int rowsAffected = db.update(PRODUCT_TABLE_NAME, contentValues, "PRODUCT_ID = ?", new String[]{id});
        return rowsAffected > 0;
    }

    public Integer deleteProduct(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PRODUCT_TABLE_NAME, "PRODUCT_ID = ?", new String[]{id});
    }
    public boolean isUserExists(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_COL_2 + "=?";
            cursor = db.rawQuery(query, new String[]{username});

            return cursor.getCount() > 0;
        }finally {
            if (cursor != null){
                cursor.close();
            }
            db.close();
        }
    }
    public Cursor getUserDetails(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + USER_COL_2 + ", " + USER_COL_1 + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COL_1 + " = ?";
        return db.rawQuery(query, new String[]{email});
    }
}
