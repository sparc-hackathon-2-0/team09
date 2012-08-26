package dix.walton.moore.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

        verifyTitleInput = (EditText) findViewById(R.id.verifyTitleInput);
        verifyDateInput = (EditText) findViewById(R.id.verifyDateInput);
        verifyStartTimeInput = (EditText) findViewById(R.id.verifyStartTimeInput);
        verifyEndTimeInput = (EditText) findViewById(R.id.verifyEndTimeInput);
        verifyLocationInput = (EditText) findViewById(R.id.verifyLocaionInput);
        Button verifyButton = (Button) findViewById(R.id.verifyButton);
        Button cancel = (Button) findViewById(R.id.cancelButton);

        verifyButton.setOnClickListener(new verifyOnclickListener());
        cancel.setOnClickListener(new CancelOnclickListener());

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
    private class CancelOnclickListener implements View.OnClickListener{

        public void onClick(View v) {
            handleCancelEvent();
       }
    }

    public void handleCancelEvent() {
        new AsyncDeleteEvent(this, serviceEvent.getId());
        finish();
    }
    public void handleUpdateEvent(){
        Event stubbedEvent = new Event();
        if(verifyEndTimeInput.getText().toString() != null){
            stubbedEvent.setEndTime(verifyEndTimeInput.getText().toString());
        }
        if(verifyDateInput.getText().toString()!=null){
            stubbedEvent.setEventDate(verifyDateInput.getText().toString());
        }
        stubbedEvent.setId(serviceEvent.getId());
        if(verifyLocationInput.getText().toString()!=null){
            stubbedEvent.setLocation(verifyLocationInput.getText().toString());
        }
        if(verifyStartTimeInput.getText().toString()!= null) {
            stubbedEvent.setStartTime(verifyStartTimeInput.getText().toString());
        }
        if(verifyTitleInput.getText().toString()!= null) {
            stubbedEvent.setTitle(verifyTitleInput.getText().toString());
        }
        if(isEventChanged(stubbedEvent)){
            new AsyncDeleteEvent(this, serviceEvent.getId());
            String newInput = getGoogleCallString(stubbedEvent);
            AsyncCalendarQuickEvent calendarQuickEventActivity = new AsyncCalendarQuickEvent(this);
            calendarQuickEventActivity.setEventString(newInput);
            calendarQuickEventActivity.execute();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Success!");
            builder.setMessage("Your event was created successfully.");
            builder.setNeutralButton("OK", (DialogInterface.OnClickListener) new OKButtonHandler());
            builder.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Success!");
            builder.setMessage("Your event was created successfully.");
            builder.setNeutralButton("OK", (DialogInterface.OnClickListener) new OKButtonHandler());
            builder.show();
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

        if (!event.equals(serviceEvent)) {
            return true;
        } else {
            return false;
        }
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

    private class OKButtonHandler implements DialogInterface.OnClickListener {

        public void onClick(DialogInterface arg0, int arg1) {
            handleOKButtonClick();
        }
    }

    public void handleOKButtonClick() {
        finish();
    }

}
