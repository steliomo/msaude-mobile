package mz.co.msaude.mobile.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class EmailValidator implements Validator {

    private TextInputLayout textInputLayout;
    private Validator validator;

    public EmailValidator(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        validator = new StandardValidator(textInputLayout);
    }

    @Override
    public boolean isValid() {

        EditText editText = textInputLayout.getEditText();
        String value = editText.getText().toString();

        if (!validator.isValid()) {
            return false;
        }

        if (value.matches(".+@.+\\..+")) {
            return true;
        }

        textInputLayout.setError("Email inválido");
        return false;
    }
}
