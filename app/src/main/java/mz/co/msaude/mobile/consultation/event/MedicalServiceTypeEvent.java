package mz.co.msaude.mobile.consultation.event;

import mz.co.msaude.mobile.medicalservicetype.model.MedicalServiceType;

/**
 * Created by steliomo on 9/17/17.
 */

public class MedicalServiceTypeEvent {

    private MedicalServiceType medicalServiceType;

    public MedicalServiceTypeEvent(MedicalServiceType medicalServiceType) {
        this.medicalServiceType = medicalServiceType;
    }

    public MedicalServiceType getMedicalServiceType() {
        return this.medicalServiceType;
    }
}
