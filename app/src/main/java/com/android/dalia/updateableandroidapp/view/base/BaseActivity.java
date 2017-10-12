package com.android.dalia.updateableandroidapp.view.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.dalia.updateableandroidapp.R;

public abstract class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    Toolbar toolbar;
    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);

        FrameLayout activityContainer = fullView.findViewById(R.id.activity_content);

        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        super.setContentView(fullView);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (useToolbar()) {
            toolbar.setTitle(setToolbarTitle());
            setSupportActionBar(toolbar);
            Log.d("Toolbar", "Setting toolbar title: ");
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    protected String setToolbarTitle(){
        return getString(R.string.empty_string);
    };

    protected boolean useToolbar(){
        return true;
    }
}
