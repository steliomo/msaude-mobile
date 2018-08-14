package mz.co.msaude.mobile.doctor.event;

import mz.co.msaude.mobile.doctor.dto.DoctorDTO;

/**
 * Created by Stélio Moiane on 7/22/17.
 */
public class DoctorEvent {

    private DoctorDTO doctorDTO;

    public DoctorEvent(DoctorDTO doctorDTO) {
        this.doctorDTO = doctorDTO;
    }

    public DoctorDTO getDoctorDTO() {
        return doctorDTO;
    }
}
