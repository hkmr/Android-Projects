package com.example.android.pets.data;

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

public class PetProvider extends ContentProvider {

    public static final String LOG_TAG = PetProvider.class.getSimpleName();
    private static final int PETS = 100;
    private static final int PET_ID = 101;
    PetDbHelper mDbHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY,PetContract.PATH_PETS,PETS);
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY,PetContract.PATH_PETS+"/#",PET_ID);
    }

    @Override
    public boolean onCreate() {

        mDbHelper = new PetDbHelper(getContext());

        return false;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        final int match = sUriMatcher.match(uri);
        switch (match){
            case PETS:
                return insertPet(uri,contentValues);
                default:
                    throw new IllegalArgumentException("Insertion not supported for "+uri);
        }
    }

    private Uri insertPet(Uri uri, ContentValues values){

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

//        validating name
        String name = values.getAsString(PetContract.PetEntry.COLUMN_PET_NAME);
        if(name == null){
            throw new IllegalArgumentException("Pet requires a name");
        }

//        validating gender
        Integer gender =  values.getAsInteger(PetContract.PetEntry.COLUMN_PET_GENDER);
        if(gender == null || !PetContract.PetEntry.isValidGender(gender)){
            throw new IllegalArgumentException("Pet required valid gender");
        }

        Integer weight = values.getAsInteger(PetContract.PetEntry.COLUMN_PET_WEIGHT);
        if(weight != null && weight < 0){
            throw new IllegalArgumentException("Pet require valid weight");
        }

        long id = database.insert(PetContract.PetEntry.TABLE_NAME,null,values);
        if(id == -1){
            Log.e(LOG_TAG,"Failed to insert row for"+uri);
            return null;
        }

        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues,String selection,String[] selectionArgs) {

        int match = sUriMatcher.match(uri);
        switch (match){
            case PETS:
                return updatePet(uri,contentValues,selection,selectionArgs);

            case PET_ID:

                selection = PetContract.PetEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                return updatePet(uri,contentValues,selection,selectionArgs);

                default: throw new IllegalArgumentException("Update is not supported for "+uri);
        }
    }

    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs){

        if(values.containsKey(PetContract.PetEntry.COLUMN_PET_NAME)){

            String name = values.getAsString(PetContract.PetEntry.COLUMN_PET_NAME);
            if(name == null){
                throw new IllegalArgumentException("Pet require a name");
            }
        }

        if(values.containsKey(PetContract.PetEntry.COLUMN_PET_GENDER)){
            Integer gender = values.getAsInteger(PetContract.PetEntry.COLUMN_PET_GENDER);
            if(gender == null || !PetContract.PetEntry.isValidGender(gender)){
                throw new IllegalArgumentException("Pet require a valid gender");
            }
        }

        if(values.containsKey(PetContract.PetEntry.COLUMN_PET_WEIGHT)){
            Integer weight = values.getAsInteger(PetContract.PetEntry.COLUMN_PET_WEIGHT);
            if(weight != null && weight < 0){
                throw new IllegalArgumentException("Pet require valid weight");
            }
        }

        if(values.size() == 0)
            return 0;

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        return database.update(PetContract.PetEntry.TABLE_NAME, values,selection,selectionArgs);
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match){
            case PETS:
                return database.delete(PetContract.PetEntry.TABLE_NAME,selection,selectionArgs);

            case PET_ID:
                selection = PetContract.PetEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                return database.delete(PetContract.PetEntry.TABLE_NAME,selection,selectionArgs);

                default:
                    throw new IllegalArgumentException("Delete is not supported for "+uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        final int match = sUriMatcher.match(uri);
        switch (match){
            case PETS:
                return PetContract.PetEntry.CONTENT_LIST_TYPE;
            case PET_ID:
                return PetContract.PetEntry.CONTENT_ITEM_TYPE;
                default:
                    throw new IllegalArgumentException("Unkown URI "+uri+" with match "+match);
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);

        switch(match){
            case PETS:

                cursor = database.query(PetContract.PetEntry.TABLE_NAME,projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;

            case PET_ID:
                selection = PetContract.PetEntry._ID+ "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(PetContract.PetEntry.TABLE_NAME,projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;

                default:
                    throw new IllegalArgumentException("Cannot query unknown URI"+uri);

        }

        return cursor;


    }
}
