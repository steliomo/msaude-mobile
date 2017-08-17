package mz.co.txova.msaude.module;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mz.co.txova.msaude.consultation.service.AllConsultationQueryFilter;
import mz.co.txova.msaude.consultation.service.ConsultationDateQueryFilter;
import mz.co.txova.msaude.consultation.service.ConsultationQueryFilter;
import mz.co.txova.msaude.consultation.service.ConsultationService;
import mz.co.txova.msaude.consultation.service.ConsultationServiceImpl;
import mz.co.txova.msaude.consultation.service.DefaultConsultationQueryFilter;
import mz.co.txova.msaude.consultation.service.DoctorConsultationQueryFilter;
import mz.co.txova.msaude.consultation.service.HealthFacilityAndDoctorConsultationQueryFilter;
import mz.co.txova.msaude.consultation.service.HealthFacilityConsultationQueryFilter;
import mz.co.txova.msaude.validator.TextViewValidator;

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
