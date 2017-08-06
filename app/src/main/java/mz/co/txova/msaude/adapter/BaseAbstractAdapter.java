package mz.co.txova.msaude.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import butterknife.ButterKnife;

/**
 * Created by Stélio Moiane on 6/15/17.
 */
public abstract class BaseAbstractAdapter extends BaseAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(getResourceId(), parent, false);
        }

        ButterKnife.bind(this, view);

        onCreateView(position);

        return view;
    }

    public abstract Context getContext();

    public abstract int getResourceId();

    public abstract void onCreateView(int position);
}
