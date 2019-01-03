package mz.co.msaude.mobile.listner;

import android.view.View;

public interface VerticalMenuClickListner<T> {
    void perform(View view, T item);
}
