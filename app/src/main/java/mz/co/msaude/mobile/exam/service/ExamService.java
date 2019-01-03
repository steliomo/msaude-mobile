package mz.co.msaude.mobile.exam.service;

import java.util.List;

import mz.co.msaude.mobile.exam.model.Exam;
import mz.co.msaude.mobile.exam.model.ExamStatus;
import mz.co.msaude.mobile.listner.ResponseListner;

public interface ExamService {

    void scheduleExam(final String token, final Exam exam, final ResponseListner<Exam> listner);

    void findExamsByStatus(String token, ExamStatus examStatus, ResponseListner<List<Exam>> listner);
}
