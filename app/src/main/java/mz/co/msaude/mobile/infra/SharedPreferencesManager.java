package mz.co.msaude.mobile.infra;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String MSAUDE_PREF_NAME = "MSAUDE PREF_NAME";
    private static final String TOKEN = "TOKEN";

    private Context context;

    public SharedPreferencesManager(Context context) {
        this.context = context;
    }

    public void login(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);

        editor.apply();
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(MSAUDE_PREF_NAME, context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {

        String token = getString();

        if (token != null) {
            return Boolean.TRUE;
        }

        return false;
    }

    private String getString() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getString(TOKEN, null);
    }

    public void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

    public String getToken() {
        return getString();
    }
}
