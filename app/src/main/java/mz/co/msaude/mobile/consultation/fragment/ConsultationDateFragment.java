package mz.co.msaude.mobile.consultation.fragment;

import android.support.annotation.NonNull;


import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.apache.commons.lang3.StringUtils;
import org.threeten.bp.LocalDate;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.delegate.ScheduleConsultationDelegate;
import mz.co.msaude.mobile.delegate.ScheduleDelegate;
import mz.co.msaude.mobile.fragment.BaseFragment;


public class ConsultationDateFragment extends BaseFragment implements OnDateSelectedListener {

    @BindView(R.id.fragment_consultation_date_calendar_view)
    MaterialCalendarView consultationDateCalendarView;

    private ScheduleDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_consultation_date;
    }

    @Override
    public void onCreateView() {

        delegate = (ScheduleDelegate) getActivity();
        delegate.setFragmentTitle(getArguments().getString(ScheduleDelegate.TITLE));

        consultationDateCalendarView
                .state()
                .edit()
                .setMinimumDate(LocalDate.now()).commit();

        consultationDateCalendarView.setOnDateChangedListener(this);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {

        StringBuilder builder = new StringBuilder();
        builder.append(StringUtils.leftPad(calendarDay.getDay() + "", 2, "0"))
                .append("-")
                .append(StringUtils.leftPad(calendarDay.getMonth() + "", 2, "0"))
                .append("-")
                .append(calendarDay.getYear());

        delegate.onSelectDate(builder.toString());
    }

}
