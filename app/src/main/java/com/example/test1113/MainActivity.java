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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

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

        fillTextView(getSavedPersonFromCache());

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

                Gson gson = new Gson();
                String gsonPerson = gson.toJson(savedPerson);
                editor.putString(savedPersonKey, gsonPerson);
                editor.apply();
                fillTextView(getSavedPersonFromCache());
            }
        });
    }

    private Person getPersonFromJson(String json) {
        if (json.equals(""))
            return null;
        Gson gson = new Gson();
        Type type = new TypeToken<Person>(){}.getType();
        return gson.fromJson(json, type);
    }

    private Person getSavedPersonFromCache() {
        String jsonPerson = sharedPreferences.getString(savedPersonKey, "");
        return getPersonFromJson(jsonPerson);
    }

    private void fillTextView(Person person) {

        if (person == null)
            return;

        String gender = "женский";
        if (person.isMale)
            gender = "мужской";

        String textToFill = "Имя: " + person.name + "\n" +
            "Фамилия: " + person.surname + "\n" +
            "Возраст: " + person.age + "\n" +
            "Пол: " + gender;

        textView.setText(textToFill);
    }
}