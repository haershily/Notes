package com.example.harshil.notes;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.HashSet;

public class MainActivity2 extends AppCompatActivity implements TextWatcher {
    EditText editText;
    int noteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        editText=(EditText)findViewById(R.id.editext);
        Intent intent=getIntent();
        noteid=intent.getIntExtra("noteid",-1);
        editText.setText(String.valueOf(MainActivity.notes.get(noteid)));
        editText.addTextChangedListener(this);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        MainActivity.notes.set(noteid,String.valueOf(charSequence));
        MainActivity.arrayAdapter.notifyDataSetChanged();
        if(MainActivity.set==null){MainActivity.set=new HashSet<String>();}
        else{MainActivity.set.clear();}
        MainActivity.set.addAll(MainActivity.notes);
        MainActivity.sharedPreferences.edit().remove("notes").apply();

        MainActivity.sharedPreferences.edit().putStringSet("notes",MainActivity.set).apply();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
