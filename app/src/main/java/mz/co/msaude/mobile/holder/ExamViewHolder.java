package mz.co.msaude.mobile.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.exam.model.Exam;
import mz.co.msaude.mobile.listner.VerticalMenuClickListner;

/**
 * Created by steliomo on 10/16/17.
 */

public class ExamViewHolder extends BaseViewHolder {

    @BindView(R.id.exam_type)
    TextView examTypeTxt;

    @BindView(R.id.exam_province)
    TextView provinceTxt;

    @BindView(R.id.exam_locality)
    TextView localityTxt;

    @BindView(R.id.exam_health_facility)
    TextView healthFacilityTxt;

    @BindView(R.id.exam_time)
    TextView timeTxt;

    @BindView(R.id.exam_date)
    TextView dateTxt;

    @BindView(R.id.exam_patient)
    TextView patientTxt;

    @BindView(R.id.more_vertical)
    ImageView verticalMenu;

    private Exam exam;

    private VerticalMenuClickListner verticalMenuClickListner;

    public ExamViewHolder(View view) {
        super(view);
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public boolean onLongClick(View view) {
        return true;
    }

    public void bind(Exam exam) {
        this.exam = exam;
        examTypeTxt.setText(exam.getMedicalServiceType().getName());
        provinceTxt.setText(exam.getHealthFacility().getLocality().getProvince().getName());
        localityTxt.setText(exam.getHealthFacility().getLocality().getName());
        dateTxt.setText(exam.getExamDate());
        healthFacilityTxt.setText(exam.getHealthFacility().getName());
        timeTxt.setText(exam.getExamTime() == null ? "Aguarda Confirmação" : exam.getExamTime());
        patientTxt.setText(exam.getPatient());
    }

    @OnClick(R.id.more_vertical)
    public void onClickMoreVertical() {
        verticalMenuClickListner.perform(verticalMenu, exam);
    }

    public void setOnVerticalMenuClickListner(VerticalMenuClickListner<Exam> verticalMenuClickListner) {
        this.verticalMenuClickListner = verticalMenuClickListner;
    }
}
