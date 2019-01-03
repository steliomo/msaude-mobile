package mz.co.msaude.mobile.consultation.resource;

import java.util.List;

import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.ConsultationStatus;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public interface ConsultationResource {

    @POST("consultations")
    Call<Consultation> scheduleConsultation(@Header("Authorization") String token, @Body final Consultation consultation);

    @GET("consultations")
    Call<List<Consultation>> findConsultations(@Header("Authorization") String token, @Query("consultationStatus") final ConsultationStatus consultationStatus);

    @PUT("consultations/{id}")
    Call<Consultation> cancelConsultation(@Header("Authorization") String token, @Path("id") final Long id, @Body Consultation consultation);
}
