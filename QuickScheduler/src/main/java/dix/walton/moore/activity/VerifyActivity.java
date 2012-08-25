package dix.walton.moore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import dix.walton.moore.R;
import dix.walton.moore.model.Event;

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

        if(extras !=null) {
            String value = extras.getString("voiceString");
        }

        //TODO replace this with steven's method
        Event parsedEvent = getMockEvent();
        serviceEvent = parsedEvent;

        EditText verifyTitleInput = (EditText) findViewById(R.id.verifyTitleInput);
        EditText verifyDateInput = (EditText) findViewById(R.id.verifyDateInput);
        EditText verifyStartTimeInput = (EditText) findViewById(R.id.verifyStartTimeInput);
        EditText verifyEndTimeInput = (EditText) findViewById(R.id.verifyEndTimeInput);
        EditText verifyLocationInput = (EditText) findViewById(R.id.verifyLocaionInput);

        verifyDateInput.setText(dateFormat.format(parsedEvent.getEventDate()));
        verifyTitleInput.setText(parsedEvent.getTitle());
        verifyStartTimeInput.setText(parsedEvent.getStartTime());
        verifyEndTimeInput.setText(parsedEvent.getEndTime());
        verifyLocationInput.setText(parsedEvent.getLocation());
    }

    private Event getMockEvent() {

        Date date = new Date();

        Event event = new Event();
        event.setTitle("Poopy event");
        event.setEndTime(String.valueOf(date.getTime()));
        event.setId(123L);
        event.setLocation("Home");
        event.setStartTime(String.valueOf(date.getTime()));
        event.setEventDate(date);

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
