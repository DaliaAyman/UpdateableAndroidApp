package com.android.dalia.updateableandroidapp.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.dalia.updateableandroidapp.R;
import com.android.dalia.updateableandroidapp.model.APIDataStrategy;
import com.android.dalia.updateableandroidapp.model.LocalDatabaseStrategy;
import com.android.dalia.updateableandroidapp.model.dto.ItemModel;
import com.android.dalia.updateableandroidapp.utils.ItemDataSourceViewModelFactory;
import com.android.dalia.updateableandroidapp.view.adapters.RecyclerViewAdapter;
import com.android.dalia.updateableandroidapp.view.base.AppCompatLifecycleActivity;
import com.android.dalia.updateableandroidapp.viewmodel.ItemListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewRequestedActivity extends AppCompatLifecycleActivity {

    @BindView(R.id.requestedListRecyclerView)
    RecyclerView requestedRecyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ItemListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requested);

        ButterKnife.bind(this);

        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<ItemModel>());
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(requestedRecyclerView.getContext());
        requestedRecyclerView.setLayoutManager(linearLayoutManager);
        requestedRecyclerView.addItemDecoration
                (new DividerItemDecoration(requestedRecyclerView.getContext(),
                linearLayoutManager.getOrientation()));
        requestedRecyclerView.setAdapter(recyclerViewAdapter);

        ItemDataSourceViewModelFactory factory
                = new ItemDataSourceViewModelFactory(new APIDataStrategy());
        viewModel = ViewModelProviders.of(this, factory).get(ItemListViewModel.class);

        viewModel.getItemAndPersonList().observe(ViewRequestedActivity.this, new Observer<List<ItemModel>>(){
            @Override
            public void onChanged(@Nullable List<ItemModel> itemModels) {
                recyclerViewAdapter.addItems(itemModels);
            }
        });
    }
}
