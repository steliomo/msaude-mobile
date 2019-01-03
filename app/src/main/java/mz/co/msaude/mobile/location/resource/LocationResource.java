package mz.co.msaude.mobile.location.resource;

import java.util.List;

import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface LocationResource {

    @GET("provinces")
    Call<List<Province>> findAllProvinces(@Header("Authorization") String token);

    @GET("localities/{provinceUuid}")
    Call<List<Locality>> findAllLocalities(@Header("Authorization") String token, @Path("provinceUuid") String provinceUuid);

    @GET("health-facilities/{localityUuid}")
    Call<List<HealthFacility>> findAllLHealthFacilities(@Header("Authorization") String token, @Path("localityUuid") String localityUuid);
}
