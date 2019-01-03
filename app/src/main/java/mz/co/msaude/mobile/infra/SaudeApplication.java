package mz.co.msaude.mobile.infra;

import android.app.Application;

import mz.co.msaude.mobile.component.DaggerSaudeComponent;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.module.SaudeModule;
import mz.co.msaude.mobile.user.model.User;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public class SaudeApplication extends Application {

    private SaudeComponent component;

    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferencesManager = new SharedPreferencesManager(this);
        component = DaggerSaudeComponent.builder().saudeModule(new SaudeModule(this)).build();
    }

    public SaudeComponent getComponent() {
        return component;
    }

    public SharedPreferencesManager getSharedPreferencesManager() {
        return sharedPreferencesManager;
    }

    public boolean isLoggedIn() {
        return sharedPreferencesManager.isLoggedIn();
    }

    public void login(final String username, final String password) {
        TokenFactory tokenFactory = new TokenFactory(username, password);
        sharedPreferencesManager.login(tokenFactory.getToken());
    }

    public void logout() {
        sharedPreferencesManager.logout();
    }

    public String getToken() {
        return sharedPreferencesManager.getToken();
    }


    public User getUserInfo() {
        return sharedPreferencesManager.getUserInfo();
    }
}
