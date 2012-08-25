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

        setContentView(R.layout.verify);

        Bundle extras = getIntent().getExtras();
        Event event = null;
        if(extras !=null) {
            System.out.print("here brah");
            event = (Event) extras.getSerializable("event");
        }


        EditText verifyTitleInput = (EditText) findViewById(R.id.verifyTitleInput);
        EditText verifyDateInput = (EditText) findViewById(R.id.verifyDateInput);
        EditText verifyStartTimeInput = (EditText) findViewById(R.id.verifyStartTimeInput);
        EditText verifyEndTimeInput = (EditText) findViewById(R.id.verifyEndTimeInput);
        EditText verifyLocationInput = (EditText) findViewById(R.id.verifyLocaionInput);

        if (event.getEventDate() != null) {
            verifyDateInput.setText(event.getEventDate());
        }
        if (event.getTitle() != null) {

            verifyTitleInput.setText(event.getTitle());
        }
        if (event.getStartTime() != null) {
            verifyStartTimeInput.setText(event.getStartTime());
        }
        if (event.getEndTime() != null) {
            verifyEndTimeInput.setText(event.getEndTime());
        }
        if (event.getLocation() != null) {
            verifyLocationInput.setText(event.getLocation());
        }

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
