package mz.co.msaude.mobile.infra;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Surface;

import mz.co.msaude.mobile.user.model.User;

public class SharedPreferencesManager {

    private static final String MSAUDE_PREF_NAME = "MSAUDE_PREF_NAME";

    private static final String TOKEN = "TOKEN";

    private static final String USER_UUID = "USER_UUID";

    private static final String USER_NAME = "USER_NAME";

    private static final String USER_SURNAME = "USER_SURNAME";

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

        String token = getString(TOKEN);

        if (token != null) {
            return Boolean.TRUE;
        }

        return false;
    }

    private String getString(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getString(key, null);
    }

    public void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

    public String getToken() {
        return getString(TOKEN);
    }


    public void setUserInfo(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_UUID, user.getUuid());
        editor.putString(USER_NAME, user.getName());
        editor.putString(USER_SURNAME, user.getSurname());

        editor.apply();
    }

    public User getUserInfo() {

        User user = new User();
        user.setUuid(getString(USER_UUID));
        user.setName(getString(USER_NAME));
        user.setSurname(getString(USER_SURNAME));

        return user;
    }
}
