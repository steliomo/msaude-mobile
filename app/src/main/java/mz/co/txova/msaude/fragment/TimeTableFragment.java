package mz.co.txova.msaude.fragment;

import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import mz.co.txova.msaude.R;
import mz.co.txova.msaude.activities.ScheduleConsultationActivity;
import mz.co.txova.msaude.adapter.TimeTableAdapter;
import mz.co.txova.msaude.component.SaudeComponent;
import mz.co.txova.msaude.consultation.dto.HealthFacilityDTO;
import mz.co.txova.msaude.consultation.model.Availability;
import mz.co.txova.msaude.consultation.model.Consultation;
import mz.co.txova.msaude.consultation.model.Hour;
import mz.co.txova.msaude.consultation.model.QueryResult;
import mz.co.txova.msaude.doctor.dto.DoctorDTO;
import mz.co.txova.msaude.doctor.event.DoctorAvailabilityDateEvent;
import mz.co.txova.msaude.doctor.event.DoctorAvailabilityTimeEvent;
import mz.co.txova.msaude.doctor.event.DoctorEvent;
import mz.co.txova.msaude.doctor.model.DoctorAvailability;
import mz.co.txova.msaude.healthfacility.event.HealthFacilityEvent;

public class TimeTableFragment extends BaseFragment implements FragmentValidator {

    @BindView(R.id.time_table_dates)
    ListView datesList;

    @BindView(R.id.time_table_hours)
    ListView hoursList;

    @BindView(R.id.time_table_date_layout)
    LinearLayout dateLayout;

    @BindView(R.id.time_table_hours_layout)
    LinearLayout hourLayout;

    @Inject
    EventBus eventBus;
    private DoctorAvailability doctorAvailability;

    private Hour hour;

    @Override
    public int getResourceId() {
        return R.layout.fragment_time_table;
    }

    @Override
    public void onCreateView() {

        SaudeComponent component = application.getComponent();
        component.inject(this);
        eventBus.register(this);

        dateLayout.setVisibility(View.GONE);
        hourLayout.setVisibility(View.GONE);

        ScheduleConsultationActivity activity = (ScheduleConsultationActivity) getActivity();
        QueryResult result = (QueryResult) activity.getIntent().getSerializableExtra(QueryResult.QUERY_RESULT);

        if (result instanceof HealthFacilityDTO) {
            return;
        }

        DoctorDTO doctorDTO = (DoctorDTO) result;

        Consultation consultation = activity.getConsultation();
        consultation.setHealthFacility(doctorDTO.getHealthFacility());
        consultation.setDoctor(doctorDTO.getDoctor());
        consultation.setScheduledDate(doctorDTO.getDoctorAvailability() != null ? doctorDTO.getDoctorAvailability().getAvailability() : null);

        if (doctorDTO.getDoctorAvailability() != null) {
            hourLayout.setVisibility(View.VISIBLE);
            TimeTableAdapter hourAdapter = new TimeTableAdapter(activity, new ArrayList<Availability>(doctorDTO.getDoctorAvailability().getHours()));
            hoursList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            hoursList.setAdapter(hourAdapter);
            return;
        }

        if (doctorDTO.getDoctor() != null) {
            populateDates(new ArrayList<Availability>(doctorDTO.getDoctor().getDoctorAvailabilities()));
        }
    }

    private void populateDates(List<Availability> availabilities) {
        dateLayout.setVisibility(View.VISIBLE);

        TimeTableAdapter datesAdapter = new TimeTableAdapter(getActivity(),
                availabilities);

        datesList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        datesList.setAdapter(datesAdapter);
    }

    @Subscribe
    public void onEvent(DoctorEvent event) {

        boolean hasDate = getArguments() != null ? getArguments().getBoolean("hasDate") : false;

        if (hasDate) {
            hourLayout.setVisibility(View.VISIBLE);
            TimeTableAdapter hourAdapter = new TimeTableAdapter(getActivity(), new ArrayList<Availability>(event.getDoctorDTO().getDoctorAvailability().getHours()));
            hoursList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            hoursList.setAdapter(hourAdapter);
            return;
        }

        if (!hasDate) {

            populateDates(new ArrayList<Availability>(event.getDoctorDTO().getDoctor().getDoctorAvailabilities()));

            doctorAvailability = null;
            hour = null;
        }
    }

    @Subscribe
    public void onEvent(HealthFacilityEvent event) {

        boolean hasDate = getArguments() != null ? getArguments().getBoolean("hasDate") : false;

        if (hasDate) {
            return;
        }

        if (!hasDate && event.getHealthFacilityDTO().getDoctor() != null) {

            populateDates(new ArrayList<Availability>(event.getHealthFacilityDTO().getDoctor().getDoctorAvailabilities()));

            doctorAvailability = null;
            hour = null;
        }
    }

    @OnItemClick(R.id.time_table_dates)
    public void onClickDate(int position) {
        hour = null;
        hourLayout.setVisibility(View.VISIBLE);

        doctorAvailability = (DoctorAvailability) datesList.getItemAtPosition(position);
        TimeTableAdapter hourAdapter = new TimeTableAdapter(getActivity(), new ArrayList<Availability>(doctorAvailability.getHours()));

        eventBus.post(new DoctorAvailabilityDateEvent(doctorAvailability));

        hoursList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        hoursList.setAdapter(hourAdapter);
    }

    @OnItemClick(R.id.time_table_hours)
    public void onClickHour(int position) {
        hour = (Hour) hoursList.getItemAtPosition(position);
        eventBus.post(new DoctorAvailabilityTimeEvent(hour));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    @Override
    public void validate(ViewPager viewPager, int position) {

        boolean hasDate = getArguments() != null ? getArguments().getBoolean("hasDate") : false;

        if (hasDate) {
            validateHour(viewPager, position);
            return;
        }

        if (doctorAvailability == null) {
            viewPager.setCurrentItem(position);
            Snackbar.make(getView(), getString(R.string.date_must_be_selected), Snackbar.LENGTH_SHORT).show();
            return;
        }

        validateHour(viewPager, position);
    }

    private void validateHour(ViewPager viewPager, int position) {
        if (hour == null) {
            viewPager.setCurrentItem(position);
            Snackbar.make(getView(), getString(R.string.hour_must_be_selected), Snackbar.LENGTH_SHORT).show();
        }
    }
}
