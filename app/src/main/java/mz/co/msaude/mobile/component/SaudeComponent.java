package mz.co.msaude.mobile.component;

import javax.inject.Singleton;

import dagger.Component;
import mz.co.msaude.mobile.activities.ConsultationsActivity;
import mz.co.msaude.mobile.activities.HealthFacilitySelectActivity;
import mz.co.msaude.mobile.activities.ScheduleConsultationActivity;
import mz.co.msaude.mobile.activities.SearchConsultationActivity;
import mz.co.msaude.mobile.activities.SelectCityActivity;
import mz.co.msaude.mobile.activities.SelectConsultationTypeActivity;
import mz.co.msaude.mobile.activities.SelectDoctorActivity;
import mz.co.msaude.mobile.firebase.SaudeFirebaseMessagingService;
import mz.co.msaude.mobile.fragment.DoctorFragment;
import mz.co.msaude.mobile.fragment.HealthFacilityFragment;
import mz.co.msaude.mobile.fragment.ScheduleConfirmationFragment;
import mz.co.msaude.mobile.fragment.TimeTableFragment;
import mz.co.msaude.mobile.module.SaudeModule;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
@Singleton
@Component(modules = SaudeModule.class)
public interface SaudeComponent {

    void inject(SearchConsultationActivity activity);

    void inject(ScheduleConsultationActivity activity);

    void inject(DoctorFragment fragment);

    void inject(HealthFacilityFragment fragment);

    void inject(ScheduleConfirmationFragment fragment);

    void inject(TimeTableFragment fragment);

    void inject(SaudeFirebaseMessagingService service);

    void inject(ConsultationsActivity activity);

    void inject(SelectCityActivity activity);

    void inject(SelectConsultationTypeActivity activity);

    void inject(HealthFacilitySelectActivity activity);

    void inject(SelectDoctorActivity activity);
}
