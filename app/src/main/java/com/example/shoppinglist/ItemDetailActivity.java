package com.example.shoppinglist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText;
    private String item;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        dbHelper = new DBHelper(this);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);

        item = getIntent().getStringExtra("item");
        String[] parts = item.split(": ");
        titleEditText.setText(parts[0]);
        descriptionEditText.setText(parts[1]);
    }

    public void updateItem(View view) {
        String newTitle = titleEditText.getText().toString();
        String newDescription = descriptionEditText.getText().toString();

        boolean updated = dbHelper.updateItem(item, newTitle, newDescription);
        if (updated) {
            Toast.makeText(this, "Item updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to update item", Toast.LENGTH_SHORT).show();
        }
    }
}
