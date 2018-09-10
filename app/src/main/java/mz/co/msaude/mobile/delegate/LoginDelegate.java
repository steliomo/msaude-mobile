package mz.co.msaude.mobile.delegate;

import android.app.Application;

import mz.co.msaude.mobile.patient.model.Patient;
import mz.co.msaude.mobile.user.model.User;

public interface LoginDelegate {

    void addSignUpFragment();

    void addResetPasswordFragment();

    void back();

    Application getApplication();

    void signUp(User user);

    void login(String username, String password);

    void resetPassword(String email);
}
