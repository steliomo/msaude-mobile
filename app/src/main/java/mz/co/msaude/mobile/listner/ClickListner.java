package mz.co.msaude.mobile.listner;

import android.view.View;

/**
 * Created by steliomo on 10/16/17.
 */

public interface ClickListner {

    void onClick(final View view, int position);

    void onLongClick(final View view, int position);
}
