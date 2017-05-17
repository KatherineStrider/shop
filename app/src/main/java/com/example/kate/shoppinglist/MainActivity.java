package com.example.kate.shoppinglist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.kate.shoppinglist.fragments.ButtonFragment;
import com.example.kate.shoppinglist.fragments.NewShoppingItem;
import com.example.kate.shoppinglist.fragments.ShoppingListFragment;
import com.example.kate.shoppinglist.interfaces.ChangeFragment;
import com.example.kate.shoppinglist.interfaces.NewItemClickListener;

public class MainActivity extends AppCompatActivity implements NewItemClickListener, ChangeFragment {

    ShoppingListFragment fragmentList;
    NewShoppingItem fragmentItem;
    ButtonFragment buttonFragment;

    LinearLayout.LayoutParams layoutParams;
    final int lWeightWithKeyboard = 3;
    final int lWeightWithoutKeyboard = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.newItem);

        fragmentList = ShoppingListFragment.getInstance();
        fragmentItem = NewShoppingItem.getInstance();
        buttonFragment = ButtonFragment.getInstance();

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
        layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
    }

    @Override
    public void onItemClick() {
        layoutParams.weight = lWeightWithoutKeyboard;
        fragmentList.refresh();
    }

    @Override
    public void changeHeight() {
        Log.d("fragment", "Меняем высоту");
        layoutParams.weight = lWeightWithKeyboard;
    }

    @Override
    public void hideFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.newItem, buttonFragment, ButtonFragment.TAG)
                .addToBackStack(ButtonFragment.TAG)
                .commit();
    }

    @Override
    public void showFragment() {

//        fragmentList = ShoppingListFragment.getInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.newItem, fragmentItem, NewShoppingItem.TAG)
                .addToBackStack(NewShoppingItem.TAG)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (layoutParams.weight == lWeightWithKeyboard){
            this.onItemClick();
        }
        else if (getSupportFragmentManager().getBackStackEntryCount() > 0 | layoutParams.weight == lWeightWithoutKeyboard) {
            getSupportFragmentManager().popBackStackImmediate();
            finish();
        }
        super.onBackPressed();
    }
}
