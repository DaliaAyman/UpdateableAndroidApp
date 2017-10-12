package com.android.dalia.updateableandroidapp.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.dalia.updateableandroidapp.R;
import com.android.dalia.updateableandroidapp.model.LocalDatabaseStrategy;
import com.android.dalia.updateableandroidapp.model.dto.ItemModel;
import com.android.dalia.updateableandroidapp.utils.ItemDataSourceViewModelFactory;
import com.android.dalia.updateableandroidapp.view.adapters.RecyclerViewAdapter;
import com.android.dalia.updateableandroidapp.view.base.BaseActivity;
import com.android.dalia.updateableandroidapp.viewmodel.ItemListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnLongClickListener{

    private ItemListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;

    @BindView(R.id.borrowedListRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<ItemModel>(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation()));
        recyclerView.setAdapter(recyclerViewAdapter);

        ItemDataSourceViewModelFactory factory
                = new ItemDataSourceViewModelFactory(new LocalDatabaseStrategy(this));
        viewModel = ViewModelProviders.of(this, factory).get(ItemListViewModel.class);

        viewModel.getItemAndPersonList()
                .observe(MainActivity.this, new Observer<List<ItemModel>>() {
            @Override
            public void onChanged(@Nullable List<ItemModel> itemModelList) {
                recyclerViewAdapter.addItems(itemModelList);
            }
        });
    }

    @Override
    protected String setToolbarTitle() {
        return getString(R.string.app_name);
    }

    @Override
    public boolean onLongClick(View v) {
        ItemModel itemModel = (ItemModel) v.getTag();
        viewModel.deleteItem(itemModel);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.request:
                startActivity(new Intent(this, ViewRequestedActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}
