package mz.co.msaude.mobile.fragment;

import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.delegate.LoginDelegate;
import mz.co.msaude.mobile.validator.EmailValidator;
import mz.co.msaude.mobile.validator.Validator;

public class ResetPasswordFragment extends BaseFragment {

    @BindView(R.id.fragment_reset_password_email)
    TextInputLayout emailTxt;

    private Validator validator;

    private LoginDelegate delegate;

    @Override
    public int getResourceId() {
        return R.layout.fragment_reset_password;
    }

    @Override
    public void onCreateView() {
        delegate = (LoginDelegate) getActivity();
        addEmailValidation(emailTxt);
    }

    private void addEmailValidation(TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.getEditText();
        validator = new EmailValidator(textInputLayout);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.isValid();
                }
            }
        });
    }

    @OnClick(R.id.fragment_reset_password_submit)
    public void onClickSubmit() {

        if (!validator.isValid()) {
            return;
        }

        delegate.resetPassword(emailTxt.getEditText().getText().toString());
        Log.i("EMAIL", emailTxt.getEditText().getText().toString());

    }


    @OnClick(R.id.fragment_reset_password_cancel)
    public void onCancel() {
        delegate.back();
    }
}
