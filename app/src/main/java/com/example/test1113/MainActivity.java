package com.example.test1113;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editTextName;
    EditText editTextSurname;
    EditText editTextAge;
    Button button;
    Person savedPerson;
    RadioGroup radioGroup;

    String savedPersonKey = "saved_person";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextAge = findViewById(R.id.editTextAge);
        button = findViewById(R.id.button);
        radioGroup = findViewById(R.id.radioGroup);

        sharedPreferences = getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        textView.setText(getSavedTextFromCache());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();
                String age = editTextAge.getText().toString();
                boolean isMale = true;
                if (radioGroup.getCheckedRadioButtonId() == R.id.femaleGender)
                    isMale = false;

                savedPerson = new Person(name, surname, age, isMale);



                editor.putString(savedPersonKey, savedPerson);
                editor.apply();
                textView.setText(getSavedTextFromCache());
            }
        });
    }

    private String getSavedTextFromCache() {
        return sharedPreferences.getString(savedPersonKey, "");
    }
}