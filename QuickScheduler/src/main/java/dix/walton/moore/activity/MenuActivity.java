package dix.walton.moore.activity;

import android.accounts.*;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.common.collect.Lists;
import dix.walton.moore.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import dix.walton.moore.util.GoogleEventTransformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuActivity extends Activity {
    /** Logging level for HTTP requests/responses. */
    private static final Level LOGGING_LEVEL = Level.OFF;

    private static final String TAG = "CalendarSample";

    private static final String AUTH_TOKEN_TYPE = "cl";

    private static final int MENU_ACCOUNTS = 0;

    private static final int MENU_ADD = 1;

    private static final int MENU_QUICKADD = 2;

    private static final int CONTEXT_EDIT = 0;

    private static final int CONTEXT_DELETE = 1;

    private static final int REQUEST_AUTHENTICATE = 0;

    final HttpTransport transport = AndroidHttp.newCompatibleTransport();

    final JsonFactory jsonFactory = new GsonFactory();

    static final String PREF_ACCOUNT_NAME = "accountName";

    static final String PREF_AUTH_TOKEN = "authToken";

    GoogleAccountManager accountManager;

    SharedPreferences sharedPreferencesSettings;

    String accountName;

    String authToken;

    CalendarListEntry calendar;

    public com.google.api.services.calendar.Calendar client;

    List<CalendarInfo> calendars = Lists.newArrayList();

    private boolean received401;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.menu);

            final Button mic = (Button) findViewById(R.id.mic);

            final Button settings = (Button) findViewById(R.id.settings);

            settings.setOnClickListener(new SettingsButtonHandler());
            mic.setOnClickListener(new MicButtonHandler());

        HttpRequestInitializer requestInitializer = new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
                request.getHeaders().setAuthorization(GoogleHeaders.getGoogleLoginValue(authToken));
            }
        };
        client = new com.google.api.services.calendar.
                Calendar.Builder(transport, jsonFactory, requestInitializer)
                .setApplicationName("Google-CalendarAndroidSample/1.0")
                .setJsonHttpRequestInitializer(new GoogleKeyInitializer(ClientCredentials.KEY))
                .build();
        sharedPreferencesSettings = getPreferences(MODE_PRIVATE);
        accountName = sharedPreferencesSettings.getString(PREF_ACCOUNT_NAME, null);
        authToken = sharedPreferencesSettings.getString(PREF_AUTH_TOKEN, null);
        Logger.getLogger("com.google.api.client").setLevel(LOGGING_LEVEL);
        accountManager = new GoogleAccountManager(this);
//        registerForContextMenu(getListView());
        gotAccount();
    }


    void gotAccount() {
        Account account = accountManager.getAccountByName(accountName);
        if (account == null) {
            chooseAccount();
            return;
        }
        if (authToken != null) {
            onAuthToken();
            return;
        }
        accountManager.getAccountManager()
                .getAuthToken(account, AUTH_TOKEN_TYPE, true, new AccountManagerCallback<Bundle>() {

                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            Bundle bundle = future.getResult();
                            if (bundle.containsKey(AccountManager.KEY_INTENT)) {
                                Intent intent = bundle.getParcelable(AccountManager.KEY_INTENT);
                                intent.setFlags(intent.getFlags() & ~Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivityForResult(intent, REQUEST_AUTHENTICATE);
                            } else if (bundle.containsKey(AccountManager.KEY_AUTHTOKEN)) {
                                setAuthToken(bundle.getString(AccountManager.KEY_AUTHTOKEN));
                                onAuthToken();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage(), e);
                        }
                    }
                }, null);
    }

    private void chooseAccount() {
        accountManager.getAccountManager().getAuthTokenByFeatures(GoogleAccountManager.ACCOUNT_TYPE,
                AUTH_TOKEN_TYPE,
                null,
                MenuActivity.this,
                null,
                null,
                new AccountManagerCallback<Bundle>() {

                    public void run(AccountManagerFuture<Bundle> future) {
                        Bundle bundle;
                        try {
                            bundle = future.getResult();
                            setAccountName(bundle.getString(AccountManager.KEY_ACCOUNT_NAME));
                            setAuthToken(bundle.getString(AccountManager.KEY_AUTHTOKEN));
                            onAuthToken();
                        } catch (OperationCanceledException e) {
                            // user canceled
                        } catch (AuthenticatorException e) {
                            Log.e(TAG, e.getMessage(), e);
                        } catch (IOException e) {
                            Log.e(TAG, e.getMessage(), e);
                        }
                    }
                },
                null);
    }

    void setAccountName(String accountName) {
        SharedPreferences.Editor editor = sharedPreferencesSettings.edit();
        editor.putString(PREF_ACCOUNT_NAME, accountName);
        editor.commit();
        this.accountName = accountName;
    }

    void setAuthToken(String authToken) {
        SharedPreferences.Editor editor = sharedPreferencesSettings.edit();
        editor.putString(PREF_AUTH_TOKEN, authToken);
        editor.commit();
        this.authToken = authToken;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AUTHENTICATE:
                if (resultCode == RESULT_OK) {
                    gotAccount();
                } else {
                    chooseAccount();
                }
                break;
            case 1234:
                handleVoiceEntryCompleted(data);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_ADD, 0, "New Calendar");
        if (accountManager.getAccounts().length >= 2) {
            menu.add(0, MENU_ACCOUNTS, 0, "Switch Account");
        }
        menu.add(0, MENU_QUICKADD, 0, "QUICK ADD YO");
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ADD:
                Calendar entry = new Calendar();
                entry.setSummary("Calendar " + new DateTime(new Date()));
                new AsyncInsertCalendar(this, entry).execute();
                return true;
            case MENU_QUICKADD:
                new AsyncCalendarQuickEvent(this).execute();
                return true;
            case MENU_ACCOUNTS:
                chooseAccount();
                return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CONTEXT_EDIT, 0, "Update Title");
        menu.add(0, CONTEXT_DELETE, 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int calendarIndex = (int) info.id;
        CalendarInfo calendarInfo = calendars.get(calendarIndex);

        switch (item.getItemId()) {
            case CONTEXT_EDIT:
                Calendar entry = new Calendar();
                entry.setSummary(calendarInfo.summary + " UPDATED " + new DateTime(new Date()));
                new AsyncUpdateCalendar(this, calendarIndex, entry).execute();
                return true;
            case CONTEXT_DELETE:
                new AsyncDeleteCalendar(this, calendarIndex).execute();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    void onAuthToken() {
        new AsyncLoadCalendars(this).execute();
    }

    public void onRequestCompleted() {
        received401 = false;
    }

    public void handleGoogleException(final IOException e) {
        if (e instanceof GoogleJsonResponseException) {
            GoogleJsonResponseException exception = (GoogleJsonResponseException) e;
            if (exception.getStatusCode() == 401 && !received401) {
                received401 = true;
                accountManager.invalidateAuthToken(authToken);
                authToken = null;
                SharedPreferences.Editor editor2 = sharedPreferencesSettings.edit();
                editor2.remove(PREF_AUTH_TOKEN);
                editor2.commit();
                gotAccount();
            }
        }
        Log.e(TAG, e.getMessage(), e);
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(MenuActivity.this).setTitle("Exception").setMessage(
                        e.getMessage()).setNeutralButton("ok", null).create().show();
            }
        });
    }

    private class SettingsButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            handleSettingsButtonClick();
        }
    }

    private class MicButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            handleMicButtonClick();
        }
    }

    public void handleSettingsButtonClick() {
        startActivity(new Intent(this, SettingsActivity.class));
        finish();
    }

    public void handleMicButtonClick() {

        doVoice();

    }

    private void doVoice() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your event info.");
        startActivityForResult(intent, 1234);
    }

    private void handleVoiceEntryCompleted(Intent data) {

        ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        String firstString = matches.get(0);
        System.out.println(firstString);

        AsyncCalendarQuickEvent calendarQuickEventActivity = new AsyncCalendarQuickEvent(this);
        calendarQuickEventActivity.setEventString(firstString);
        calendarQuickEventActivity.execute();
        Event result = null;
        while(result == null)
        {
            try{
                Thread.sleep(1000);
                result = calendarQuickEventActivity.getReturnedEvent();
            }catch (InterruptedException ie ){
                System.out.println("bad stuff happened");
            }

        }

        Intent verifyIntent = new Intent(this, VerifyActivity.class);
        dix.walton.moore.model.Event ourEvent = GoogleEventTransformer.convertToOurEvent(result);
        verifyIntent.putExtra("event", ourEvent);
        startActivity(verifyIntent);
    }
}