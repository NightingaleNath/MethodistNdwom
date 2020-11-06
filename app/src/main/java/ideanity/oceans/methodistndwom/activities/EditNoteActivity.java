package ideanity.oceans.methodistndwom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ideanity.oceans.methodistndwom.R;
import ideanity.oceans.methodistndwom.database.DatabaseAccess;

public class EditNoteActivity extends AppCompatActivity {

    String date_time = "";
    EditText etxtDate;
    EditText etxtTheme;
    EditText etxtMessage;
    TextView noteId;
    int mDay;
    int mMonth;
    int mYear;
    TextView txtAddMessage;
    ImageView back;

    String get_id;
    String get_theme;
    String get_date;
    String get_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_note);

        this.back = (ImageView) findViewById(R.id.back_edit);
        this.etxtTheme = (EditText) findViewById(R.id.edit_theme);
        this.etxtMessage = (EditText) findViewById(R.id.edit_message);
        this.etxtDate = (EditText) findViewById(R.id.edit_date);
        this.noteId = (TextView) findViewById(R.id.note_id);
        this.txtAddMessage = (TextView) findViewById(R.id.edit_add);

        this.get_id = getIntent().getStringExtra("note_id");
        this.get_theme = getIntent().getStringExtra("theme_name");
        this.get_date = getIntent().getStringExtra("note_date");
        this.get_body = getIntent().getStringExtra("message_body");

        this.noteId.setText(this.get_id);
        this.etxtTheme.setText(this.get_theme);
        this.etxtDate.setText(this.get_date);
        this.etxtMessage.setText(this.get_body);

        this.etxtDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditNoteActivity.this.datePicker();
            }
        });

        this.txtAddMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String theme = EditNoteActivity.this.etxtTheme.getText().toString();
                String message = EditNoteActivity.this.etxtMessage.getText().toString();
                String expense_date = EditNoteActivity.this.etxtDate.getText().toString();

                if (theme.isEmpty()) {
                    EditNoteActivity.this.etxtTheme.setError(EditNoteActivity.this.getString(R.string.theme_cannot));
                    EditNoteActivity.this.etxtTheme.requestFocus();
                } else if (message.isEmpty()) {
                    EditNoteActivity.this.etxtMessage.setError(EditNoteActivity.this.getString(R.string.message_cannot));
                    EditNoteActivity.this.etxtMessage.requestFocus();
                }
                else{
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(EditNoteActivity.this);
                    databaseAccess.open();
                    if (databaseAccess.updateNote(String.valueOf(noteId.getText()), theme, message, expense_date)){
                        Toast.makeText(EditNoteActivity.this, "Message Successfully added", Toast.LENGTH_SHORT).show();
                        EditNoteActivity.this.startActivity(new Intent(EditNoteActivity.this, DetailNoteActivity.class));
                        EditNoteActivity.this.finish();
                        return;
                    }
                    Toast.makeText(EditNoteActivity.this, "Failed to add Message", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void datePicker() {
        Calendar c = Calendar.getInstance();
        this.mYear = c.get(1);
        this.mMonth = c.get(2);
        this.mDay = c.get(5);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int month = monthOfYear + 1;
                StringBuilder sb = new StringBuilder();
                String str = "";
                sb.append(str);
                sb.append(month);
                String fm = sb.toString();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(dayOfMonth);
                String fd = sb2.toString();
                String str2 = "0";
                if (monthOfYear < 10) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str2);
                    sb3.append(month);
                    fm = sb3.toString();
                }
                if (dayOfMonth < 10) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(str2);
                    sb4.append(dayOfMonth);
                    fd = sb4.toString();
                }
                EditNoteActivity addNoteActivity = EditNoteActivity.this;
                StringBuilder sb5 = new StringBuilder();
                sb5.append(year);
                String str3 = "-";
                sb5.append(str3);
                sb5.append(fm);
                sb5.append(str3);
                sb5.append(fd);
                addNoteActivity.date_time = sb5.toString();
                EditNoteActivity.this.etxtDate.setText(EditNoteActivity.this.date_time);
            }
        }, this.mYear, this.mMonth, this.mDay);

        datePickerDialog.show();
    }

}
