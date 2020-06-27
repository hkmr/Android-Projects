package com.example.harshkumar.bookshelf;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.harshkumar.bookshelf.data.BookContract;
import com.example.harshkumar.bookshelf.data.BookDbHelper;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int BOOK_LOADER = 0;
    BookCursorAdapter mBookCursorAdapter;
    BookDbHelper mDbHelper;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                startActivity(intent);
            }
        });

        ListView bookListView =  findViewById(R.id.book_list);

        View emptyView = findViewById(R.id.empty_view);
        bookListView.setEmptyView(emptyView);

        mBookCursorAdapter = new BookCursorAdapter(this,null);
        bookListView.setAdapter(mBookCursorAdapter);

        getLoaderManager().initLoader(BOOK_LOADER,null,this);
    }

    private void insertBook(){

        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.BOOK_NAME,"Zero to One");
        values.put(BookContract.BookEntry.BOOK_PRICE,125.6);
        values.put(BookContract.BookEntry.BOOK_QUANTITY,5);
        values.put(BookContract.BookEntry.BOOK_SUPPLIER_NAME,"Harsh");
        values.put(BookContract.BookEntry.BOOK_SUPPLIER_PHONE,98765);

        Uri newUri = getContentResolver().insert(BookContract.BookEntry.CONTENT_URI,values);

    }

    private void emptyTable(){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ BookContract.BookEntry.TABLE_NAME);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_catalog,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_insert_dummy_data:
                insertBook();
                return true;

            case R.id.action_delete_all_entries:
                emptyTable();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {

        String[] projection = {
                BookContract.BookEntry._ID,
                BookContract.BookEntry.BOOK_NAME,
                BookContract.BookEntry.BOOK_PRICE,
                BookContract.BookEntry.BOOK_QUANTITY,
                BookContract.BookEntry.BOOK_SUPPLIER_NAME
        };

        return new CursorLoader(this, BookContract.BookEntry.CONTENT_URI,
                projection,null,null,null);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mBookCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mBookCursorAdapter.swapCursor(null);
    }
}
