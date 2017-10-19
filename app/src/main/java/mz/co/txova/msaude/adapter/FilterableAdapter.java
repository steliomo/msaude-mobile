package mz.co.txova.msaude.adapter;

import java.util.List;

/**
 * Created by steliomo on 9/17/17.
 */

public interface FilterableAdapter<T> {

    public void setFilter(List<T> items);

}
