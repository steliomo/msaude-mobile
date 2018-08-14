package mz.co.msaude.mobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepstone.stepper.Step;

import butterknife.ButterKnife;
import mz.co.msaude.mobile.activities.BaseActivity;
import mz.co.msaude.mobile.infra.SaudeApplication;

/**
 * Created by Stélio Moiane on 6/15/17.
 */
public abstract class BaseFragment extends Fragment implements Step {

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
