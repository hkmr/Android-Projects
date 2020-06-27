package com.example.harshkumar.bookshelf.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "bookshelf.db";

    private final static int DATABASE_VERSION = 1;

    public BookDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREAT_BOOK_TABLE = "CREATE TABLE " +BookContract.BookEntry.TABLE_NAME+"("+
                BookContract.BookEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                BookContract.BookEntry.BOOK_NAME+ " TEXT NOT NULL, "+
                BookContract.BookEntry.BOOK_PRICE+" FLOAT NOT NULL,"+
                BookContract.BookEntry.BOOK_QUANTITY+" INTEGER DEFAULT 0,"+
                BookContract.BookEntry.BOOK_SUPPLIER_NAME+" TEXT,"+
                BookContract.BookEntry.BOOK_SUPPLIER_PHONE+");";

        db.execSQL(SQL_CREAT_BOOK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
