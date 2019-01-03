package mz.co.msaude.mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;

public class MainActivity extends BaseAuthenticateActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

    @BindView(R.id.main_navigation_drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    public void onMhealthCreate(Bundle bundle) {
        setContentView(R.layout.activity_main);
        toolbar.setNavigationOnClickListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView usernameTxt = headerView.findViewById(R.id.navigation_drawer_username);
        usernameTxt.setText(application.getUserInfo().getFullname());


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.main_menu_logout:
                        application.logout();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        drawerLayout.closeDrawers();
                        return Boolean.TRUE;
                }

                return false;
            }
        });
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
        startActivity(new Intent(this, ExamsActivity.class));
    }

    @OnClick(R.id.main_pharmacies)
    public void onClickPharmacies() {
        Toast.makeText(this, "Farmácias", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
}
