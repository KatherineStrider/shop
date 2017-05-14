package com.example.kate.shoppinglist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kate.shoppinglist.App;
import com.example.kate.shoppinglist.interfaces.ChangeFragment;
import com.example.kate.shoppinglist.interfaces.NewItemClickListener;
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

    String textAdd = "Hide";
    NewItemClickListener newItemClickListener;
    ChangeFragment changeFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.view_new_item, container, false);

        btnNewItem = (Button) view.findViewById(R.id.btnNew);
        btnNewItem.setText(textAdd);
        eTName = (EditText) view.findViewById(R.id.textName);
        etCategory = (EditText) view.findViewById(R.id.textCategory);
        eTQuantity = (EditText) view.findViewById(R.id.textQuantity);

        setListener();

        return view;
    }

    private void setListener(){

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (eTName.getText().toString().equals("") &&
                        etCategory.getText().toString().equals("") &&
                        eTQuantity.getText().toString().equals("")){
                    changeFragment.hideFragment();
                }else {
                    int i = 0;

                    try {
                        i = Integer.parseInt(eTQuantity.getText().toString());
                    } catch (NumberFormatException e) {
                        return;
                    }

                    if (!App.getDB().findCategoryByName(etCategory.getText().toString())) {
                        App.getDB().addCategory(etCategory.getText().toString());
                    }

                    App.getDB().addItem(eTName.getText().toString(),
                            i,
                            App.getDB().getCategoryIdByName(etCategory.getText().toString()));

                    eTName.setText("");
                    etCategory.setText("");
                    eTQuantity.setText("");

                    btnNewItem.setText(textAdd);

                    newItemClickListener.onItemClick();
                }
            }
        };

        btnNewItem.setOnClickListener(onClickListener);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnNewItem.setText("+");
                changeFragment.changeHeight();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        eTName.addTextChangedListener(textWatcher);
        etCategory.addTextChangedListener(textWatcher);
        eTQuantity.addTextChangedListener(textWatcher);

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
            throw new IllegalStateException("Activity must implement " +
                    NewItemClickListener.class.getSimpleName());
        }

        if (context instanceof ChangeFragment) {
            changeFragment = (ChangeFragment) context;
        } else {
            throw new IllegalStateException("Activity must implement " +
                    ChangeFragment.class.getSimpleName());
        }
    }
}
