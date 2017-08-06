package mz.co.txova.msaude.consultation;

import org.junit.Test;

import java.util.List;

import mz.co.txova.msaude.consultation.model.ConsultationFilter;
import mz.co.txova.msaude.consultation.model.QueryResult;
import mz.co.txova.msaude.consultation.service.ConsultationServiceImpl;

import static org.junit.Assert.assertFalse;


/**
 * Created by Stélio Moiane on 8/6/17.
 */
public class ConsultationServiceTest {

    @Test
    public void shouldFindQueryResultsByFilter() {

        ConsultationServiceImpl consultationService = new ConsultationServiceImpl();
        ConsultationFilter consultationFilter = new ConsultationFilter("Matola", "Dermatologia", "Hospital Privado", "Dra. Nailah Moiane", "28-08-2017");
        List<QueryResult> results = consultationService.findConsultationsByFilter(consultationFilter);

        assertFalse(results.isEmpty());
    }
}
