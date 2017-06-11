package mz.co.txova.msaude.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.txova.msaude.R;

public class MainActivity extends BaseAuthenticateActivity {

    @BindView(R.id.main_consultations)
    LinearLayout consultations;

    @BindView(R.id.main_doctors)
    LinearLayout doctors;

    @BindView(R.id.main_clinics)
    LinearLayout clinics;

    @BindView(R.id.main_drugs)
    LinearLayout drugs;

    @BindView(R.id.main_exams)
    LinearLayout exams;

    @BindView(R.id.main_pharmacies)
    LinearLayout pharmacies;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.main_consultations)
    public void onClickConsultations() {
        Toast.makeText(this, "consultas", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ConsultationsActivity.class));
    }

    @OnClick(R.id.main_doctors)
    public void onClickDoctors() {
        Toast.makeText(this, "Médicos", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.main_clinics)
    public void onClickClinics() {
        Toast.makeText(this, "Clinicas", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.main_drugs)
    public void onClickDrugs() {
        Toast.makeText(this, "Medicamentos", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.main_exams)
    public void onClickExams() {
        Toast.makeText(this, "exames", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.main_pharmacies)
    public void onClickPharmacies() {
        Toast.makeText(this, "Farmácias", Toast.LENGTH_SHORT).show();
    }
}
