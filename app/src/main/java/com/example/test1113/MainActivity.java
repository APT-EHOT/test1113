package com.example.test1113;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    String savedText;

    String savedTextKey = "saved_text";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        sharedPreferences = getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        textView.setText(getSavedTextFromCache());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedText = editText.getText().toString();
                editor.putString(savedTextKey, savedText);
                editor.apply();
                textView.setText(getSavedTextFromCache());
            }
        });
    }

    private String getSavedTextFromCache() {
        return sharedPreferences.getString(savedTextKey, "");
    }
}