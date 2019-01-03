package mz.co.msaude.mobile.medicalservicetype.resource;

import java.util.List;

import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;
import mz.co.msaude.mobile.medicalservicetype.model.ServiceType;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MedicalServiceTypeResource {

    @GET("medical-service-types")
    Call<List<MedicalServiceType>> findMedicalServiceTypes(@Header("Authorization") String token, @Query("serviceType") ServiceType serviceType);
}
