package com.mehran.linkstore.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mehran.linkstore.R;
import com.mehran.linkstore.ui.fragments.LinksFragment;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHomeFragment();
    }

    private void openHomeFragment()
    {
        LinksFragment fragment = LinksFragment.getInstance();
        openFragment(fragment);
    }

    private void openFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.content_main, fragment ,fragment.getClass().getSimpleName());
        transaction.commit();
    }
}
