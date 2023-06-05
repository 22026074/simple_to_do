package sg.edu.rp.c346.id22026074.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddRemove;
    EditText etTask;
    Button btnAdd;
    Button btnRemove;
    Button btnClear;
    ListView lvTask;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddRemove = findViewById(R.id.spinner);
        etTask = findViewById(R.id.editTask);
        btnAdd = findViewById(R.id.btnAddItem);
        btnRemove = findViewById(R.id.btnRemoveItem);
        btnClear = findViewById(R.id.btnClearItem);
        lvTask = findViewById(R.id.listViewTask);

        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etTask.setHint("ENTER NEW TASKS");
                        btnAdd.setEnabled(true);
                        btnRemove.setEnabled(false);
                        break;
                    case 1:
                        etTask.setHint("ENTER TASK INDEX");
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTask = etTask.getText().toString();
                alTask.add(getTask);
                aaTask.notifyDataSetChanged();
                etTask.setText(null);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getTask = Integer.parseInt(etTask.getText().toString());
                if (alTask.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You don't have any text to remove", Toast.LENGTH_LONG).show();
                } else if (getTask >= alTask.size()) {
                    Toast.makeText(getApplicationContext(), "This index does not exist", Toast.LENGTH_LONG).show();
                } else {
                    alTask.remove(getTask);
                    aaTask.notifyDataSetChanged();
                    etTask.setText(null);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });
    }
}