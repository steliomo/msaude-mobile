package mz.co.msaude.mobile.exam.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.adapter.ConsultationAdapter;
import mz.co.msaude.mobile.adapter.ExamAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.ConsultationStatus;
import mz.co.msaude.mobile.dialog.AlertDialogManager;
import mz.co.msaude.mobile.dialog.ProgressDialogManager;
import mz.co.msaude.mobile.exam.model.Exam;
import mz.co.msaude.mobile.exam.model.ExamStatus;
import mz.co.msaude.mobile.exam.service.ExamService;
import mz.co.msaude.mobile.fragment.BaseFragment;
import mz.co.msaude.mobile.fragment.ConsultationMainFragment;
import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.listner.VerticalMenuClickListner;


public class ExamMainFragment extends BaseFragment implements VerticalMenuClickListner<Exam> {

    private static final String FRAGMENT_POSITION = "FRAGMENT_POSITION";

    @BindView(R.id.fragment_exam_main_recycle_view)
    RecyclerView recyclerView;

    @Inject
    ExamService examService;

    private AlertDialogManager alertDialogManager;

    private ProgressDialogManager progressDialogManager;

    private ProgressDialog progressBar;

    private ExamStatus examStatus;

    @Override
    public int getResourceId() {
        return R.layout.fragment_exam_main;
    }

    @Override
    public void onCreateView() {

        SaudeComponent component = application.getComponent();
        component.inject(this);

        alertDialogManager = new AlertDialogManager(getActivity());
        progressDialogManager = new ProgressDialogManager(getActivity());

        progressBar = progressDialogManager.getProgressBar(getString(R.string.wait), getString(R.string.processing_request));
    }

    private void loadData() {

        progressBar.show();

        examService.findExamsByStatus(application.getToken(), examStatus, new ResponseListner<List<Exam>>() {
            @Override
            public void success(List<Exam> exams) {
                progressBar.dismiss();

                if (exams == null) {
                    alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
                    return;
                }

                ExamAdapter adapter = new ExamAdapter(getActivity(), exams);
                adapter.setOnVerticalMenuClickListner(ExamMainFragment.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
            }
        });
    }

    private void setExamStatus() {

        Bundle arguments = getArguments();
        int position = arguments.getInt(FRAGMENT_POSITION);

        switch (position) {
            case 0:
                examStatus = ExamStatus.valueOf(arguments.getString(ExamStatus.PENDING.toString()));
                break;

            case 1:
                examStatus = ExamStatus.valueOf(arguments.getString(ExamStatus.SCHEDULED.toString()));
                break;
        }
    }

    @Override
    public void perform(View view, Exam item) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && isResumed()) {
            setExamStatus();
            loadData();
        }
    }
}
