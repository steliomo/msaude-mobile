package mz.co.msaude.mobile.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.location.model.Locality;
import mz.co.msaude.mobile.location.model.Province;

public class LocalityViewHolder extends BaseViewHolder {

    @BindView(R.id.icon)
    ImageView imageView;

    @BindView(R.id.name)
    TextView name;

    private ClickListner listner;
    private Locality locality;

    public LocalityViewHolder(View view) {
        super(view);
        imageView.setImageResource(R.mipmap.ic_location);
    }

    @Override
    public void onClick(View view) {
        listner.onClick(locality);
    }

    @Override
    public boolean onLongClick(View view) {
        listner.onLongClick(locality);
        return true;
    }

    public void setItemClicListner(ClickListner listner) {
        this.listner = listner;
    }

    public void bind(Locality locality) {
        this.locality = locality;
        name.setText(locality.getName());
    }
}
