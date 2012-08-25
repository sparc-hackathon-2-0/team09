package dix.walton.moore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import dix.walton.moore.R;

/**
 * user: ryan.moore
 * date: 8/25/12
 */
public class VerifyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.verify);

        Bundle extras = getIntent().getExtras();

        if(extras !=null) {

            String value = extras.getString("voiceString");
        }
    }
}
