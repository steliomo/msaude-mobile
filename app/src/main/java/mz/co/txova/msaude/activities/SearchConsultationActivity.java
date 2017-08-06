package mz.co.txova.msaude.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.consultation.model.ConsultationFilter;
import mz.co.txova.msaude.consultation.model.QueryResult;
import mz.co.txova.msaude.consultation.service.ConsultationService;
import mz.co.txova.msaude.validator.TextViewValidator;

public class SearchConsultationActivity extends BaseAuthenticateActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.consultation_city)
    AutoCompleteTextView consultationCity;

    @BindView(R.id.consultation_type)
    AutoCompleteTextView consultationType;

    @BindView(R.id.consultation_clinic)
    AutoCompleteTextView consultationClinic;

    @BindView(R.id.consultation_doctor)
    AutoCompleteTextView consultationDoctor;

    @BindView(R.id.consultation_date)
    EditText consultationDate;

    @BindView(R.id.consultation_date_picker)
    ImageButton consultationDatePicker;

    @BindView(R.id.consultation_search)
    Button consulationSearch;

    @BindView(R.id.consultation_cancel)
    Button consultationCancel;

    @Inject
    TextViewValidator validator;

    @Inject
    ConsultationService consultationService;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_search_consultation);
        toolbar.setTitle("Pesquisa de Consultas");

        SaudeComponent component = application.getComponent();
        component.inject(this);

        ArrayAdapter<String> consultationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Arrays.asList("Maputo", "Matola", "Beira", "Quelimane", "Neslpruit"));
        consultationCity.setAdapter(consultationAdapter);

        ArrayAdapter<String> consultationTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Arrays.asList("Acupuntura", "Alergia e Imunologia", "Cardiologia", "Cirurgia Geral", "Clinica Geral", "Dermatologia"));
        consultationType.setAdapter(consultationTypeAdapter);

        ArrayAdapter<String> consultationClinicAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Arrays.asList("Hospital Privado", "Clinicare", "Clinica 222", "Consultórios Médicos", "Policlinic"));
        consultationClinic.setAdapter(consultationClinicAdapter);

        ArrayAdapter<String> consultationDoctorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Arrays.asList("Rui Bastos", "Alima Moiane"));
        consultationDoctor.setAdapter(consultationDoctorAdapter);

        validator.addViews(consultationCity, consultationType);
    }

    @OnClick(R.id.consultation_date_picker)
    public void onClickConsultationDatePicker() {
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

        consultationDate.setText(date);
    }

    @OnClick(R.id.consultation_search)
    public void onClickConsultationSearch() {

        if (!validator.isValid()) {
            return;
        }

        Toast.makeText(this, "A pesquisar ....", Toast.LENGTH_SHORT).show();

        ConsultationFilter consultationFilter = new ConsultationFilter(consultationCity.getText().toString(),
                consultationType.getText().toString(),
                consultationClinic.getText().toString(),
                consultationDoctor.getText().toString(),
                consultationDate.getText().toString());

        QueryResult result = consultationService.findConsultationsByFilter(consultationFilter);

        Intent intent = new Intent(this, ScheduleConsultationActivity.class);
        intent.putExtra(ConsultationFilter.FILTER, consultationFilter);
        intent.putExtra(QueryResult.QUERY_RESULT, result);

        startActivity(intent);
        finish();
    }
}
