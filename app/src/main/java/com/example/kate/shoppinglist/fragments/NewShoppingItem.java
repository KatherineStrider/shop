package com.example.kate.shoppinglist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kate.shoppinglist.App;
import com.example.kate.shoppinglist.NewItemClickListener;
import com.example.kate.shoppinglist.R;

/**
 * Created by Kate on 09.05.2017.
 */

public class NewShoppingItem extends Fragment {


    public static final String TAG = NewShoppingItem.class.getSimpleName();

    Button btnNewItem;
    EditText eTName;
    EditText etCategory;
    EditText eTQuantity;
    NewItemClickListener newItemClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_new_item, container, false);
        btnNewItem = (Button) view.findViewById(R.id.btnNew);
        eTName = (EditText) view.findViewById(R.id.textName);
        etCategory = (EditText) view.findViewById(R.id.textCategory);
        eTQuantity = (EditText) view.findViewById(R.id.textQuantity);

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int i = Integer.parseInt(eTQuantity.getText().toString());

                App.getDB().addCategory(etCategory.getText().toString());

                App.getDB().addItem(eTName.getText().toString(),
                        i,
                        App.getDB().getCategoryIdByName(etCategory.getText().toString()));

                newItemClickListener.onItemClick();
            }
        };

        btnNewItem.setOnClickListener(onClickListener);

        return view;
    }

    public static NewShoppingItem getInstance() {
        NewShoppingItem fragment = new NewShoppingItem();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewItemClickListener) {
            newItemClickListener = (NewItemClickListener) context;
        } else {
            throw new IllegalStateException("Activity must implement ManClickListener");
        }
    }
}
