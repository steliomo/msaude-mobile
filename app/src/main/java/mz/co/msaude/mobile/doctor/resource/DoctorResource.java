package mz.co.msaude.mobile.doctor.resource;

import java.util.List;

import mz.co.msaude.mobile.doctor.model.Doctor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface DoctorResource {

    @GET("doctors")
    Call<List<Doctor>> findDoctorsByConsultationTypeAndHealthFacilityAndConsultationDate(@Header("Authorization") final String token, @Query("consultationType") final String consultationType,
                                                                                         @Query("healthFacility") final String healthFacility,
                                                                                         @Query("consultationDate") final String consultationDate);

}
