package dix.walton.moore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import dix.walton.moore.R;
import dix.walton.moore.model.Event;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * user: ryan.moore
 * date: 8/25/12
 */
public class VerifyActivity extends Activity {

    private Event serviceEvent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

        setContentView(R.layout.verify);

        Bundle extras = getIntent().getExtras();
        Event event = null;
        if(extras !=null) {
            event = (Event) extras.getSerializable("event");
        }
        serviceEvent = event;

        EditText verifyTitleInput = (EditText) findViewById(R.id.verifyTitleInput);
        EditText verifyDateInput = (EditText) findViewById(R.id.verifyDateInput);
        EditText verifyStartTimeInput = (EditText) findViewById(R.id.verifyStartTimeInput);
        EditText verifyEndTimeInput = (EditText) findViewById(R.id.verifyEndTimeInput);
        EditText verifyLocationInput = (EditText) findViewById(R.id.verifyLocaionInput);

        verifyDateInput.setText(serviceEvent.getEventDate());
        verifyTitleInput.setText(serviceEvent.getTitle());
        verifyStartTimeInput.setText(serviceEvent.getStartTime());
        verifyEndTimeInput.setText(serviceEvent.getEndTime());
        verifyLocationInput.setText(serviceEvent.getLocation());
    }

    private Event getMockEvent() {

        Date date = new Date();

        Event event = new Event();
        event.setTitle("Poopy event");
        event.setEndTime(String.valueOf(date.getTime()));
        event.setId("123");
        event.setLocation("Home");
        event.setStartTime(String.valueOf(date.getTime()));
        event.setEventDate("tomorrow");

        return event;
    }

    private boolean isEventChanged(Event event) {

        if (event.equals(serviceEvent)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean deleteEvent(Event event) {

        //TODO implement me
        return true;
    }

    private boolean updateEvent(Event event) {

        //TODO implement me
        return true;
    }
}
