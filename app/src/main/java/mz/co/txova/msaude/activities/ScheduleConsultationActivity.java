package mz.co.txova.msaude.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.adapter.SwipeAdapter;

public class ScheduleConsultationActivity extends BaseAuthenticateActivity {

    @BindView(R.id.view_page)
    ViewPager viewPager;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_schedule_consultation);
        toolbar.setTitle("Marcação de Consultas");

        SwipeAdapter adapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
