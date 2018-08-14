package mz.co.msaude.mobile.module;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mz.co.msaude.mobile.consultation.service.AllConsultationQueryFilter;
import mz.co.msaude.mobile.consultation.service.ConsultationDateQueryFilter;
import mz.co.msaude.mobile.consultation.service.ConsultationQueryFilter;
import mz.co.msaude.mobile.consultation.service.ConsultationService;
import mz.co.msaude.mobile.consultation.service.ConsultationServiceImpl;
import mz.co.msaude.mobile.consultation.service.DefaultConsultationQueryFilter;
import mz.co.msaude.mobile.consultation.service.DoctorConsultationQueryFilter;
import mz.co.msaude.mobile.consultation.service.HealthFacilityAndDoctorConsultationQueryFilter;
import mz.co.msaude.mobile.consultation.service.HealthFacilityConsultationQueryFilter;
import mz.co.msaude.mobile.validator.TextViewValidator;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
@Module
public class SaudeModule {

    private Context context;

    public SaudeModule(final Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.builder().build();
    }

    @Provides
    public TextViewValidator provideTextViewValidator() {
        return new TextViewValidator(context);
    }

    @Provides
    public ConsultationService provideConsultationService(ConsultationServiceImpl consultationService) {
        return consultationService;
    }

    @Provides
    @Named("defaultConsultationFilter")
    public ConsultationQueryFilter provideDefaultConsultationQueryFilter() {
        return new DefaultConsultationQueryFilter();
    }

    @Provides
    @Named("healthFacilityConsultationQueryFilter")
    public ConsultationQueryFilter provideHealthFacilityConsultationQueryFilter() {
        return new HealthFacilityConsultationQueryFilter();
    }

    @Provides
    @Named("doctorConsultationQueryFilter")
    public ConsultationQueryFilter provideDoctorConsultationQueryFilter() {
        return new DoctorConsultationQueryFilter();
    }

    @Provides
    @Named("healthFacilityAndDoctorConsultationQueryFilter")
    public ConsultationQueryFilter provideHealthFacilityAndDoctorConsultationQueryFilter() {
        return new HealthFacilityAndDoctorConsultationQueryFilter();
    }

    @Provides
    @Named("allConsultationQueryFilter")
    public ConsultationQueryFilter provideAllConsultationQueryFilter() {
        return new AllConsultationQueryFilter();
    }

    @Provides
    @Named("consultationDateQueryFilter")
    public ConsultationQueryFilter provideConsultationDateQueryFilter() {
        return new ConsultationDateQueryFilter();
    }
}
