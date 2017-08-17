package mz.co.txova.msaude.consultation.service;

import android.support.annotation.NonNull;

import mz.co.txova.msaude.consultation.model.ConsultationFilter;
import mz.co.txova.msaude.consultation.model.Hour;
import mz.co.txova.msaude.consultation.model.QueryResult;
import mz.co.txova.msaude.doctor.dto.DoctorDTO;
import mz.co.txova.msaude.doctor.model.Doctor;
import mz.co.txova.msaude.doctor.model.DoctorAvailability;
import mz.co.txova.msaude.healthfacility.model.HealthFacility;

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

        if (!consultationFilter.getHealthFacility().trim().isEmpty() && !consultationFilter.getDoctorName().trim().isEmpty() && !consultationFilter.getConsultationDate().trim().isEmpty()) {
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

        DoctorDTO doctorDTO = new DoctorDTO(consultationFilter.getCity(), consultationFilter.getHealthFacility(), clinicare, null);
        doctorDTO.setDoctor(nailah);
        doctorDTO.setDoctorAvailability(_20072017);

        return doctorDTO;
    }
}
