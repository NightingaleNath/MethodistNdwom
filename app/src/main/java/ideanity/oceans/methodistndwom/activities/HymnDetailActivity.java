package ideanity.oceans.methodistndwom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import ideanity.oceans.methodistndwom.R;

public class HymnDetailActivity extends AppCompatActivity {

    TextView id;
    TextView title;
    TextView stanzas;
    TextView content;
    TextView info;

    String get_hymn_title;
    String get_hymn_stanzas;
    String get_hymn_content;
    String get_hymn_info;
    String get_hymn_id;

    SeekBar seekBar;
    int seekValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hymn_detail);

        this.id = (TextView) findViewById(R.id.id);
        this.title = (TextView) findViewById(R.id.title);
        this.stanzas = (TextView) findViewById(R.id.stanza);
        this.content = (TextView) findViewById(R.id.hymn_content);
        this.info = (TextView) findViewById(R.id.info);
        this.seekBar = (SeekBar) findViewById(R.id.font_seekbar);

        this.get_hymn_id = getIntent().getExtras().getString("id");
        this.get_hymn_title = getIntent().getExtras().getString("title");
        this.get_hymn_stanzas = getIntent().getExtras().getString("stanzas");
        this.get_hymn_content = getIntent().getExtras().getString("content");
        this.get_hymn_info = getIntent().getExtras().getString("info");

        this.id.setText(this.get_hymn_id);
        this.title.setText(this.get_hymn_title);
        this.stanzas.setText(this.get_hymn_stanzas);
        this.content.setText(this.get_hymn_content);
        this.info.setText(this.get_hymn_info);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekValue = i;
                content.setTextSize(seekValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
