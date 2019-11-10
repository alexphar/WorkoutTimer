package com.example.lab4;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    static final int ADD_NEW_PART_REQ_ID = 200;

    ArrayList<WorkOutPartBase> parts = new ArrayList<>();
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.workOutList);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        /*WorkOutPart workOut1 = new WorkOutPart(3, " "); //test items
        parts.add(workOut1);

        PausePart workOut2 = new PausePart(2, " ");
        parts.add(workOut2);
        */

        findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (parts.size() > 0)
                {
                    Intent intent = new Intent(getApplicationContext(), run_program_timer.class);
                    intent.putExtra("PART", parts);
                    startActivity(intent);
                }
            }
        });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_menu_item) {
            Intent intent = new Intent(this, new_part.class);
            startActivityForResult(intent, ADD_NEW_PART_REQ_ID);
        }

        else if (item.getItemId() == R.id.clear_parts) {
            AlertDialog diaBox = AskOption();
            diaBox.show();
            //adapter.notifyDataSetChanged();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        PartAdapter adapter = new PartAdapter(this, parts);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
            }

    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_PART_REQ_ID  && resultCode == RESULT_OK) {
            WorkOutPartBase part = (WorkOutPartBase) data.getSerializableExtra("PART");
            parts.add(part);
        }
    }

    public AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Warning")
                .setMessage("Do you want to delete all the activities?")
                .setIcon(R.drawable.web_hi_res_512)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        parts.clear();
                        onResume();
                        //adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }
}
