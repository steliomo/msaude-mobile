package mz.co.msaude.mobile.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.stepstone.stepper.VerificationError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.activities.ScheduleConsultationActivity;
import mz.co.msaude.mobile.adapter.TimeTableAdapter;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.consultation.dto.HealthFacilityDTO;
import mz.co.msaude.mobile.consultation.model.Availability;
import mz.co.msaude.mobile.consultation.model.Consultation;
import mz.co.msaude.mobile.consultation.model.Hour;
import mz.co.msaude.mobile.consultation.model.QueryResult;
import mz.co.msaude.mobile.doctor.dto.DoctorDTO;
import mz.co.msaude.mobile.doctor.event.DoctorAvailabilityDateEvent;
import mz.co.msaude.mobile.doctor.event.DoctorAvailabilityTimeEvent;
import mz.co.msaude.mobile.doctor.event.DoctorEvent;
import mz.co.msaude.mobile.doctor.model.DoctorAvailability;
import mz.co.msaude.mobile.healthfacility.event.HealthFacilityEvent;

public class TimeTableFragment extends BaseFragment {

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

        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }

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

    @Nullable

    public VerificationError verifyStep() {
        boolean hasDate = getArguments() != null ? getArguments().getBoolean("hasDate") : false;

        if (hasDate) {
            return null;
        }

        if (doctorAvailability == null) {
            return new VerificationError(getString(R.string.date_must_be_selected));
        }

        if (hour == null) {
            return new VerificationError(getString(R.string.hour_must_be_selected));
        }

        return null;
    }


    public void onSelected() {

    }


    public void onError(@NonNull VerificationError error) {
        Snackbar.make(getView(), error.getErrorMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
