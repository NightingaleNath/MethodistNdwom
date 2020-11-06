package ideanity.oceans.methodistndwom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import ideanity.oceans.methodistndwom.R;

public class DetailCanticleActivity extends AppCompatActivity {

    TextView id;
    TextView content;

    String get_can_info;
    String get_can_id;

    SeekBar seekBar;
    int seekValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_canticle);

        this.content = (TextView) findViewById(R.id.lyrics_content);
        this.id = (TextView) findViewById(R.id.id);
        this.seekBar = (SeekBar) findViewById(R.id.font_seekbar);

        this.get_can_id = getIntent().getExtras().getString("id");
        this.get_can_info = getIntent().getExtras().getString("hymn_lyrics");

        this.id.setText(this.get_can_id);
        this.content.setText(this.get_can_info);

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
