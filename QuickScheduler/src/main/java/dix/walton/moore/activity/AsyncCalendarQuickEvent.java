package dix.walton.moore.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.google.api.services.calendar.model.Event;
import dix.walton.moore.activity.MenuActivity;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: joshwalton
 * Date: 8/25/12
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class AsyncCalendarQuickEvent extends AsyncTask<Void, Void, Void> {

    private final MenuActivity calendarSample;
    private final ProgressDialog dialog;
//    private final Calendar entry;
    private com.google.api.services.calendar.Calendar client;

    public AsyncCalendarQuickEvent(MenuActivity calendarSample) {
        this.calendarSample = calendarSample;
        client = calendarSample.client;
        dialog = new ProgressDialog(calendarSample);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Inserting Quick Event...");
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            com.google.api.services.calendar.Calendar.Events.QuickAdd quickAdd = null;
            quickAdd = client.events().quickAdd("primary", "TEST EVENT TODAY AT 3");
            Event event = quickAdd.execute();

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
//        calendarSample.refresh();
    }
}
