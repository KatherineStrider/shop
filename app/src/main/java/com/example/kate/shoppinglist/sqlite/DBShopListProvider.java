package com.example.kate.shoppinglist.sqlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Kate on 17.05.2017.
 */

public class DBShopListProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.kate.shoppinglist.sqlite.DBShopList";

    private static final UriMatcher uriMatcher;

    private static final int DB_ITEM = 1;
    private static final int DB_ITEMS = 2;

    private static final int DB_CATEGORY = 3;
    private static final int DB_CATEGORIES = 4;

    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "items", DB_ITEMS);
        uriMatcher.addURI(AUTHORITY, "item/#", DB_ITEM);

        uriMatcher.addURI(AUTHORITY, "categories", DB_CATEGORIES);
        uriMatcher.addURI(AUTHORITY, "category/#", DB_CATEGORY);

    }

    private DBShopList dbShop = null;

    public static final Uri CONTENT_URI_ITEMS = Uri.parse("content://" +
            AUTHORITY + "/items");

    public static final Uri CONTENT_URI_ITEM = Uri.parse("content://" +
            AUTHORITY + "/item/#");

    public static final Uri CONTENT_URI_CATEGORIES = Uri.parse("content://" +
            AUTHORITY + "/categories");

    public static final Uri CONTENT_URI_CATEGORY = Uri.parse("content://" +
            AUTHORITY + "/category/#");

    @Override
    public boolean onCreate() {

        dbShop = new DBShopList(this.getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor c = dbShop.getCursorForLinkedDBs();

//        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        if (uri != null)
            return "text/plain";
        else
            return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        Uri resultUri = null;

        if (uriMatcher.match(uri) == DB_ITEMS){

            long l = dbShop.getCategoryIdByName(values.get(DBShopList.TableItems.C_CATEGORY_ID).toString());

            values.put(DBShopList.TableItems.C_CATEGORY_ID, l);

            long id = dbShop.addItem(values);

            if (id > 0) {
                Uri itemUri = ContentUris.withAppendedId(CONTENT_URI_ITEM, id);
//                getContext().getContentResolver().notifyChange(itemUri, null);
                resultUri =  itemUri;
            }

        }

        else if(uriMatcher.match(uri) == DB_CATEGORIES){

            if (!dbShop.findCategoryByName(values.get(DBShopList.TableCategories.C_CATEGORY).toString())) {
                long id = dbShop.addCategory(values);

                if (id > 0) {
                    Uri itemUri = ContentUris.withAppendedId(CONTENT_URI_CATEGORY, id);
//                    getContext().getContentResolver().notifyChange(itemUri, null);
                    resultUri =  itemUri;
                }
            }
        }

        else {
            throw new IllegalArgumentException("Unknow URI " + uri);
        }
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
