package mz.co.msaude.mobile.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.consultation.model.Availability;
import mz.co.msaude.mobile.doctor.model.DoctorAvailability;

/**
 * Created by Stélio Moiane on 7/19/17.
 */
public class TimeTableAdapter extends BaseAbstractAdapter {

    @BindView(R.id.icon)
    ImageView icon;

    @BindView(R.id.name)
    TextView timeTable;

    private Context context;

    private List<Availability> availabilities;

    public TimeTableAdapter(final Context context, List<Availability> availabilities) {
        this.context = context;
        this.availabilities = availabilities;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public int getResourceId() {
        return R.layout.list_dates;
    }

    @Override
    public void onCreateView(int position) {

        String iconName = "ic_timer";
        Availability item = availabilities.get(position);

        if (item instanceof DoctorAvailability)
            iconName = "ic_calendar_today";

        icon.setImageResource(context.getResources().getIdentifier(iconName, "mipmap", context.getPackageName()));
        timeTable.setText(item.getAvailability());
    }

    @Override
    public int getCount() {
        return availabilities.size();
    }

    @Override
    public Availability getItem(int position) {
        return availabilities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return availabilities.get(position).hashCode();
    }
}
