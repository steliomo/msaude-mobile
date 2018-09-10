package mz.co.msaude.mobile.listner;

public interface ResponseListner<T> {

    void success(T response);

    void error(String message);

}
