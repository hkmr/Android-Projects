package com.example.harshkumar.bookshelf.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class BookProvider extends ContentProvider {

    private static final String LOG_TAG = BookProvider.class.getSimpleName();
    private static final int BOOKS = 100;
    private static final int BOOK_ID = 101;
    private BookDbHelper mDbHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{

        sUriMatcher.addURI(BookContract.CONTENT_AUTHORITY,BookContract.PATH_BOOKS,BOOKS);

        sUriMatcher.addURI(BookContract.CONTENT_AUTHORITY,BookContract.PATH_BOOKS+"/#",BOOK_ID);

    }

    @Override
    public boolean onCreate() {
        mDbHelper = new BookDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match){
            case BOOKS:
                cursor = database.query(BookContract.BookEntry.TABLE_NAME,projection,
                        selection,selectionArgs,null,null,sortOrder);
                break;

            case BOOK_ID:
                selection = BookContract.BookEntry._ID+"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(BookContract.BookEntry.TABLE_NAME,projection,
                        selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unkown URI: "+uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        int match = sUriMatcher.match(uri);
        switch (match){
            case BOOKS:
                return BookContract.BookEntry.CONTENT_LIST_TYPE;
            case BOOK_ID:
                return BookContract.BookEntry.CONTENT_ITEM_TPE;
            default:
                throw new IllegalArgumentException("Unkown uri "+uri+" with match "+match);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        int match = sUriMatcher.match(uri);
        switch (match){
            case BOOKS:
                return insertBook(uri,contentValues);
            default:
                throw new IllegalArgumentException("Insertion not supported for uri: "+uri);
        }

    }

    private Uri insertBook(Uri uri,ContentValues contentValues){

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

//        validation
        String bookName = contentValues.getAsString(BookContract.BookEntry.BOOK_NAME);
        Float bookPrice = contentValues.getAsFloat(BookContract.BookEntry.BOOK_PRICE);
        Integer quantity = contentValues.getAsInteger(BookContract.BookEntry.BOOK_QUANTITY);

        if(bookName == null){
            throw new IllegalArgumentException("Book name required");
        }
        if(bookPrice == null || (bookPrice != null && bookPrice < 0)){
            throw new IllegalArgumentException("Book required valid price");
        }
        if(quantity == null || (quantity != null && quantity <0)){
            throw new IllegalArgumentException("Book require valid quantity");
        }

        long id = database.insert(BookContract.BookEntry.TABLE_NAME,null,contentValues);

        if(id == -1){
            Log.e(LOG_TAG,"Failed to insert row for "+uri);
            return null;
        }
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        switch (match){
            case BOOKS:
                return database.delete(BookContract.BookEntry.TABLE_NAME,selection,selectionArgs);
            case BOOK_ID:
                selection = BookContract.BookEntry._ID+"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                return database.delete(BookContract.BookEntry.TABLE_NAME,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for uri: "+uri);
        }

    }

    @Override
    public int update( Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        int match = sUriMatcher.match(uri);
        switch (match){
            case BOOKS:
                return updateBook(uri,contentValues,selection,selectionArgs);
            case BOOK_ID:

                selection = BookContract.BookEntry._ID+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                return updateBook(uri,contentValues,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for uri: "+uri);
        }

    }

    private int updateBook(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs){

        if(contentValues.containsKey(BookContract.BookEntry.BOOK_NAME)){
            String name = contentValues.getAsString(BookContract.BookEntry.BOOK_NAME);
            if(name == null)
                throw new IllegalArgumentException("Book is required");
        }

        if(contentValues.containsKey(BookContract.BookEntry.BOOK_QUANTITY)){
            Integer quantity = contentValues.getAsInteger(BookContract.BookEntry.BOOK_QUANTITY);
            if(quantity == null || (quantity!=null && quantity < 0))
                throw new IllegalArgumentException("Book require valid quantity");
        }

        if(contentValues.containsKey(BookContract.BookEntry.BOOK_PRICE)){
            Float price = contentValues.getAsFloat(BookContract.BookEntry.BOOK_PRICE);
            if(price == null || (price != null && price <0))
                throw new IllegalArgumentException("Book require valid price");
        }

        if(contentValues.size() == 0){
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        return database.update(BookContract.BookEntry.TABLE_NAME,contentValues,selection,selectionArgs);
    }
}
