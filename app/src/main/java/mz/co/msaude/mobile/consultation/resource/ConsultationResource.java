package mz.co.msaude.mobile.consultation.resource;

import java.util.List;

import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public interface ConsultationResource {

    @GET("consultation/default")
    List<HealthFacility> findConsultations(@Query("city") final String city, @Query("consultationType") final String consultationType);

}
