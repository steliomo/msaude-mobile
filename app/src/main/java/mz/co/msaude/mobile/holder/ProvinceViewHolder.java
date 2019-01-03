package mz.co.msaude.mobile.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.listner.ClickListner;
import mz.co.msaude.mobile.location.model.Province;

public class ProvinceViewHolder extends BaseViewHolder {

    @BindView(R.id.icon)
    ImageView imageView;

    @BindView(R.id.name)
    TextView name;

    private ClickListner listner;
    private Province province;

    public ProvinceViewHolder(View view) {
        super(view);
        imageView.setImageResource(R.mipmap.ic_province);
    }

    @Override
    public void onClick(View view) {
        listner.onClick(province);
    }

    @Override
    public boolean onLongClick(View view) {
        return true;
    }

    public void setItemClicListner(ClickListner listner) {
        this.listner = listner;
    }

    public void bind(Province province) {
        this.province = province;
        name.setText(province.getName());
    }
}
