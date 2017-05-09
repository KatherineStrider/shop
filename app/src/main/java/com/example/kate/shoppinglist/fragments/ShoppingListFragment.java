package com.example.kate.shoppinglist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.kate.shoppinglist.ListViewAdapter;
import com.example.kate.shoppinglist.R;

/**
 * Created by Kate on 09.05.2017.
 */

public class ShoppingListFragment extends ListFragment {

    public static final String TAG = ListFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), getResources()
                .getStringArray(R.array.list_test), getResources()
                .getStringArray(R.array.count_test));
        setListAdapter(listViewAdapter);
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
}
