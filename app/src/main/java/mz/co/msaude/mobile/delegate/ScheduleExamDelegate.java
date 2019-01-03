package mz.co.msaude.mobile.delegate;

import mz.co.msaude.mobile.exam.model.Exam;

public interface ScheduleExamDelegate extends ScheduleDelegate {
    Exam getExam();

    void scheduleExam(Exam exam);
}
