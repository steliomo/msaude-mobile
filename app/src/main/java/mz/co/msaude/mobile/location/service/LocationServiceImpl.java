package mz.co.msaude.mobile.location.service;

import java.util.List;

import javax.inject.Inject;

import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;
import mz.co.msaude.mobile.location.resource.LocationResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationServiceImpl implements LocationService {

    @Inject
    Retrofit retrofit;

    @Inject
    public LocationServiceImpl() {
    }

    @Override
    public void findAllProvinces(String token, final ResponseListner listner) {

        LocationResource resource = getResource();

        resource.findAllProvinces(token)
                .enqueue(new Callback<List<Province>>() {
                    @Override
                    public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                        listner.success(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Province>> call, Throwable t) {
                        listner.error(t.getMessage());
                    }
                });
    }

    private LocationResource getResource() {
        return retrofit.create(LocationResource.class);
    }

    @Override
    public void findLocalityByProvinceUuid(String token, String provinceUuid, final ResponseListner<List<Locality>> listner) {

        getResource().findAllLocalities(token, provinceUuid)
                .enqueue(new Callback<List<Locality>>() {
                    @Override
                    public void onResponse(Call<List<Locality>> call, Response<List<Locality>> response) {
                        listner.success(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Locality>> call, Throwable t) {
                        listner.error(t.getMessage());
                    }
                });
    }

    @Override
    public void findHealthFacilityByLocalityUuid(String token, String localityUuid, final ResponseListner<List<HealthFacility>> listner) {

        getResource().findAllLHealthFacilities(token, localityUuid).enqueue(new Callback<List<HealthFacility>>() {

            @Override
            public void onResponse(Call<List<HealthFacility>> call, Response<List<HealthFacility>> response) {
                listner.success(response.body());
            }

            @Override
            public void onFailure(Call<List<HealthFacility>> call, Throwable t) {
                listner.error(t.getMessage());
            }
        });
    }
}
