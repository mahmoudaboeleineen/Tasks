package com.maboe.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {

    public static final String EXTRA_ID = "com.maboe.tasks.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.maboe.tasks.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.maboe.tasks.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.maboe.tasks.EXTRA_PRIORITY";


    private EditText noteTitle;
    private EditText noteDescription;
    private NumberPicker noteNumberPickerPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteTitle = findViewById(R.id.add_note_title);
        noteDescription = findViewById(R.id.add_note_description);
        noteNumberPickerPriority = findViewById(R.id.number_picker_priority);
        noteNumberPickerPriority.setMinValue(1);
        noteNumberPickerPriority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_icon);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Update Note");
            noteTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            noteDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            noteNumberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));

        } else {
            setTitle("Add Note");
        }
    }

    private void SaveNote() {
        String title = noteTitle.getText().toString();
        String description = noteDescription.getText().toString();
        int priority = noteNumberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please Insert The Title and Description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                SaveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
