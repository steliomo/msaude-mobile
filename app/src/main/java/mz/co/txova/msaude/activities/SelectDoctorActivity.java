package mz.co.txova.msaude.activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.adapter.DoctorAdapter;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.doctor.dto.DoctorDTO;
import mz.co.txova.msaude.doctor.event.DoctorEvent;
import mz.co.txova.msaude.doctor.model.Doctor;

public class SelectDoctorActivity extends BaseAuthenticateActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.doctors_list)
    ListView doctorsList;

    @Inject
    EventBus eventBus;

    private List<Doctor> doctors;
    private DoctorAdapter doctorAdapter;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_select_doctor);
        toolbar.setTitle("Médicos");

        SaudeComponent component = application.getComponent();
        component.inject(this);

        doctors = Arrays.asList(new Doctor("Nailah", "Moiane", "Especialista em Dermatologia"), new Doctor("Alima", "Moiane", "Cardiologista"), new Doctor("Kamilah", "Moiane", "Medicina Geral"));
        doctorAdapter = new DoctorAdapter(this, doctors);
        doctorsList.setAdapter(doctorAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        ArrayList<Doctor> newDoctors = new ArrayList<>();

        for (Doctor doctor : this.doctors) {
            if (doctor.getFullName().toLowerCase().contains(query)) {
                newDoctors.add(doctor);
            }
        }

        doctorAdapter.setFilter(newDoctors);
        return true;
    }

    @OnItemClick(R.id.doctors_list)
    public void onItemnClick(int position) {

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setDoctor(doctorAdapter.getItem(position));

        eventBus.post(new DoctorEvent(doctorDTO));
        finish();
    }
}
