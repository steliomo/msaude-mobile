package mz.co.msaude.mobile.module;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mz.co.msaude.mobile.consultation.service.ConsultationService;
import mz.co.msaude.mobile.consultation.service.ConsultationServiceImpl;
import mz.co.msaude.mobile.doctor.service.DoctorService;
import mz.co.msaude.mobile.doctor.service.DoctorServiceImpl;
import mz.co.msaude.mobile.exam.service.ExamService;
import mz.co.msaude.mobile.exam.service.ExamServiceImpl;
import mz.co.msaude.mobile.infra.SharedPreferencesManager;
import mz.co.msaude.mobile.location.service.LocationService;
import mz.co.msaude.mobile.location.service.LocationServiceImpl;
import mz.co.msaude.mobile.medicalservicetype.service.MedicalServiceTypeService;
import mz.co.msaude.mobile.medicalservicetype.service.MedicalServiceTypeServiceImpl;
import mz.co.msaude.mobile.patient.service.PatientService;
import mz.co.msaude.mobile.patient.service.PatientServiceImpl;
import mz.co.msaude.mobile.retrofit.RetrofitConfig;
import mz.co.msaude.mobile.user.service.UserService;
import mz.co.msaude.mobile.user.service.UserServiceImpl;
import mz.co.msaude.mobile.validator.TextViewValidator;
import retrofit2.Retrofit;

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
    @Singleton
    public Retrofit provideRetrofit() {
        return new RetrofitConfig().build();
    }

    @Provides
    @Singleton
    public SharedPreferencesManager provideSharedPreferencesManager() {
        return new SharedPreferencesManager(context);
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
    public PatientService providePatientService(PatientServiceImpl patientService) {
        return patientService;
    }

    @Provides
    public UserService provideUserService(UserServiceImpl userService) {
        return userService;
    }

    @Provides
    public LocationService proviceLocationService(LocationServiceImpl locationService) {
        return locationService;
    }

    @Provides
    public DoctorService proviceDoctorService(DoctorServiceImpl doctorService) {
        return doctorService;
    }

    @Provides
    public MedicalServiceTypeService provide(MedicalServiceTypeServiceImpl medicalServiceTypeService) {
        return medicalServiceTypeService;
    }

    @Provides
    public ExamService proviceExamService(ExamServiceImpl examService) {
        return examService;
    }
}
