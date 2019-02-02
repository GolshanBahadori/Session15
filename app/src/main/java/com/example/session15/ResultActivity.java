package com.example.session15;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResultActivity extends AppCompatActivity {
    public static final String EXTERA_KEY_FIRSTNAME = "first_name";
    public static final String EXTERA_KEY_LASTNAME = "last_name";
    //private EditText firstNameEt;
    //private EditText lastNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // firstNameEt=findViewById(R.id.et_result_firstName);
        // lastNameEt=findViewById(R.id.et_result_lastName);
        final EditText firstNameEt = findViewById(R.id.et_result_firstName);
        final EditText lastNameEt = findViewById(R.id.et_result_lastName);

        Button saveResultBtn = findViewById(R.id.btn_result_save);
        saveResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EXTERA_KEY_FIRSTNAME, firstNameEt.getText().toString());
                intent.putExtra(EXTERA_KEY_LASTNAME, lastNameEt.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
