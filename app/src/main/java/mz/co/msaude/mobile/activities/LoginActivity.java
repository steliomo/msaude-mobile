package mz.co.msaude.mobile.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.delegate.LoginDelegate;
import mz.co.msaude.mobile.fragment.LoginFragment;
import mz.co.msaude.mobile.fragment.SignUpFragment;

public class LoginActivity extends BaseActivity implements LoginDelegate {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);

        showFragment(new LoginFragment());
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_frame_layout, fragment);
        transaction.commit();
    }

    @Override
    public void addSignUpFragment() {
        showFragment(new SignUpFragment());
    }
}
