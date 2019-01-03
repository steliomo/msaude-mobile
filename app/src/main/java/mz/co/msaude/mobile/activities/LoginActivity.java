package mz.co.msaude.mobile.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import javax.inject.Inject;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.component.SaudeComponent;
import mz.co.msaude.mobile.delegate.LoginDelegate;
import mz.co.msaude.mobile.dialog.AlertDialogManager;
import mz.co.msaude.mobile.dialog.ProgressDialogManager;
import mz.co.msaude.mobile.fragment.LoginFragment;
import mz.co.msaude.mobile.fragment.ResetPasswordFragment;
import mz.co.msaude.mobile.fragment.SignUpFragment;
import mz.co.msaude.mobile.infra.SharedPreferencesManager;
import mz.co.msaude.mobile.infra.TokenFactory;
import mz.co.msaude.mobile.listner.AlertListner;
import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.user.model.User;
import mz.co.msaude.mobile.user.service.UserService;

public class LoginActivity extends BaseActivity implements LoginDelegate {

    private FragmentManager fragmentManager;

    @Inject
    UserService userService;

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    private ProgressDialogManager dialogManager;

    private AlertDialogManager alertDialogManager;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);

        SaudeComponent component = application.getComponent();
        component.inject(this);

        showFragment(new LoginFragment(), Boolean.FALSE);
        dialogManager = new ProgressDialogManager(this);
        alertDialogManager = new AlertDialogManager(this);
    }

    private void showFragment(Fragment fragment, boolean onstack) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_frame_layout, fragment);

        if (onstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    @Override
    public void addSignUpFragment() {
        showFragment(new SignUpFragment(), Boolean.TRUE);
    }

    @Override
    public void addResetPasswordFragment() {
        showFragment(new ResetPasswordFragment(), Boolean.TRUE);
    }

    @Override
    public void back() {
        fragmentManager.popBackStack();
    }

    @Override
    public void signUp(final User user) {

        final ProgressDialog progressBar = dialogManager.getProgressBar(getString(R.string.wait), getString(R.string.processing_request));
        progressBar.show();

        userService.signUp(user, new ResponseListner<User>() {
            @Override
            public void success(final User responseUser) {

                if (responseUser == null) {
                    progressBar.dismiss();
                    alertDialogManager.showAlert(getString(R.string.sign_up_failure), R.layout.error_alert_dialog, null);
                    return;
                }

                alertDialogManager.showAlert(getString(R.string.sign_up_success), R.layout.success_alert_dialog, new AlertListner() {
                    @Override
                    public void perform() {
                        sharedPreferencesManager.setUserInfo(responseUser);
                        mainActivity(progressBar, user.getPhoneNumber(), user.getPassword());
                    }
                });
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
                Log.e("SIGNUP", message);
            }
        });
    }

    private void mainActivity(ProgressDialog progressBar, String phoneNumber, String password) {
        progressBar.dismiss();
        sharedPreferencesManager.login(new TokenFactory(phoneNumber, password).getToken());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void login(final String username, final String password) {
        final ProgressDialog progressBar = dialogManager.getProgressBar(getString(R.string.wait), getString(R.string.processing_request));
        progressBar.show();

        userService.login(username, password, new ResponseListner<User>() {
            @Override
            public void success(User user) {

                if (user == null) {
                    progressBar.dismiss();
                    alertDialogManager.showAlert(getString(R.string.username_password_invalid), R.layout.error_alert_dialog, null);
                    return;
                }

                sharedPreferencesManager.setUserInfo(user);
                mainActivity(progressBar, username, password);
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
                Log.e("LOGIN", message);
            }
        });
    }

    @Override
    public void resetPassword(String email) {

        final ProgressDialog progressBar = dialogManager.getProgressBar(getString(R.string.wait), getString(R.string.processing_request));
        progressBar.show();

        userService.resetPassword(email, new ResponseListner<User>() {
            @Override
            public void success(User user) {

                if (user == null) {
                    progressBar.dismiss();
                    alertDialogManager.showAlert(getString(R.string.reset_password_failure), R.layout.error_alert_dialog, null);
                    return;
                }

                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.reset_password_success), R.layout.success_alert_dialog, new AlertListner() {
                    @Override
                    public void perform() {
                        back();
                    }
                });
            }

            @Override
            public void error(String message) {
                progressBar.dismiss();
                alertDialogManager.showAlert(getString(R.string.communication_failure), R.layout.error_alert_dialog, null);
                Log.e("RESET_PASSWORD", message);
            }
        });
    }
}
