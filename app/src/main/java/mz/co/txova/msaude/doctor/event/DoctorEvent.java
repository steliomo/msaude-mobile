package mz.co.txova.msaude.doctor.event;

import mz.co.txova.msaude.doctor.dto.DoctorDTO;

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
