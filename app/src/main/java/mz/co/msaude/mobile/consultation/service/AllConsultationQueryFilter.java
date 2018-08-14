package mz.co.msaude.mobile.consultation.service;

import android.support.annotation.NonNull;

import mz.co.msaude.mobile.consultation.model.ConsultationFilter;
import mz.co.msaude.mobile.consultation.model.Hour;
import mz.co.msaude.mobile.consultation.model.QueryResult;
import mz.co.msaude.mobile.doctor.dto.DoctorDTO;
import mz.co.msaude.mobile.doctor.model.Doctor;
import mz.co.msaude.mobile.doctor.model.DoctorAvailability;
import mz.co.msaude.mobile.healthfacility.model.HealthFacility;

/**
 * Created by Stélio Moiane on 8/6/17.
 */
public class AllConsultationQueryFilter implements ConsultationQueryFilter {

    private ConsultationQueryFilter consultationQueryFilter;

    @Override
    public void setNextConsultationQueryFilter(ConsultationQueryFilter consultationQueryFilter) {
        this.consultationQueryFilter = consultationQueryFilter;
    }

    @Override
    public QueryResult find(ConsultationFilter consultationFilter) {

        if (consultationFilter.getHealthFacility() != null && consultationFilter.getDoctor() != null && !consultationFilter.getConsultationDate().trim().isEmpty()) {
            return fakeData(consultationFilter);
        }

        return consultationQueryFilter.find(consultationFilter);
    }

    @NonNull
    private DoctorDTO fakeData(ConsultationFilter consultationFilter) {

        DoctorAvailability _20072017 = new DoctorAvailability("20-07-2017");
        _20072017.addHours(Hour.values());

        Doctor nailah = new Doctor("Nailah", "Moiane", "Especialista em pediatria");
        nailah.addDoctorAvailabilities(_20072017);

        HealthFacility clinicare = new HealthFacility("Clinicare");
        clinicare.addDoctors(nailah);

        DoctorDTO doctorDTO = new DoctorDTO(consultationFilter.getCity(), "Cardiologia", clinicare, null);
        doctorDTO.setDoctor(nailah);
        doctorDTO.setDoctorAvailability(_20072017);

        return doctorDTO;
    }
}
