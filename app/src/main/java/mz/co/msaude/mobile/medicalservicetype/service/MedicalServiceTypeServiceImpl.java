package mz.co.msaude.mobile.medicalservicetype.service;

import java.util.List;

import javax.inject.Inject;

import mz.co.msaude.mobile.consultation.resource.ConsultationResource;
import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;
import mz.co.msaude.mobile.medicalservicetype.model.ServiceType;
import mz.co.msaude.mobile.medicalservicetype.resource.MedicalServiceTypeResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MedicalServiceTypeServiceImpl implements MedicalServiceTypeService {

    @Inject
    Retrofit retrofit;

    @Inject
    public MedicalServiceTypeServiceImpl() {
    }

    public MedicalServiceTypeResource getResource() {
        return retrofit.create(MedicalServiceTypeResource.class);
    }

    @Override
    public void findMedicalServiceTypes(final String token, final ServiceType serviceType, final ResponseListner<List<MedicalServiceType>> listner) {
        getResource().findMedicalServiceTypes(token, serviceType).enqueue(new Callback<List<MedicalServiceType>>() {
            @Override
            public void onResponse(Call<List<MedicalServiceType>> call, Response<List<MedicalServiceType>> response) {
                listner.success(response.body());
            }

            @Override
            public void onFailure(Call<List<MedicalServiceType>> call, Throwable t) {
                listner.error(t.getMessage());
            }
        });
    }
}
