package mz.co.msaude.mobile.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.ConsultationAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.ConsultationStatus;
import mz.co.msaude.mobile.consultation.service.ConsultationService;
import mz.co.msaude.mobile.delegate.ConsultationDelegate;
import mz.co.msaude.mobile.dialog.AlertDialogManager;
import mz.co.msaude.mobile.dialog.ProgressDialogManager;
import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.listner.VerticalMenuClickListner;

public class ConsultationMainFragment extends BaseFragment implements VerticalMenuClickListner<Consultation> {

    private static final String FRAGMENT_POSITION = "FRAGMENT_POSITION";

    @BindView(R.id.fragment_consultation_main_recycle_view)
    RecyclerView recyclerView;

    @Inject
    ConsultationService consultationService;

    private ConsultationDelegate delegate;

    private ProgressDialog progressBar;

    private AlertDialogManager alertDialogManager;

    private ProgressDialogManager progressDialogManager;

    private ConsultationStatus consultationStatus;

    @Override
    public int getResourceId() {
        return R.layout.fragment_consultation_main;
    }

    @Override
    public void onCreateView() {
        delegate = (ConsultationDelegate) getActivity();

        SaudeComponent component = application.getComponent();
        component.inject(this);

        alertDialogManager = new AlertDialogManager(getActivity());
        progressDialogManager = new ProgressDialogManager(getActivity());

        progressBar = progressDialogManager.getProgressBar(getString(R.string.wait), getString(R.string.processing_request));
    }

    private void loadData() {

        progressBar.show();

        consultationService.findConsultationsByStatus(application.getToken(), consultationStatus, new ResponseListner<List<Consultation>>() {

            @Override
            public void success(List<Consultation> consultations) {
                progressBar.dismiss();

                if (consultations == null) {
                    alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
                    return;
                }

                ConsultationAdapter adapter = new ConsultationAdapter(getActivity(), consultations);
                adapter.setOnVerticalMenuClickListner(ConsultationMainFragment.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && isResumed()) {
            setConsultationStatus();
            loadData();
        }
    }

    private void setConsultationStatus() {

        Bundle arguments = getArguments();
        int position = arguments.getInt(FRAGMENT_POSITION);

        switch (position) {
            case 0:
                consultationStatus = ConsultationStatus.valueOf(arguments.getString(ConsultationStatus.PENDING.toString()));
                break;

            case 1:
                consultationStatus = ConsultationStatus.valueOf(arguments.getString(ConsultationStatus.ACCEPTED.toString()));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            setConsultationStatus();
            loadData();
        }
    }

    @Override
    public void perform(View view, Consultation consultation) {
        delegate.onVerticalMenuClickListner(view, consultation);
    }

    public void onRefresh() {
        loadData();
    }
}
