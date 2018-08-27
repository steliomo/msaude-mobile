package mz.co.msaude.mobile.fragment;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.delegate.LoginDelegate;
import mz.co.msaude.mobile.formatter.PhoneFormatter;
import mz.co.msaude.mobile.validator.PhoneNumberValidator;
import mz.co.msaude.mobile.validator.StandardValidator;
import mz.co.msaude.mobile.validator.Validator;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.fragment_login_username)
    TextInputLayout username;

    @BindView(R.id.fragment_login_password)
    TextInputLayout password;

    private List<Validator> validators;

    private LoginDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onCreateView() {
        delegate = (LoginDelegate) getActivity();
        this.validators = new ArrayList<>();
        configurePhoneNumber(username);
        addStandardValidation(password);
    }

    private void configurePhoneNumber(TextInputLayout phoneNumber) {
        final EditText editText = phoneNumber.getEditText();

        final Validator validator = new PhoneNumberValidator(phoneNumber);
        validators.add(validator);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String phoneNumberValue = editText.getText().toString();
                    editText.setText(PhoneFormatter.remove(phoneNumberValue));
                } else {
                    validator.isValid();
                }
            }
        });
    }

    private void addStandardValidation(TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.getEditText();

        final Validator validator = new StandardValidator(textInputLayout);
        validators.add(validator);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.isValid();
                }
            }
        });
    }

    @OnClick(R.id.fragment_login_button)
    public void onClickLoginButton() {

        for (Validator validator : validators) {
            if (!validator.isValid()) {
                return;
            }
        }
        Snackbar.make(username, "pode logar", Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fragment_login_forget_password)
    public void onClickForgetPasswordButton() {

        Snackbar.make(username, "Senha Esquecida", Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fragment_login_sign_up)
    public void onClickSignUpButton() {
        delegate.addSignUpFragment();
        Snackbar.make(username, "Registar-se.....", Snackbar.LENGTH_SHORT).show();
    }
}
