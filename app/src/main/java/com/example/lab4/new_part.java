package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.EXTRA_TEXT;

public class new_part extends AppCompatActivity  {

    private RadioButton radioButtonWorkOut;
    private RadioButton radioButtonPause;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_part);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final RadioGroup rg = findViewById(R.id.optionsGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButtonWorkOut:
                        findViewById(R.id.radioButtonPause).setEnabled(false);
                        id = 1;

                        break;
                    case R.id.radioButtonPause:
                        findViewById(R.id.radioButtonWorkOut).setEnabled(false);
                        id = 2;

                        break;
                }
            }
        });


        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                EditText editor = findViewById(R.id.edit_text);
                String text = editor.getText().toString();
                int countDown = Integer.parseInt(text);

              if (id == 1 ) {

                  WorkOutPart stuff = new WorkOutPart(countDown, " ");

                  returnData(stuff);
              }
              else if (id == 2) {
                  PausePart stuff = new PausePart(countDown, " ");
                  returnData(stuff);
              }
            }
        });
    }

    private void returnData(WorkOutPartBase data) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("PART", data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
