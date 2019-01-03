package mz.co.msaude.mobile.doctor.service;

import java.util.List;

import javax.inject.Inject;

import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.doctor.resource.DoctorResource;
import mz.co.msaude.mobile.listner.ResponseListner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DoctorServiceImpl implements DoctorService {

    @Inject
    Retrofit retrofit;

    @Inject
    public DoctorServiceImpl() {
    }

    @Override
    public void findDoctorsByConsultationTypeAndHealthFacilityAndConsultationDate(String token, String consultationType, String healthFacility, String consultationDate, final ResponseListner<List<Doctor>> listner) {

        DoctorResource resource = retrofit.create(DoctorResource.class);

        resource.findDoctorsByConsultationTypeAndHealthFacilityAndConsultationDate(token, consultationType, healthFacility, consultationDate)
                .enqueue(new Callback<List<Doctor>>() {
                    @Override
                    public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                        listner.success(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Doctor>> call, Throwable t) {
                        listner.error(t.getMessage());
                    }
                });
    }
}
