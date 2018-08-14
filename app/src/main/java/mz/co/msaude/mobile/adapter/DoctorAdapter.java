package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.doctor.model.Doctor;

/**
 * Created by Stélio Moiane on 6/16/17.
 */
public class DoctorAdapter extends BaseAbstractAdapter implements FilterableAdapter<Doctor> {

    @BindView(R.id.doctor_icon)
    ImageView doctorIcon;

    @BindView(R.id.doctor_full_name)
    TextView doctorFullName;

    @BindView(R.id.doctor_category)
    TextView doctorCategory;

    private Context context;
    private List<Doctor> doctors;

    public DoctorAdapter(final Context context, final List<Doctor> doctors) {
        this.context = context;
        this.doctors = doctors;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public int getResourceId() {
        return R.layout.list_doctors;
    }

    @Override
    public void onCreateView(int position) {
        Doctor doctor = doctors.get(position);

        doctorFullName.setText(doctor.getFullName());
        doctorCategory.setText(doctor.getCategory());
    }

    @Override
    public int getCount() {
        return doctors.size();
    }

    @Override
    public Doctor getItem(int position) {
        return doctors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return doctors.get(position).hashCode();
    }

    @Override
    public void setFilter(List<Doctor> items) {
        this.doctors = items;
        notifyDataSetChanged();
    }
}
