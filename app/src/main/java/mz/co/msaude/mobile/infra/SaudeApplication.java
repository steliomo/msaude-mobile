package mz.co.msaude.mobile.infra;

import android.app.Application;

import mz.co.msaude.mobile.component.DaggerSaudeComponent;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.module.SaudeModule;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public class SaudeApplication extends Application {

    private SaudeComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerSaudeComponent.builder().saudeModule(new SaudeModule(this)).build();
    }

    public SaudeComponent getComponent() {
        return component;
    }
}
