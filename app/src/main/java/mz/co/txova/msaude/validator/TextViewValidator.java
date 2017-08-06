package mz.co.txova.msaude.validator;

import android.content.Context;
import android.widget.TextView;

import mz.co.txova.msaude.R;

/**
 * Created by Stélio Moiane on 7/20/17.
 */
public class TextViewValidator {

    private Context context;

    public TextViewValidator(final Context context) {
        this.context = context;
    }

    private TextView[] views;

    public void addViews(TextView... views) {
        this.views = views;
    }

    public boolean isValid() {

        for (TextView view : views) {
            if (view.getText().toString().isEmpty()) {
                view.setError(context.getString(R.string.required_field));
                view.requestFocus();
                return false;
            }
        }

        return true;
    }
}
