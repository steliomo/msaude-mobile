package mz.co.txova.msaude.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.adapter.ConsultationAdapter;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.consultation.event.ConsultationEvent;
import mz.co.txova.msaude.consultation.model.Consultation;
import mz.co.txova.msaude.consultation.model.Hour;
import mz.co.txova.msaude.doctor.model.Doctor;
import mz.co.txova.msaude.healthfacility.model.HealthFacility;

public class ConsultationsActivity extends BaseAuthenticateActivity {

    @BindView(R.id.add_new_consultation)
    ImageButton addNewconsultation;

    @BindView(R.id.consultations)
    ListView consultations;

    @Inject
    EventBus eventBus;

    private List<Consultation> consultationList;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_consultations);
        SaudeComponent component = application.getComponent();
        component.inject(this);
        eventBus.register(this);

        toolbar.setTitle("Consultas");

        Doctor alima = new Doctor("Alima", "Moiane", "Pediatra");
        HealthFacility clinicare = new HealthFacility("Clinicare", "clinicare@gmail.com", null, null);

        Consultation consultation = new Consultation("Maputo", "Pediatria");
        consultation.setHealthFacility(clinicare);
        consultation.setDoctor(alima);
        consultation.setScheduledDate("28-08-1984");
        consultation.setHour(Hour.TEN_HALF_TO_ELEVEN);
        consultationList = new ArrayList<>();

        populateConsultation(consultation);
    }

    private void populateConsultation(Consultation consultation) {
        consultationList.add(consultation);
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

    @Subscribe
    public void onEvent(ConsultationEvent event) {
        populateConsultation(event.getConsultation());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }
}
