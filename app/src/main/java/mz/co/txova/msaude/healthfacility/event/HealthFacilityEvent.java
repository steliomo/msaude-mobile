package mz.co.txova.msaude.healthfacility.event;

import mz.co.txova.msaude.consultation.dto.HealthFacilityDTO;

/**
 * Created by Stélio Moiane on 7/22/17.
 */
public class HealthFacilityEvent {

    private HealthFacilityDTO healthFacilityDTO;

    public HealthFacilityEvent(HealthFacilityDTO healthFacilityDTO) {
        this.healthFacilityDTO = healthFacilityDTO;
    }

    public HealthFacilityDTO getHealthFacilityDTO() {
        return healthFacilityDTO;
    }
}
