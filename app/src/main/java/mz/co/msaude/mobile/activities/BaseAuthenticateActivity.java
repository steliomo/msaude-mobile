package mz.co.msaude.mobile.activities;

import android.os.Bundle;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public abstract class BaseAuthenticateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onMhealthCreate(savedInstanceState);
    }

    public abstract void onMhealthCreate(Bundle bundle);
}
