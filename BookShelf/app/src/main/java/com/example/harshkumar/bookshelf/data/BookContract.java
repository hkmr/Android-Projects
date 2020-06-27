package com.example.harshkumar.bookshelf.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class BookContract {

    private BookContract(){}

    public final static String CONTENT_AUTHORITY = "com.example.harshkumar.bookshelf";

    public final static Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public final static String PATH_BOOKS = "books";

    public static final class BookEntry implements BaseColumns{

        public final static String TABLE_NAME = "books";

        public final static String _ID = BaseColumns._ID;
        public final static String BOOK_NAME = "name";
        public final static String BOOK_PRICE = "price";
        public final static String BOOK_QUANTITY = "quantity";
        public final static String BOOK_SUPPLIER_NAME = "supplier_name";
        public final static String BOOK_SUPPLIER_PHONE = "supplier_phone";

        public final static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_BOOKS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+CONTENT_AUTHORITY+"/"+PATH_BOOKS;

        public static final String CONTENT_ITEM_TPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE+"/"+CONTENT_AUTHORITY+"/"+PATH_BOOKS;

    }

}
