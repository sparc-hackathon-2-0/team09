package dix.walton.moore.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: joshwalton
 * Date: 8/25/12
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class AsyncDeleteEvent extends AsyncTask<Void, Void, Void> {

    private final VerifyActivity calendarSample;
    private final ProgressDialog dialog;
//    private final int calendarId;
    private final String eventId;
    private com.google.api.services.calendar.Calendar client;

    public AsyncDeleteEvent(VerifyActivity calendarSample, String eventIdInput) {
        this.calendarSample = calendarSample;
        client = calendarSample.client;
//        this.calendarId = calendarId;
        this.eventId = eventIdInput;
        dialog = new ProgressDialog(calendarSample);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Deleting calendar...");
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            client.events().delete(calendarSample.calendar.getId(), eventId).execute();
        } catch (IOException e) {
            calendarSample.handleGoogleException(e);
        } finally {
            calendarSample.onRequestCompleted();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        dialog.dismiss();
//    calendarSample.refresh();
    }
}
