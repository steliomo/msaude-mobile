package mz.co.txova.msaude.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import mz.co.txova.msaude.R;
import mz.co.txova.msaude.adapter.ConsultationTypeAdapter;
import mz.co.txova.msaude.consultation.ConsultationType;

public class ConsultationTypeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_consultation_type, container, false);
        ListView consultationTypesView = (ListView) view.findViewById(R.id.fragment_consultation_type);

        List<ConsultationType> consultationTypes = Arrays.asList(new ConsultationType("Acupuntura"), new ConsultationType("Cardiologia"), new ConsultationType("Cirurgia Plastica"));

        ConsultationTypeAdapter adapter = new ConsultationTypeAdapter(getActivity(), consultationTypes);
        consultationTypesView.setAdapter(adapter);

        return view;
    }
}
