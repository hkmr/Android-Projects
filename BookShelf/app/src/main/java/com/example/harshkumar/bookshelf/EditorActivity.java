package com.example.harshkumar.bookshelf;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.harshkumar.bookshelf.data.BookContract;
import com.example.harshkumar.bookshelf.data.BookDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mQuantityEditText;
    private EditText mSupplierNameEditText;
    private EditText mSupplierPhoneEditText;

    private BookDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mNameEditText = findViewById(R.id.book_name);
        mPriceEditText = findViewById(R.id.book_price);
        mQuantityEditText = findViewById(R.id.book_quantity);
        mSupplierNameEditText = findViewById(R.id.suppier_name);
        mSupplierPhoneEditText = findViewById(R.id.supplier_number);

    }

    private void insertBook(){

        String bookName = mNameEditText.getText().toString().trim();
        float bookPrice = Float.parseFloat(mPriceEditText.getText().toString());
        int bookQuantity = Integer.parseInt(mQuantityEditText.getText().toString());
        String supplierName = mSupplierNameEditText.getText().toString().trim();
        int supplierPhone = Integer.parseInt(mSupplierPhoneEditText.getText().toString());

        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.BOOK_NAME,bookName);
        values.put(BookContract.BookEntry.BOOK_PRICE,bookPrice);
        values.put(BookContract.BookEntry.BOOK_QUANTITY,bookQuantity);
        values.put(BookContract.BookEntry.BOOK_SUPPLIER_NAME,supplierName);
        values.put(BookContract.BookEntry.BOOK_SUPPLIER_PHONE,supplierPhone);

        Uri newUri = getContentResolver().insert(BookContract.BookEntry.CONTENT_URI,values);

        if(newUri == null){
            Toast.makeText(this,getString(R.string.editor_insert_book_failed),Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,getString(R.string.editor_insert_book_success),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_save:
                insertBook();
                finish();
                return true;

            case R.id.action_delete:
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }
}
