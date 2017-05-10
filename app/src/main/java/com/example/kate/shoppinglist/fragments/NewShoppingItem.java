package com.example.kate.shoppinglist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kate.shoppinglist.R;

/**
 * Created by Kate on 09.05.2017.
 */

public class NewShoppingItem extends Fragment {


    public static final String TAG = NewShoppingItem.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_new_item, container, false);

        return view;
    }

    public static NewShoppingItem getInstance() {
        NewShoppingItem fragment = new NewShoppingItem();
        return fragment;
    }
}
