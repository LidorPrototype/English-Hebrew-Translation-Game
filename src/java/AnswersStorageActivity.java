package com.example.translationgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AnswersStorageActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView itemList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;
    private Button btnBackToGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_storage);

        btnBackToGame = findViewById(R.id.btnBackToGame);
        itemList = findViewById(R.id.listViewAnswers);
        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);

        btnBackToGame.setOnClickListener(this);

//        items.clear();
//        adapter.clear();
//        adapter.notifyDataSetChanged();
//        FileHelper.writeData(items, this);
    }

    @Override
    public void onClick(View view) {
        Intent goToMainActivity = new Intent(AnswersStorageActivity.this, MainActivity.class);
        startActivity(goToMainActivity);
        finish();
    }
}
