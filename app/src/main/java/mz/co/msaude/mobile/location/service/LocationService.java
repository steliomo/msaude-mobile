package mz.co.msaude.mobile.location.service;

import java.util.List;

import mz.co.msaude.mobile.healthfacility.model.HealthFacility;
import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;

public interface LocationService {

    public void findAllProvinces(String token, ResponseListner<List<Province>> listner);

    public void findLocalityByProvinceUuid(String token, String provinceUuid, ResponseListner<List<Locality>> listner);

    public void findHealthFacilityByLocalityUuid(String token, String localityUuid, ResponseListner<List<HealthFacility>> listner);

}
