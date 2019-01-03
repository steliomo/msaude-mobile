package mz.co.msaude.mobile.consultation.service;

import java.util.List;

import javax.inject.Inject;

import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.ConsultationStatus;
import mz.co.msaude.mobile.consultation.resource.ConsultationResource;
import mz.co.msaude.mobile.listner.ResponseListner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public class ConsultationServiceImpl implements ConsultationService {

    @Inject
    Retrofit retrofit;

    @Inject
    public ConsultationServiceImpl() {
    }

    private ConsultationResource getResource() {
        return retrofit.create(ConsultationResource.class);
    }

    @Override
    public void scheduleConsultation(String token, Consultation consultation, final ResponseListner<Consultation> listner) {

        getResource().scheduleConsultation(token, consultation)
                .enqueue(new Callback<Consultation>() {
                    @Override
                    public void onResponse(Call<Consultation> call, Response<Consultation> response) {
                        listner.success(response.body());
                    }

                    @Override
                    public void onFailure(Call<Consultation> call, Throwable t) {
                        listner.error(t.getMessage());
                    }
                });
    }

    @Override
    public void findConsultationsByStatus(String token, ConsultationStatus consultationStatus, final ResponseListner<List<Consultation>> listner) {

        getResource().findConsultations(token, consultationStatus)
                .enqueue(new Callback<List<Consultation>>() {
                    @Override
                    public void onResponse(Call<List<Consultation>> call, Response<List<Consultation>> response) {
                        listner.success(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Consultation>> call, Throwable t) {
                        listner.error(t.getMessage());
                    }
                });
    }

    @Override
    public void cancelConsultation(final String token, final Long id, final Consultation consultation, final ResponseListner<Consultation> listner) {
        getResource().cancelConsultation(token, id, consultation)
                .enqueue(new Callback<Consultation>() {
                    @Override
                    public void onResponse(Call<Consultation> call, Response<Consultation> response) {
                        listner.success(response.body());
                    }

                    @Override
                    public void onFailure(Call<Consultation> call, Throwable t) {
                        listner.error(t.getMessage());
                    }
                });
    }
}
