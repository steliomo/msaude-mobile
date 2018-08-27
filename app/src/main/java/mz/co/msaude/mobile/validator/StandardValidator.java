package mz.co.msaude.mobile.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class StandardValidator implements Validator {


    private TextInputLayout textInputLayout;

    public StandardValidator(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    @Override
    public boolean isValid() {

        EditText editText = textInputLayout.getEditText();

        if (editText.getText().toString().isEmpty()) {
            textInputLayout.setError("*Campo Obrigatório");
            return false;
        }

        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
        return true;
    }
}
