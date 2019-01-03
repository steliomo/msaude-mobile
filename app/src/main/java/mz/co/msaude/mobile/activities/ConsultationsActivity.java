package mz.co.msaude.mobile.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.ServiceViewPagerAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.ConsultationStatus;
import mz.co.msaude.mobile.consultation.service.ConsultationService;
import mz.co.msaude.mobile.delegate.ConsultationDelegate;
import mz.co.msaude.mobile.dialog.AlertDialogManager;
import mz.co.msaude.mobile.dialog.ProgressDialogManager;
import mz.co.msaude.mobile.fragment.ConsultationMainFragment;
import mz.co.msaude.mobile.listner.AlertListner;
import mz.co.msaude.mobile.listner.ResponseListner;

import static mz.co.msaude.mobile.consultation.model.ConsultationStatus.*;

public class ConsultationsActivity extends BaseAuthenticateActivity implements ConsultationDelegate, PopupMenu.OnMenuItemClickListener {

    private static final String FRAGMENT_POSITION = "FRAGMENT_POSITION";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.consultation_view_pager)
    ViewPager viewPager;

    @BindView(R.id.consultation_tabs)
    TabLayout tabs;

    @Inject
    EventBus eventBus;

    @Inject
    ConsultationService consultationService;

    private Consultation consultation;

    private AlertDialogManager alertDialogManager;

    private ProgressDialog progressBar;

    private ServiceViewPagerAdapter adapter;

    @Override
    public void onMhealthCreate(Bundle bundle) {

        setContentView(R.layout.activity_consultations);
        SaudeComponent component = application.getComponent();
        component.inject(this);

        toolbar.setTitle(R.string.consultations);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new ServiceViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(getConsultationMainFragment(PENDING, 0), getString(R.string.pending));
        adapter.addFragment(getConsultationMainFragment(ACCEPTED, 1), getString(R.string.accepted));

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

        alertDialogManager = new AlertDialogManager(this);
        ProgressDialogManager progressDialogManager = new ProgressDialogManager(this);
        progressBar = progressDialogManager.getProgressBar(getString(R.string.wait), getString(R.string.processing_request));
    }

    @NonNull
    private ConsultationMainFragment getConsultationMainFragment(ConsultationStatus consultationStatus, int position) {
        ConsultationMainFragment pendingFragment = new ConsultationMainFragment();

        Bundle arguments = new Bundle();
        arguments.putString(consultationStatus.toString(), consultationStatus.toString());
        arguments.putInt(FRAGMENT_POSITION, position);

        pendingFragment.setArguments(arguments);
        return pendingFragment;
    }

    @OnClick(R.id.consultation_schedule_btn)
    public void onClickConsultationScheduleBtn() {
        startActivity(new Intent(ConsultationsActivity.this, ScheduleConsultationActivity.class));
    }

    @Override
    public void onVerticalMenuClickListner(View view, Consultation consultation) {
        this.consultation = consultation;

        PopupMenu popupMenu = new PopupMenu(this, view);
        showPopupMenuIcon(popupMenu);

        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.vertical_menu);
        popupMenu.show();
    }

    private void showPopupMenuIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceShowIcon = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceShowIcon.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.action_cancel:
                alertDialogManager.showAlert(getString(R.string.cancelation_confirm), R.layout.alert_dialog, new AlertListner() {
                    @Override
                    public void perform() {
                        progressBar.show();

                        consultation.setConsultationStatus(ConsultationStatus.CANCELED);
                        consultationService.cancelConsultation(application.getToken(), consultation.getId(), consultation, new ResponseListner<Consultation>() {

                            @Override
                            public void success(Consultation consultation) {
                                progressBar.dismiss();

                                if (consultation == null) {
                                    alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
                                    return;
                                }

                                alertDialogManager.showAlert(getString(R.string.consultation_canceled), R.layout.success_alert_dialog, new AlertListner() {
                                    @Override
                                    public void perform() {
                                        reloadFragmentData();
                                    }
                                });
                            }

                            @Override
                            public void error(String message) {
                                progressBar.dismiss();
                                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
                            }
                        });
                    }
                });

                return true;
        }

        return false;
    }

    private void reloadFragmentData() {
        int position = tabs.getSelectedTabPosition();
        ConsultationMainFragment fragment = (ConsultationMainFragment) adapter.getItem(position);
        fragment.onRefresh();
    }
}
