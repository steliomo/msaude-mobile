package mz.co.msaude.mobile.patient.resource;

import mz.co.msaude.mobile.patient.model.Patient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PatientResource {

    @POST("patients")
    Call<Patient> createPatient(@Header("Authorization") String token, @Body Patient patient);

}
