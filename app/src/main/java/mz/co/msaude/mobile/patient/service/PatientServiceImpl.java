package mz.co.msaude.mobile.patient.service;

import javax.inject.Inject;

import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.patient.model.Patient;
import mz.co.msaude.mobile.patient.resource.PatientResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PatientServiceImpl implements PatientService {

    @Inject
    Retrofit retrofit;

    @Inject
    public PatientServiceImpl() {
    }

    @Override
    public void createPatient(String token, Patient patient, final ResponseListner<Patient> listner) {
        PatientResource patientResource = retrofit.create(PatientResource.class);

        patientResource.createPatient(token, patient).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if (response.isSuccessful()) {
                    listner.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                listner.error(t.getMessage());
            }
        });
    }
}
