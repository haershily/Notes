package com.example.harshil.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> notes=new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static Set<String> set;
    static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.listview);

        sharedPreferences=this.getSharedPreferences("com.example.harshil.notes", Context.MODE_PRIVATE);
        set=sharedPreferences.getStringSet("notes",null);
        notes.clear();
        if(set!=null){
            notes.addAll(set);
        }else {
            notes.add("Example Note");
            set=new HashSet<String>();
            set.addAll(notes);
            sharedPreferences.edit().remove("notes").apply();

            sharedPreferences.edit().putStringSet("notes",set).apply();
        }


        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("noteid",i);
                startActivity(intent);
            }
        });

listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

          notes.remove(i);

        if(MainActivity.set==null)
        {MainActivity.set=new HashSet<String>();}
        else{
            MainActivity.set.clear();}
        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().remove("notes").apply();
        MainActivity.sharedPreferences.edit().putStringSet("notes",MainActivity.set).apply();
        arrayAdapter.notifyDataSetChanged();
        return false;
    }
});
    }
    public void newNote(View view)
    {
        notes.add("");
        if(MainActivity.set==null)
        {MainActivity.set=new HashSet<String>();}
        else{
            MainActivity.set.clear();}
        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().remove("notes").apply();
        MainActivity.sharedPreferences.edit().putStringSet("notes",MainActivity.set).apply();
        arrayAdapter.notifyDataSetChanged();
        Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
        intent.putExtra("noteid",notes.size()-1);
        startActivity(intent);
    }
}
