package ideanity.oceans.methodistndwom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
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

public class AddNoteActivity extends AppCompatActivity {

    String date_time = "";
    EditText etxtDate;
    EditText etxtTheme;
    EditText etxtMessage;
    int mDay;
    int mMonth;
    int mYear;
    TextView txtAddMessage;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_note);

        this.back = (ImageView) findViewById(R.id.back);
        this.etxtTheme = (EditText) findViewById(R.id.etxt_theme);
        this.etxtMessage = (EditText) findViewById(R.id.etxt_message);
        this.etxtDate = (EditText) findViewById(R.id.etxt_date);
        this.txtAddMessage = (TextView) findViewById(R.id.txt_add);

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date());

        this.etxtDate.setText(currentDate);

        this.etxtDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddNoteActivity.this.datePicker();
            }
        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNoteActivity.super.onBackPressed();
            }
        });

        this.txtAddMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String theme = AddNoteActivity.this.etxtTheme.getText().toString();
                String message = AddNoteActivity.this.etxtMessage.getText().toString();
                String expense_date = AddNoteActivity.this.etxtDate.getText().toString();

                if (theme.isEmpty()) {
                    AddNoteActivity.this.etxtTheme.setError(AddNoteActivity.this.getString(R.string.theme_cannot));
                    AddNoteActivity.this.etxtTheme.requestFocus();
                } else if (message.isEmpty()) {
                    AddNoteActivity.this.etxtMessage.setError(AddNoteActivity.this.getString(R.string.message_cannot));
                    AddNoteActivity.this.etxtMessage.requestFocus();
                } else {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(AddNoteActivity.this);
                    databaseAccess.open();
                    if (databaseAccess.addNote(theme, message, expense_date)) {
                        //Toasty.success((Context) AddNoteActivity.this, (int) R.string.expense_successfully_added, 0).show();
                        Toast.makeText(AddNoteActivity.this, "Message Successfully added", Toast.LENGTH_SHORT).show();
                        //AddNoteActivity.this.startActivity(new Intent(AddNoteActivity.this, HomeActivity.class));
                        AddNoteActivity.this.finish();
                        return;
                    }
                    Toast.makeText(AddNoteActivity.this, "Failed to add Message", Toast.LENGTH_SHORT).show();
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
                AddNoteActivity addNoteActivity = AddNoteActivity.this;
                StringBuilder sb5 = new StringBuilder();
                sb5.append(year);
                String str3 = "-";
                sb5.append(str3);
                sb5.append(fm);
                sb5.append(str3);
                sb5.append(fd);
                addNoteActivity.date_time = sb5.toString();
                AddNoteActivity.this.etxtDate.setText(AddNoteActivity.this.date_time);
            }
        }, this.mYear, this.mMonth, this.mDay);

        datePickerDialog.show();
    }

}
