package ideanity.oceans.methodistndwom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import ideanity.oceans.methodistndwom.R;

public class DetailNoteActivity extends AppCompatActivity {

    TextView noteId;
    TextView themeDetails;
    TextView dateDetails;
    TextView bodyDetails;
    ImageView back, editDetails;

    String get_id;
    String get_theme;
    String get_date;
    String get_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_note);

        this.noteId = (TextView) findViewById(R.id.note_id);
        this.themeDetails = (TextView) findViewById(R.id.theme_name);
        this.dateDetails = (TextView) findViewById(R.id.date_details);
        this.bodyDetails = (TextView) findViewById(R.id.body_detail);
        this.back = (ImageView) findViewById(R.id.back_details);
        this.editDetails = (ImageView) findViewById(R.id.edit_details);

        this.get_id = getIntent().getExtras().getString("note_id");
        this.get_theme = getIntent().getExtras().getString("theme_name");
        this.get_date = getIntent().getExtras().getString("note_date");
        this.get_body = getIntent().getExtras().getString("message_body");

        this.noteId.setText(this.get_id);
        this.themeDetails.setText(this.get_theme);
        this.dateDetails.setText(this.get_date);
        this.bodyDetails.setText(this.get_body);

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailNoteActivity.super.onBackPressed();
            }
        });

        this.editDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchinIntent = new Intent(DetailNoteActivity.this, EditNoteActivity.class);
                launchinIntent.putExtra("note_id", get_id);
                launchinIntent.putExtra("theme_name", get_theme);
                launchinIntent.putExtra("note_date", get_date);
                launchinIntent.putExtra("message_body", get_body);
                DetailNoteActivity.this.startActivity(launchinIntent);
            }
        });

    }

}
