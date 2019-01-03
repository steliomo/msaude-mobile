package mz.co.msaude.mobile.listner;

/**
 * Created by steliomo on 10/16/17.
 */

public interface ClickListner<T> {

    void onClick(final T item);

    void onLongClick(T item);
}
