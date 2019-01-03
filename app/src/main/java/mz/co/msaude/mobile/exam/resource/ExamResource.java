package mz.co.msaude.mobile.exam.resource;

import java.util.List;

import mz.co.msaude.mobile.exam.model.Exam;
import mz.co.msaude.mobile.exam.model.ExamStatus;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ExamResource {

    @POST("exams")
    Call<Exam> scheduleExam(@Header("Authorization") String token, @Body Exam exam);

    @GET("exams")
    Call<List<Exam>> findExamsByStatus(@Header("Authorization") String token, @Query("examStatus") ExamStatus examStatus);
}
