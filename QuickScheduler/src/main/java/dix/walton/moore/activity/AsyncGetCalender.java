package dix.walton.moore.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: joshwalton
 * Date: 8/25/12
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class AsyncGetCalender extends AsyncTask<Void, Void, Void> {

        private final MenuActivity calendarSample;
        private final ProgressDialog dialog;
        private com.google.api.services.calendar.Calendar client;
        private String calenderId;
        public AsyncGetCalender(MenuActivity calendarSample, String calId) {
            this.calendarSample = calendarSample;
            this.calenderId = calId;
            client = calendarSample.client;
            dialog = new ProgressDialog(calendarSample);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Loading calendars...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                calendarSample.calendar = client.calendarList().get(calenderId).execute();
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

