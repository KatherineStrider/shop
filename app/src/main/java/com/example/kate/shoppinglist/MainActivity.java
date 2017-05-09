package com.example.kate.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kate.shoppinglist.fragments.ShoppingListFragment;

public class MainActivity extends AppCompatActivity {

    ShoppingListFragment fragment_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_list = ShoppingListFragment.getInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment_list, ShoppingListFragment.TAG)
                .addToBackStack(ShoppingListFragment.TAG)
                .commit();
    }
}
