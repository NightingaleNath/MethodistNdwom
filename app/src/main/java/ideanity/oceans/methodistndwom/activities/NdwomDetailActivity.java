package ideanity.oceans.methodistndwom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import ideanity.oceans.methodistndwom.R;

public class NdwomDetailActivity extends AppCompatActivity {

    TextView id;
    TextView title;
    TextView stanzas;
    TextView content;
    TextView info;

    String get_ndwom_title;
    String get_ndwom_stanzas;
    String get_ndwom_content;
    String get_ndwom_info;
    String get_ndwom_id;

    SeekBar seekBar;
    int seekValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ndwom_detail);

        this.id = (TextView) findViewById(R.id.id);
        this.title = (TextView) findViewById(R.id.title);
        this.stanzas = (TextView) findViewById(R.id.stanza);
        this.content = (TextView) findViewById(R.id.hymn_content);
        this.info = (TextView) findViewById(R.id.info);
        this.seekBar = (SeekBar) findViewById(R.id.font_seekbar);

        this.get_ndwom_id = getIntent().getExtras().getString("id");
        this.get_ndwom_title = getIntent().getExtras().getString("title");
        this.get_ndwom_stanzas = getIntent().getExtras().getString("stanzas");
        this.get_ndwom_content = getIntent().getExtras().getString("content");
        this.get_ndwom_info = getIntent().getExtras().getString("info");

        this.id.setText(this.get_ndwom_id);
        this.title.setText(this.get_ndwom_title);
        this.stanzas.setText(this.get_ndwom_stanzas);
        this.content.setText(this.get_ndwom_content);
        this.info.setText(this.get_ndwom_info);

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
