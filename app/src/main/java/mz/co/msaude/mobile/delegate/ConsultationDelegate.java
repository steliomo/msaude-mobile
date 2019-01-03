package mz.co.msaude.mobile.delegate;

import android.view.View;

import mz.co.msaude.mobile.consultation.model.Consultation;

public interface ConsultationDelegate {

    void onVerticalMenuClickListner(View view, Consultation consultation);
}
