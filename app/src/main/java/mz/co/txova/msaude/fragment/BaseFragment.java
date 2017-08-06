package mz.co.txova.msaude.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import mz.co.txova.msaude.activities.BaseActivity;
import mz.co.txova.msaude.infra.SaudeApplication;

/**
 * Created by Stélio Moiane on 6/15/17.
 */
public abstract class BaseFragment extends Fragment {

    protected SaudeApplication application;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getResourceId(), container, false);

        ButterKnife.bind(this, view);
        BaseActivity activity = (BaseActivity) getActivity();
        application = (SaudeApplication) activity.getApplication();

        onCreateView();

        return view;
    }

    public abstract int getResourceId();

    public abstract void onCreateView();
}
