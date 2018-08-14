package mz.co.msaude.mobile.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.infra.SaudeApplication;


/**
 * Created by Stélio Moiane on 6/11/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected SaudeApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (SaudeApplication) getApplication();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        if (toolbar != null) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
        }
    }
}
