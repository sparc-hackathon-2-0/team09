package dix.walton.moore.activity;

import dix.walton.moore.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

    private class SettingsButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            handleSettingsButtonClick();
        }
    }

    private class MicButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            handleMicButtonClick();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu);

        final Button mic = (Button) findViewById(R.id.mic);

        final Button settings = (Button) findViewById(R.id.settings);

        settings.setOnClickListener(new SettingsButtonHandler());
        mic.setOnClickListener(new MicButtonHandler());
    }

    public void handleSettingsButtonClick() {
        startActivity(new Intent(this, SettingsActivity.class));
        finish();
    }

    public void handleMicButtonClick() {
        startActivity(new Intent(this, VoiceAddAcitivity.class));
        finish();
    }
}