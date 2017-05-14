package com.example.kate.shoppinglist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kate.shoppinglist.R;
import com.example.kate.shoppinglist.interfaces.ChangeFragment;

/**
 * Created by Kate on 14.05.2017.
 */

public class ButtonFragment extends Fragment {

    public static final String TAG = ButtonFragment.class.getSimpleName();

    Button btnShow;
    ChangeFragment changeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.view_hide, container, false);
        btnShow = (Button) view.findViewById(R.id.btnShow);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment.showFragment();
            }
        };

        btnShow.setOnClickListener(onClickListener);

        return view;
    }

    public static ButtonFragment getInstance() {
        ButtonFragment fragment = new ButtonFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChangeFragment) {
            changeFragment = (ChangeFragment) context;
        } else {
            throw new IllegalStateException("Activity must implement " +
                    ChangeFragment.class.getSimpleName());
        }
    }

}
