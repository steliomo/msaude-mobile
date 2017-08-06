package mz.co.txova.msaude.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.adapter.ConsultationAdapter;
import mz.co.txova.msaude.consultation.model.Consultation;
import mz.co.txova.msaude.consultation.model.ConsultationType;
import mz.co.txova.msaude.doctor.model.Doctor;
import mz.co.txova.msaude.healthfacility.model.HealthFacility;

public class ConsultationsActivity extends BaseAuthenticateActivity {

    @BindView(R.id.add_new_consultation)
    ImageButton addNewconsultation;

    @BindView(R.id.consultations)
    ListView consultations;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_consultations);
        toolbar.setTitle("Consultas");

        Consultation consultation = new Consultation(new ConsultationType("Pediatria", getResources().getIdentifier("ic_stethoscope_icon", "mipmap", getPackageName())), new Doctor("Alima", "Moiane", "Pediatra"), new HealthFacility("Clinicare", "clinicare@gmail.com", null, null), Calendar.getInstance().getTime());
        List<Consultation> consultationList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            consultationList.add(consultation);
        }

        ConsultationAdapter adapter = new ConsultationAdapter(this, consultationList);
        consultations.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consultations, menu);
        return true;
    }

    @OnClick(R.id.add_new_consultation)
    public void OnclickAddNewConsultation() {
        Toast.makeText(this, "Nova consulta", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SearchConsultationActivity.class));
    }
}
