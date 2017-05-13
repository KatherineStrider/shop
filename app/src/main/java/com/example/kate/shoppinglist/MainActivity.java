package com.example.kate.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kate.shoppinglist.fragments.NewShoppingItem;
import com.example.kate.shoppinglist.fragments.ShoppingListFragment;

public class MainActivity extends AppCompatActivity implements NewItemClickListener {

    ShoppingListFragment fragmentList;
    NewShoppingItem fragmentItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentList = ShoppingListFragment.getInstance();
        fragmentItem = NewShoppingItem.getInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.list_items, fragmentList, ShoppingListFragment.TAG)
                .addToBackStack(ShoppingListFragment.TAG)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.newItem, fragmentItem, NewShoppingItem.TAG)
                .addToBackStack(NewShoppingItem.TAG)
                .commit();
    }

    @Override
    public void onItemClick() {
        fragmentList.refresh();
    }
}
