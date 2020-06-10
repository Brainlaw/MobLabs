package com.example.lab2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class StorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        updateData();
        Button action_drop = (Button) findViewById(R.id.drop_button);
        action_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
                db.execSQL("DROP TABLE IF EXISTS choices");
                db.close();
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("Empty");
            }

        });
    }
    public void updateData() {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS choices (name TEXT)");
        Cursor query = db.rawQuery("SELECT * FROM choices;", null);
        TextView textView = (TextView) findViewById(R.id.textView);
        if(query.moveToFirst()){
            int i = 1;
            do{
                String name = query.getString(0);
                textView.append("Number: " + i + " Name: " + name + "\n");
                i+=1;
            }
            while(query.moveToNext());
        }
        query.close();
        db.close();
    }
}

