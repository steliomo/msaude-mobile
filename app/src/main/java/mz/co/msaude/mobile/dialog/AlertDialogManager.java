package mz.co.msaude.mobile.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mz.co.msaude.mobile.R;
import mz.co.msaude.mobile.listner.AlertListner;

public class AlertDialogManager {

    private Context context;

    public AlertDialogManager(Context context) {
        this.context = context;
    }

    public void showAlert(String message, int layoutId, final AlertListner listner) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(layoutId)
                .setCancelable(Boolean.FALSE).create();

        dialog.show();

        TextView textView = dialog.findViewById(R.id.success_alert_dialog_message);
        textView.setText(message);

        Button okButton = dialog.findViewById(R.id.success_alert_dialog_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (listner != null) {
                    listner.perform();
                }
            }
        });

        Button noButton = dialog.findViewById(R.id.success_alert_dialog_no);
        if (noButton == null) {
            return;
        }

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
