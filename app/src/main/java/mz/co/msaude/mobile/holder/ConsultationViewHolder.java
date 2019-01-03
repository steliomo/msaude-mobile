package mz.co.msaude.mobile.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.listner.VerticalMenuClickListner;

/**
 * Created by steliomo on 10/16/17.
 */

public class ConsultationViewHolder extends BaseViewHolder {

    @BindView(R.id.consultation_type)
    TextView consultationTypeTxt;

    @BindView(R.id.consultation_province)
    TextView provinceTxt;

    @BindView(R.id.consultation_locality)
    TextView localityTxt;

    @BindView(R.id.consultation_health_facility)
    TextView healthFacilityTxt;

    @BindView(R.id.consultation_time)
    TextView timeTxt;

    @BindView(R.id.consultation_date)
    TextView dateTxt;

    @BindView(R.id.consultation_doctor)
    TextView doctorTxt;

    @BindView(R.id.consultation_patient)
    TextView patientTxt;


    @BindView(R.id.more_vertical)
    ImageView verticalMenu;

    private Consultation consultation;
    private VerticalMenuClickListner verticalMenuClickListner;

    public ConsultationViewHolder(View view) {
        super(view);
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public boolean onLongClick(View view) {
        return true;
    }

    public void bind(Consultation consultation) {
        this.consultation = consultation;
        consultationTypeTxt.setText(consultation.getMedicalServiceType().getName());
        provinceTxt.setText(consultation.getHealthFacility().getLocality().getProvince().getName());
        localityTxt.setText(consultation.getHealthFacility().getLocality().getName());
        healthFacilityTxt.setText(consultation.getHealthFacility().getName());
        dateTxt.setText(consultation.getConsultationDate());
        doctorTxt.setText(consultation.getDoctor().getFullName());
        timeTxt.setText(consultation.getConsultationTime() == null ? "Aguarda Confirmação" : consultation.getConsultationTime());
        patientTxt.setText(consultation.getPatient());
    }

    @OnClick(R.id.more_vertical)
    public void onClickMoreVertical() {
        verticalMenuClickListner.perform(verticalMenu, consultation);
    }

    public void setOnVerticalMenuClickListner(VerticalMenuClickListner<Consultation> verticalMenuClickListner) {
        this.verticalMenuClickListner = verticalMenuClickListner;
    }
}
