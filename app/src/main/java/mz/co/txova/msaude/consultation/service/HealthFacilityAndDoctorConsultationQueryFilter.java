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
public class HealthFacilityAndDoctorConsultationQueryFilter implements ConsultationQueryFilter {

    private ConsultationQueryFilter consultationQueryFilter;

    @Override
    public void setNextConsultationQueryFilter(ConsultationQueryFilter consultationQueryFilter) {
        this.consultationQueryFilter = consultationQueryFilter;
    }

    @Override
    public QueryResult find(ConsultationFilter consultationFilter) {

        if (!consultationFilter.getHealthFacility().trim().isEmpty() && !consultationFilter.getDoctorName().trim().isEmpty()) {
            return fakeData(consultationFilter);
        }

        return consultationQueryFilter.find(consultationFilter);
    }

    @NonNull
    private DoctorDTO fakeData(ConsultationFilter consultationFilter) {

        DoctorAvailability _20072017 = new DoctorAvailability("20-07-2017");
        DoctorAvailability _21072017 = new DoctorAvailability("21-07-2017");
        DoctorAvailability _22072017 = new DoctorAvailability("22-07-2017");
        DoctorAvailability _23072017 = new DoctorAvailability("23-07-2017");
        DoctorAvailability _24072017 = new DoctorAvailability("24-07-2017");
        DoctorAvailability _25072017 = new DoctorAvailability("25-07-2017");

        _20072017.addHours(Hour.values());
        _21072017.addHours(Hour.NINE_TO_NINE_HALF, Hour.NINE_HALF_TO_TEN, Hour.TEN_TO_TEN_HALF, Hour.TEN_HALF_TO_ELEVEN, Hour.ELEVEN_HALF_TO_TWELVE, Hour.TWO_TO_TWO_HALF);
        _22072017.addHours(Hour.NINE_TO_NINE_HALF, Hour.NINE_HALF_TO_TEN, Hour.TEN_TO_TEN_HALF, Hour.TEN_HALF_TO_ELEVEN, Hour.ELEVEN_HALF_TO_TWELVE, Hour.TWO_TO_TWO_HALF, Hour.NINE_TO_NINE_HALF.THREE_HALF_TO_FOUR);
        _23072017.addHours(Hour.NINE_TO_NINE_HALF, Hour.NINE_HALF_TO_TEN, Hour.TEN_TO_TEN_HALF, Hour.TEN_HALF_TO_ELEVEN, Hour.ELEVEN_HALF_TO_TWELVE);
        _24072017.addHours(Hour.NINE_TO_NINE_HALF, Hour.NINE_HALF_TO_TEN, Hour.TEN_TO_TEN_HALF, Hour.TEN_HALF_TO_ELEVEN);
        _25072017.addHours(Hour.NINE_TO_NINE_HALF, Hour.NINE_HALF_TO_TEN, Hour.TEN_TO_TEN_HALF);

        Doctor nailah = new Doctor("Nailah", "Moiane", "Especialista em pediatria");
        nailah.addDoctorAvailabilities(_21072017, _22072017, _23072017);

        HealthFacility clinicare = new HealthFacility("Clinicare");
        clinicare.addDoctors(nailah);

        DoctorDTO doctorDTO = new DoctorDTO(consultationFilter.getCity(), consultationFilter.getHealthFacility(), clinicare, null);
        doctorDTO.setDoctor(nailah);

        return doctorDTO;
    }
}