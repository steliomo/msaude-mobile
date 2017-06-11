package mz.co.txova.msaude.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mz.co.txova.msaude.R;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        ButterKnife.bind(this);

        if (toolbar != null) {

            toolbar.setTitle("");
            toolbar.setNavigationIcon(R.mipmap.ic_menu);
            toolbar.setTitleTextColor(Color.WHITE);

            setSupportActionBar(toolbar);
        }
    }
}
