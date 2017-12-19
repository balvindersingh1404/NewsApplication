package com.example.balvinder.newsapplication.ui;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.balvinder.newsapplication.NewsConstant;
import com.example.balvinder.newsapplication.R;
import com.example.balvinder.newsapplication.fragments.source.SourceFragments;
import com.example.balvinder.newsapplication.utils.Utility;

public class MainActivity extends AppCompatActivity {
    Utility utility;
    Context mContext;
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        utility = new Utility();
        utility.putVisibleFragment(this, NewsConstant.SOURCE_FRAGMENT);
        callSourceFragment();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("News");
        setSupportActionBar(myToolbar);
    }
    private void callSourceFragment() {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        utility.putVisibleFragment(mContext, NewsConstant.SOURCE_FRAGMENT);
        SourceFragments sourceFragment = new SourceFragments();
        fragmentTransaction.replace(R.id.fragmentContainer, sourceFragment, getResources().getString(R.string.source_fragment));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}


