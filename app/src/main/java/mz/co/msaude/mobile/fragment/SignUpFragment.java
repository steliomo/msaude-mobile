package mz.co.msaude.mobile.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.delegate.LoginDelegate;
import mz.co.msaude.mobile.formatter.PhoneFormatter;
import mz.co.msaude.mobile.patient.model.Gender;
import mz.co.msaude.mobile.user.model.User;
import mz.co.msaude.mobile.validator.DateOfBirthValidator;
import mz.co.msaude.mobile.validator.EmailValidator;
import mz.co.msaude.mobile.validator.PhoneNumberValidator;
import mz.co.msaude.mobile.validator.StandardValidator;
import mz.co.msaude.mobile.validator.Validator;

public class SignUpFragment extends BaseFragment {

    @BindView(R.id.fragment_login_sign_up_name)
    TextInputLayout nameTxt;

    @BindView(R.id.fragment_login_sign_up_surname)
    TextInputLayout surnameTxt;

    @BindView(R.id.fragment_login_sign_up_male)
    RadioButton male;

    @BindView(R.id.fragment_login_sign_up_female)
    RadioButton female;

    @BindView(R.id.fragment_login_sign_up_date_of_birth)
    TextInputLayout dateOfBirthTxt;

    @BindView(R.id.fragment_login_sign_up_phone_number)
    TextInputLayout phoneNumberTxt;

    @BindView(R.id.fragment_login_sign_up_password)
    TextInputLayout passwordTxt;

    @BindView(R.id.fragment_login_sign_up_email)
    TextInputLayout emailTxt;

    private List<Validator> validators;
    private LoginDelegate delegate;
    private User user;
    private PhoneFormatter formatter;

    @Override
    public int getResourceId() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void onCreateView() {

        delegate = (LoginDelegate) getActivity();
        validators = new ArrayList<>();
        user = new User();

        configureFields();

        formatter = new PhoneFormatter();
    }

    private void configureFields() {
        configureField(nameTxt, new StandardValidator(nameTxt));
        configureField(surnameTxt, new StandardValidator(surnameTxt));

        configureDateOfBirth(dateOfBirthTxt);
        configurePhoneNumber(phoneNumberTxt);

        configureField(passwordTxt, new StandardValidator(passwordTxt));
        configureField(emailTxt, new EmailValidator(emailTxt));
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
                    editText.setText(formatter.remove(phoneNumberValue));
                } else {
                    validator.isValid();
                }
            }
        });
    }

    private void configureField(TextInputLayout textInputLayout, final Validator validator) {
        EditText editText = textInputLayout.getEditText();
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

    @OnClick(R.id.fragment_login_sign_up_regist)
    public void onRegist() {

        for (Validator validator : validators) {
            if (!validator.isValid()) {
                return;
            }
        }

        populateUser();

        delegate.signUp(user);
    }

    private void populateUser() {
        user.setName(getText(nameTxt));
        user.setSurname(getText(surnameTxt));
        user.setDateOfBirth(getText(dateOfBirthTxt));
        user.setPhoneNumber(formatter.remove(getText(phoneNumberTxt)));
        user.setPassword(getText(passwordTxt));
        user.setEmail(getText(emailTxt));
    }

    @NonNull
    private String getText(TextInputLayout textInputLayout) {
        return textInputLayout.getEditText().getText().toString();
    }

    @OnClick(R.id.fragment_login_sign_up_cancel)
    public void onCancel() {
        delegate.back();
    }

    @OnClick(R.id.fragment_login_sign_up_male)
    public void onClickMaleRadio() {
        user.setGender(Gender.MALE);
    }

    @OnClick(R.id.fragment_login_sign_up_female)
    public void onClickFemaleRadio() {
        user.setGender(Gender.FEMALE);
    }

    private void showDatePicker(final EditText editText) {
        Calendar instance = Calendar.getInstance();
        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = StringUtils.leftPad((dayOfMonth) + "", 2, "0") + "-" +
                        StringUtils.leftPad((month + 1) + "", 2, "0") + "-" +
                        year;

                editText.setText(date);
            }

        }, instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH));

        pickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        pickerDialog.show();
    }

    private void configureDateOfBirth(TextInputLayout inputLayout) {

        final EditText editText = inputLayout.getEditText();

        final Validator validator = new DateOfBirthValidator(inputLayout);
        validators.add(validator);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                showDatePicker(editText);
            }
        });
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
