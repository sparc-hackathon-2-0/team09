/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package dix.walton.moore.activity;

import com.google.api.services.calendar.Calendar.Calendars;
import com.google.api.services.calendar.model.Calendar;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;


public class AsyncUpdateCalendar extends AsyncTask<Void, Void, Void> {

  private final MenuActivity calendarSample;
  private final ProgressDialog dialog;
  private final int calendarIndex;
  private final Calendar entry;
  private com.google.api.services.calendar.Calendar client;

  public AsyncUpdateCalendar(MenuActivity calendarSample, int calendarIndex, Calendar entry) {
    this.calendarSample = calendarSample;
    this.calendarIndex = calendarIndex;
    client = calendarSample.client;
    this.entry = entry;
    dialog = new ProgressDialog(calendarSample);
  }

  @Override
  protected void onPreExecute() {
    dialog.setMessage("Updating calendar...");
    dialog.show();
  }

  @Override
  protected Void doInBackground(Void... arg0) {
    String calendarId = calendarSample.calendars.get(calendarIndex).id;
    try {
      Calendars.Patch patch = client.calendars().patch(calendarId, entry);
      patch.setFields("id");
      Calendar updatedCalendar = patch.execute();
      calendarSample.calendars.remove(calendarIndex);
      CalendarInfo info = new CalendarInfo(updatedCalendar.getId(), entry.getSummary());
      calendarSample.calendars.add(info);
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
