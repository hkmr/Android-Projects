package com.example.harshkumar.bookshelf;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.harshkumar.bookshelf.data.BookContract;

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context,Cursor cursor){
        super(context,cursor,0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.book_name);
        TextView supplierName = (TextView) view.findViewById(R.id.book_supplier_name);
        TextView price = (TextView) view.findViewById(R.id.book_price);
        TextView quantity = (TextView) view.findViewById(R.id.book_quantity);

        String bookName = cursor.getString(cursor.getColumnIndexOrThrow(BookContract.BookEntry.BOOK_NAME));
        String bookSupplierName = cursor.getString(cursor.getColumnIndexOrThrow(BookContract.BookEntry.BOOK_SUPPLIER_NAME));
        String bookPrice = cursor.getString(cursor.getColumnIndexOrThrow(BookContract.BookEntry.BOOK_PRICE));
        String bookQuantity = cursor.getString(cursor.getColumnIndexOrThrow(BookContract.BookEntry.BOOK_QUANTITY));

        name.setText(bookName);
        supplierName.setText(bookSupplierName);
        price.setText(bookPrice);
        quantity.setText(bookQuantity);

    }
}
