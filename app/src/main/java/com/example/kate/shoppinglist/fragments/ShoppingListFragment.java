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
import com.example.kate.shoppinglist.RefreshList;
import com.example.kate.shoppinglist.SQLite.DBShopList;

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

        Cursor c = App.getDB().getCursorForLinkedDBs();

        String[] from = {DBShopList.TableItems.C_NAME,
                DBShopList.TableItems.C_QUANTITY,
                DBShopList.TableCategories.C_CATEGORY};

        int[] to = {R.id.textItem, R.id.textCount, R.id.textCategory};

        adapter = new SimpleCursorAdapter(this.getContext(),
                R.layout.view_shop_list,c, from, to, 1);

        adapter.notifyDataSetChanged();

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
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
        adapter.changeCursor(App.getDB().getCursorForLinkedDBs());
    }
}
