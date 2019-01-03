package mz.co.msaude.mobile.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.ServiceViewPagerAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.dialog.AlertDialogManager;
import mz.co.msaude.mobile.dialog.ProgressDialogManager;
import mz.co.msaude.mobile.exam.fragment.ExamMainFragment;
import mz.co.msaude.mobile.exam.model.ExamStatus;
import mz.co.msaude.mobile.fragment.BaseFragment;

import static mz.co.msaude.mobile.exam.model.ExamStatus.PENDING;
import static mz.co.msaude.mobile.exam.model.ExamStatus.SCHEDULED;


public class ExamsActivity extends BaseAuthenticateActivity {

    private static final String FRAGMENT_POSITION = "FRAGMENT_POSITION";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.exam_view_pager)
    ViewPager viewPager;

    @BindView(R.id.exams_tabs)
    TabLayout tabs;

    private ServiceViewPagerAdapter adapter;

    private AlertDialogManager alertDialogManager;

    private ProgressDialog progressBar;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_exams);

        SaudeComponent component = application.getComponent();
        component.inject(this);

        toolbar.setTitle(R.string.exams);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new ServiceViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(getExamMainFragment(PENDING, 0), getString(R.string.pending));
        adapter.addFragment(getExamMainFragment(SCHEDULED, 1), getString(R.string.accepted));

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

        alertDialogManager = new AlertDialogManager(this);
        ProgressDialogManager progressDialogManager = new ProgressDialogManager(this);
        progressBar = progressDialogManager.getProgressBar(getString(R.string.wait), getString(R.string.processing_request));
    }

    private BaseFragment getExamMainFragment(ExamStatus examStatus, int position) {

        ExamMainFragment examMainFragment = new ExamMainFragment();

        Bundle arguments = new Bundle();
        arguments.putString(examStatus.toString(), examStatus.toString());
        arguments.putInt(FRAGMENT_POSITION, position);

        examMainFragment.setArguments(arguments);
        return examMainFragment;
    }

    @OnClick(R.id.exam_schedule_btn)
    public void onClickScheduleBtn() {
        startActivity(new Intent(this, ScheduleExamActivity.class));
    }

}
