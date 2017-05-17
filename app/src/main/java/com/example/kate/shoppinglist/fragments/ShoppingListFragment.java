package com.example.kate.shoppinglist.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;

import com.example.kate.shoppinglist.App;
import com.example.kate.shoppinglist.R;
import com.example.kate.shoppinglist.interfaces.RefreshList;
import com.example.kate.shoppinglist.sqlite.DBShopList;
import com.example.kate.shoppinglist.sqlite.DBShopListProvider;

/**
 * Created by Kate on 09.05.2017.
 */

public class ShoppingListFragment extends ListFragment implements RefreshList {

    public static final String TAG = ListFragment.class.getSimpleName();
    SimpleCursorAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), getResources()
//                .getStringArray(R.array.list_test), getResources()
//                .getStringArray(R.array.count_test));
//        setListAdapter(listViewAdapter);

        Cursor c = getContext().getContentResolver().query(DBShopListProvider.CONTENT_URI_ITEMS,
                null, null, null, null);

        String[] from = {DBShopList.TableItems.C_NAME,
                DBShopList.TableItems.C_QUANTITY,
                DBShopList.TableCategories.C_CATEGORY};

        int[] to = {R.id.textItem, R.id.textCount, R.id.textCategory};

        adapter = new SimpleCursorAdapter(this.getContext(),
                R.layout.view_shop_list,c, from, to, 1);

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        App.getDB().deleteItem(id);
        refresh();
    }


    public static ShoppingListFragment getInstance() {
        return new ShoppingListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void refresh() {

        Cursor c = getContext().getContentResolver().query(DBShopListProvider.CONTENT_URI_ITEMS,
                null, null, null, null);
        adapter.changeCursor(c);
    }
}
