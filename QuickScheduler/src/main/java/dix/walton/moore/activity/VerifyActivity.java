package dix.walton.moore.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
public class VerifyActivity extends MenuActivity {

    private Event serviceEvent;
    EditText verifyTitleInput;
    EditText verifyDateInput;
    EditText verifyStartTimeInput;
    EditText verifyEndTimeInput;
    EditText verifyLocationInput;




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
        serviceEvent = event;

        EditText verifyTitleInput = (EditText) findViewById(R.id.verifyTitleInput);
        EditText verifyDateInput = (EditText) findViewById(R.id.verifyDateInput);
        EditText verifyStartTimeInput = (EditText) findViewById(R.id.verifyStartTimeInput);
        EditText verifyEndTimeInput = (EditText) findViewById(R.id.verifyEndTimeInput);
        EditText verifyLocationInput = (EditText) findViewById(R.id.verifyLocaionInput);
        Button verifyButton = (Button) findViewById(R.id.verifyButton);

        verifyButton.setOnClickListener(new verifyOnclickListener());

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

    private class verifyOnclickListener implements View.OnClickListener{

        public void onClick(View v) {
            handleUpdateEvent();
       }
    }
    public void handleUpdateEvent(){
        Event stubbedEvent = new Event();
        stubbedEvent.setEndTime(verifyEndTimeInput.getText().toString());
        stubbedEvent.setEventDate(verifyDateInput.getText().toString());
        stubbedEvent.setId(serviceEvent.getId());
        stubbedEvent.setLocation(verifyLocationInput.getText().toString());
        stubbedEvent.setStartTime(verifyStartTimeInput.getText().toString());
        stubbedEvent.setTitle(verifyTitleInput.getText().toString());
        if(isEventChanged(stubbedEvent)){
            new AsyncDeleteEvent(this, serviceEvent.getId());
            String newInput = getGoogleCallString(stubbedEvent);
    //            new AsyncCalendarQuickEvent()
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
    
    private String getGoogleCallString(Event stubbedEvent) {

        String newInput = "";

        if (stubbedEvent.getTitle() != null) {

            newInput = newInput + stubbedEvent.getTitle() + " ";
        }
        if (stubbedEvent.getLocation() != null) {
           newInput = newInput + "at " + stubbedEvent.getLocation() + " ";
        }
        if (stubbedEvent.getEventDate() != null) {
            newInput = newInput + "on " + stubbedEvent.getEventDate() + " ";

            if (stubbedEvent.getStartTime() != null) {

                if (stubbedEvent.getEndTime() != null) {
                    newInput = newInput + stubbedEvent.getStartTime() + "-";
                    newInput = newInput + stubbedEvent.getEndTime();
                } else{
                    newInput = newInput + stubbedEvent.getStartTime();
                }
            }
        }

        System.out.println(newInput);
        return newInput;

    }
}
