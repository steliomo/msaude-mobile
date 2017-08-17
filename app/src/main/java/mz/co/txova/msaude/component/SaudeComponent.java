package mz.co.txova.msaude.component;

import javax.inject.Singleton;

import dagger.Component;
import mz.co.txova.msaude.activities.ConsultationsActivity;
import mz.co.txova.msaude.activities.ScheduleConsultationActivity;
import mz.co.txova.msaude.activities.SearchConsultationActivity;
import mz.co.txova.msaude.firebase.SaudeFirebaseMessagingService;
import mz.co.txova.msaude.fragment.DoctorFragment;
import mz.co.txova.msaude.fragment.HealthFacilityFragment;
import mz.co.txova.msaude.fragment.ScheduleConfirmationFragment;
import mz.co.txova.msaude.fragment.TimeTableFragment;
import mz.co.txova.msaude.module.SaudeModule;

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
}
