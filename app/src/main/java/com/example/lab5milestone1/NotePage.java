package com.example.lab5milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotePage extends AppCompatActivity {

    int noteid = -1;
    SharedPreferences sharedPreferences;
    DBHelper dbHelper;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);

        sharedPreferences = getSharedPreferences("lab5milestone1", Context.MODE_PRIVATE);

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

        dbHelper = new DBHelper(sqLiteDatabase);

        EditText textBox = (EditText) findViewById(R.id.note_text);

        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);

        if (noteid != -1) {
            Note note = Welcome.notes.get(noteid);
            String noteContent = note.getContent();

            textBox.setText(noteContent);
        }
    }

    public void saveNote(View view) {

        EditText textBox = (EditText) findViewById(R.id.note_text);
        content = textBox.getText().toString();

        String username = sharedPreferences.getString("username", "");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (Welcome.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("user", username);
        startActivity(intent);
    }
}