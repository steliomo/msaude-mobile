package mz.co.msaude.mobile.user.service;

import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.user.model.User;

public interface UserService {

    void signUp(User user, ResponseListner<User> listner);

    void login(String username, String password, ResponseListner<User> listner);

    void resetPassword(String emial, ResponseListner<User> listner);
}
