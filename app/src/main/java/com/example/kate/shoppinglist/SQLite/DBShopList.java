package com.example.kate.shoppinglist.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.kate.shoppinglist.App;
import com.example.kate.shoppinglist.R;

/**
 * Created by Kate on 13.05.2017.
 */

public class DBShopList extends DBSQLite {

    private static final String SQL_WHERE_BY_ID = BaseColumns._ID + "=?";
    private static final String DB_NAME = "DBShopList.db";
    private static final int DB_VERSION = 2;

    public DBShopList(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        DBSQLite.execSQL(db, TableItems.SQL_CREATE);
        DBSQLite.execSQL(db, TableCategories.SQL_CREATE);

        String[] categories = getContext().getResources().getStringArray(
                R.array.category_test);
        ContentValues values = new ContentValues(categories.length);

        for (int i = 0; i < categories.length; i++) {

            values.put(TableCategories.C_CATEGORY, categories[i]);

            db.insert(TableCategories.T_NAME, null, values);
        }

        String[] items = getContext().getResources().getStringArray(
                R  .array.list_test);
        ContentValues contentValues = new ContentValues(items.length);

        for (int i = 0; i < items.length; i++) {

            String[] item = items[i].split("-");

            contentValues.put(TableItems.C_NAME, item[0]);
            contentValues.put(TableItems.C_QUANTITY, item[1]);
            contentValues.put(TableItems.C_CATEGORY_ID, item[2]);

            db.insert(TableItems.T_NAME, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        DBSQLite.dropTable(db, TableItems.T_NAME);
        DBSQLite.dropTable(db, TableCategories.T_NAME);

        this.onCreate(db);
    }

    public Cursor getCursorForLinkedDBs(){

        String SQLQuery = "SELECT " +
                TableItems.T_NAME +  "." + TableItems._ID + ", " +
                TableItems.T_NAME +  "." + TableItems.C_NAME + ", " +
                TableItems.C_QUANTITY + ", " +
                TableItems.C_CATEGORY_ID + ", " +
                TableCategories.C_CATEGORY +
                " FROM " +
                TableItems.T_NAME +
                " INNER JOIN " + TableCategories.T_NAME +
                " ON " + TableItems.T_NAME +  "." + TableItems.C_CATEGORY_ID+ " = " +
                TableCategories.T_NAME + "." + TableCategories._ID;

        return App.getDB().getReadableDatabase().rawQuery(SQLQuery, null);
    }

    public long addCategory(String name) {

        ContentValues v = new ContentValues();

        v.put(TableCategories.C_CATEGORY, name);

        return this.getWritableDatabase().insert(TableCategories.T_NAME, null, v);

    }

    public boolean updateCategory(String name, long id) {

        ContentValues v = new ContentValues();

        v.put(TableCategories.C_CATEGORY, name);

        return 1 == this.getWritableDatabase().update(TableCategories.T_NAME, v,
                SQL_WHERE_BY_ID, new String[] {String.valueOf(id)});
    }

    public boolean deleteCategory(long id) {
        return 1 == this.getWritableDatabase().delete(
                TableCategories.T_NAME, SQL_WHERE_BY_ID,
                new String[] {String.valueOf(id)});
    }

    public boolean findCategoryByName(String name){
        Cursor c = this.getReadableDatabase().rawQuery("SELECT " + TableCategories._ID +
                " FROM " + TableCategories.T_NAME +
                " WHERE " + TableCategories.C_CATEGORY + " LIKE '" + name + "'", null);
        if(!c.moveToFirst()){return false;}
        c.close();
        return true;

    }

    public long getCategoryIdByName(String name){
        Cursor c = this.getReadableDatabase().rawQuery("SELECT " + TableCategories._ID +
                " FROM " + TableCategories.T_NAME +
                " WHERE " + TableCategories.C_CATEGORY + " LIKE '" + name + "'", null);

        if(!c.moveToFirst()){return 0;}
        long i = c.getLong(c.getColumnIndex(TableCategories._ID));
        c.close();
        return i;
    }

    public long addItem(String name, int quantity, long category_id) {

        ContentValues v = new ContentValues();

        v.put(TableItems.C_NAME, name);
        v.put(TableItems.C_QUANTITY, quantity);
        v.put(TableItems.C_CATEGORY_ID, category_id);

        return this.getWritableDatabase().insert(TableItems.T_NAME, null, v);

    }

    public boolean updateItem(String name, int quantity, long category_id, long id) {

        ContentValues v = new ContentValues();

        v.put(TableItems.C_NAME, name);
        v.put(TableItems.C_QUANTITY, quantity);
        v.put(TableItems.C_CATEGORY_ID, category_id);

        return 1 == this.getWritableDatabase().update(TableItems.T_NAME, v,
                SQL_WHERE_BY_ID, new String[] {String.valueOf(id)});
    }

    public boolean deleteItem(long id) {
        return 1 == this.getWritableDatabase().delete(
                TableItems.T_NAME, SQL_WHERE_BY_ID,
                new String[] {String.valueOf(id)});
    }

    public static class TableItems implements BaseColumns {

        public static final String T_NAME = "tItems";
        public static final String C_NAME = "name";
        public static final String C_QUANTITY = "quantity";
        public static final String C_CATEGORY_ID  = "category_id";

        public static final String SQL_CREATE = "CREATE TABLE " + T_NAME +
                " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                C_NAME + " TEXT," +
                C_QUANTITY + " INTEGER," +
                C_CATEGORY_ID + " INTEGER," +
                "FOREIGN KEY ("+C_CATEGORY_ID+") REFERENCES "+ TableCategories.T_NAME+" ("+ TableCategories._ID+")"
                + ")";
    }

    public static class TableCategories implements BaseColumns {

        public static final String T_NAME = "tCategory";
        public static final String C_CATEGORY = "category";

        public static final String SQL_CREATE = "CREATE TABLE " + T_NAME +
                " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                C_CATEGORY + " TEXT)";
    }
}
