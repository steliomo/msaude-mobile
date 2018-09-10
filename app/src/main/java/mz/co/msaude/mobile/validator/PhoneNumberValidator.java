package mz.co.msaude.mobile.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import mz.co.msaude.mobile.formatter.PhoneFormatter;

public class PhoneNumberValidator implements Validator {

    private final PhoneFormatter formatter;
    private TextInputLayout textInputLayout;
    private StandardValidator validator;

    public PhoneNumberValidator(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        validator = new StandardValidator(textInputLayout);
        formatter = new PhoneFormatter();
    }

    @Override
    public boolean isValid() {

        EditText editText = textInputLayout.getEditText();
        String phoneNumber = editText.getText().toString();

        if (!validator.isValid()) {
            return false;
        }

        if (formatter.remove(phoneNumber).length() != 9) {
            textInputLayout.setError("O Telefone deve ter 9 dígitos");
            return false;
        }

        editText.setText(formatter.format(phoneNumber));
        return true;
    }
}
