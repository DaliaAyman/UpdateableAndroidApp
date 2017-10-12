package com.android.dalia.updateableandroidapp.view.ui;

import android.app.DatePickerDialog;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.dalia.updateableandroidapp.R;
import com.android.dalia.updateableandroidapp.model.LocalDatabaseStrategy;
import com.android.dalia.updateableandroidapp.model.dto.ItemModel;
import com.android.dalia.updateableandroidapp.utils.ItemDataSourceViewModelFactory;
import com.android.dalia.updateableandroidapp.view.base.BaseActivity;
import com.android.dalia.updateableandroidapp.viewmodel.ItemListViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends BaseActivity {
    private ItemListViewModel viewModel;

    @BindView(R.id.setDateButton)
    Button setDateButton;
    @BindView(R.id.itemEditText)
    EditText itemEditText;
    @BindView(R.id.personEditText) EditText personEditText;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener datePickerDialogListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);

        setDateButton.setText("Set Date");

        ItemDataSourceViewModelFactory factory
                = new ItemDataSourceViewModelFactory(new LocalDatabaseStrategy(this));
        viewModel = ViewModelProviders.of(this, factory).get(ItemListViewModel.class);

        Log.d("Add", "calendar time: " + calendar.getTime());
        datePickerDialogListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, datePickerDialogListener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemEditText.getText().toString();
                String person = personEditText.getText().toString();

                Log.d("Add", "item: " + item + ", person: " + person + ", datePickerDialogListener: " + updateLabel());

                ItemModel itemModel = new ItemModel(item, person, calendar.getTime());
                viewModel.addItem(itemModel);

                finish();
            }
        });
    }

    private String updateLabel() {
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String dateText = sdf.format(calendar.getTime());
        setDateButton.setText(dateText);
        return dateText;
    }
}
