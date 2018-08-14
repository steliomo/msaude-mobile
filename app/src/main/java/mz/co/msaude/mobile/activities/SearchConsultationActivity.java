package mz.co.msaude.mobile.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.event.ConsultationTypeEvent;
import mz.co.msaude.mobile.consultation.model.ConsultationFilter;
import mz.co.msaude.mobile.consultation.model.QueryResult;
import mz.co.msaude.mobile.consultation.service.ConsultationService;
import mz.co.msaude.mobile.doctor.event.DoctorEvent;
import mz.co.msaude.mobile.healthfacility.event.CityEvent;
import mz.co.msaude.mobile.healthfacility.event.HealthFacilityEvent;
import mz.co.msaude.mobile.validator.TextViewValidator;

public class SearchConsultationActivity extends BaseAuthenticateActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.consultation_city)
    EditText consultationCity;

    @BindView(R.id.consultation_type)
    EditText consultationType;

    @BindView(R.id.consultation_type_btn)
    ImageButton consultationTypeBtn;

    @BindView(R.id.consultation_clinic)
    EditText consultationClinic;

    @BindView(R.id.consultation_clinic_btn)
    ImageButton consultationClinicBtn;

    @BindView(R.id.consultation_doctor)
    EditText consultationDoctor;

    @BindView(R.id.consultation_doctor_btn)
    ImageButton consultationDoctorBtn;

    @BindView(R.id.consultation_date)
    EditText consultationDate;

    @BindView(R.id.consultation_date_picker)
    ImageButton consultationDatePicker;

    @BindView(R.id.consultation_city_btn)
    ImageButton consultationCityBtn;

    @BindView(R.id.consultation_search)
    Button consulationSearch;

    @BindView(R.id.consultation_cancel)
    Button consultationCancel;

    @Inject
    TextViewValidator validator;

    @Inject
    ConsultationService consultationService;

    @Inject
    EventBus eventBus;

    private ConsultationFilter consultationFilter;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_search_consultation);
        toolbar.setTitle("Pesquisa de Consultas");

        SaudeComponent component = application.getComponent();
        component.inject(this);
        eventBus.register(this);

        validator.addViews(consultationCity, consultationType);
        this.consultationFilter = new ConsultationFilter();
    }

    @OnClick(R.id.consultation_date_picker)
    public void onClickConsultationDatePicker() {
        showDatePicker();
    }

    private void showDatePicker() {
        Calendar instance = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, instance.get(Calendar.YEAR),
                instance.get(Calendar.MONTH),
                instance.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @OnClick(R.id.consultation_cancel)
    public void onClickConcultationCancel() {
        consultationCity.setText("");
        consultationType.setText("");
        consultationClinic.setText("");
        consultationDoctor.setText("");
        consultationDate.setText("");

        consultationCity.requestFocus();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = StringUtils.leftPad((dayOfMonth) + "", 2, "0") + "-" +
                StringUtils.leftPad((monthOfYear + 1) + "", 2, "0") + "-" +
                year;

        consultationFilter.setConsultationDate(date);
        consultationDate.setText(consultationFilter.getConsultationDate());
    }

    @OnClick(R.id.consultation_search)
    public void onClickConsultationSearch() {

        if (!validator.isValid()) {
            return;
        }

        Toast.makeText(this, "A pesquisar ....", Toast.LENGTH_SHORT).show();

        QueryResult result = consultationService.findConsultationsByFilter(consultationFilter);

        Intent intent = new Intent(this, ScheduleConsultationActivity.class);
        intent.putExtra(ConsultationFilter.FILTER, consultationFilter);
        intent.putExtra(QueryResult.QUERY_RESULT, result);

        startActivity(intent);
        finish();
    }

    @OnClick(R.id.consultation_date)
    public void onClickConsultationDate() {
        showDatePicker();
    }

    @OnClick(R.id.consultation_city)
    public void onclickConsultationCity() {
        startActivity(new Intent(this, SelectCityActivity.class));
    }

    @OnClick(R.id.consultation_city_btn)
    public void onClickConsultationCityBtn() {
        startActivity(new Intent(this, SelectCityActivity.class));
    }

    @OnClick(R.id.consultation_type)
    public void onclickConsultationType() {
        startActivity(new Intent(this, SelectConsultationTypeActivity.class));
    }

    @OnClick(R.id.consultation_type_btn)
    public void onclickConsultationTypeBtn() {
        startActivity(new Intent(this, SelectConsultationTypeActivity.class));
    }

    @OnClick(R.id.consultation_clinic)
    public void onclickConsultationClinic() {
        startActivity(new Intent(this, HealthFacilitySelectActivity.class));
    }

    @OnClick(R.id.consultation_clinic_btn)
    public void onclickConsultationClinicBtn() {
        startActivity(new Intent(this, HealthFacilitySelectActivity.class));
    }

    @OnClick(R.id.consultation_doctor)
    public void onclickConsultationDoctor() {
        startActivity(new Intent(this, SelectDoctorActivity.class));
    }

    @OnClick(R.id.consultation_doctor_btn)
    public void onclickConsultationDoctorBtn() {
        startActivity(new Intent(this, SelectDoctorActivity.class));
    }

    @Subscribe
    public void onEvent(CityEvent cityEvent) {
        consultationFilter.setCity(cityEvent.getCity());
        consultationCity.setText(consultationFilter.getCity().getCity());
        consultationCity.setError(null);
    }

    @Subscribe
    public void onEvent(ConsultationTypeEvent consultationTypeEvent) {
        consultationFilter.setConsultationType(consultationTypeEvent.getConsultationType().getConsultationType());
        consultationType.setText(consultationFilter.getConsultationType());
        consultationType.setError(null);
    }

    @Subscribe
    public void onEvent(HealthFacilityEvent healthFacilityEvent) {
        consultationFilter.setHealthFacility(healthFacilityEvent.getHealthFacilityDTO().getHealthFacility());
        consultationClinic.setText(consultationFilter.getHealthFacility().getName());
    }

    @Subscribe
    public void onEvent(DoctorEvent doctorEvent) {
        consultationFilter.setDoctor(doctorEvent.getDoctorDTO().getDoctor());
        consultationDoctor.setText(consultationFilter.getDoctor().getFullName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }
}