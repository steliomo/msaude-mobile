package mz.co.msaude.mobile.exam.service;

import java.util.List;

import javax.inject.Inject;

import mz.co.msaude.mobile.exam.model.Exam;
import mz.co.msaude.mobile.exam.model.ExamStatus;
import mz.co.msaude.mobile.exam.resource.ExamResource;
import mz.co.msaude.mobile.listner.ResponseListner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExamServiceImpl implements ExamService {

    @Inject
    Retrofit retrofit;

    @Inject
    public ExamServiceImpl() {
    }

    public ExamResource getResource() {
        return retrofit.create(ExamResource.class);
    }


    @Override
    public void scheduleExam(String token, Exam exam, final ResponseListner<Exam> listner) {

        getResource().scheduleExam(token, exam).enqueue(new Callback<Exam>() {
            @Override
            public void onResponse(Call<Exam> call, Response<Exam> response) {
                listner.success(response.body());
            }

            @Override
            public void onFailure(Call<Exam> call, Throwable t) {
                listner.error(t.getMessage());
            }
        });
    }

    @Override
    public void findExamsByStatus(String token, ExamStatus examStatus, final ResponseListner<List<Exam>> listner) {

        getResource().findExamsByStatus(token, examStatus).enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                listner.success(response.body());
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {
                listner.error(t.getMessage());
            }
        });
    }
}
