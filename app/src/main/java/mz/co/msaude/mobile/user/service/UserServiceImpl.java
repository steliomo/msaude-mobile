package mz.co.msaude.mobile.user.service;

import javax.inject.Inject;

import mz.co.msaude.mobile.infra.TokenFactory;
import mz.co.msaude.mobile.listner.ResponseListner;
import mz.co.msaude.mobile.user.model.User;
import mz.co.msaude.mobile.user.resource.UserResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserServiceImpl implements UserService {

    @Inject
    Retrofit retrofit;

    @Inject
    public UserServiceImpl() {
    }

    @Override
    public void signUp(User user, final ResponseListner<User> listner) {

        UserResource resource = getUserResource();
        resource.signUp(user)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        listner.success(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        listner.error(t.getMessage());
                    }
                });
    }

    private UserResource getUserResource() {
        return retrofit.create(UserResource.class);
    }

    @Override
    public void login(String username, String password, final ResponseListner<User> listner) {

        UserResource resource = getUserResource();

        resource.login(new TokenFactory(username, password).getToken())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        listner.success(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        listner.error(t.getMessage());
                    }
                });
    }

    @Override
    public void resetPassword(String email, final ResponseListner<User> listner) {

        UserResource resource = getUserResource();

        resource.resetPassword(email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                listner.success(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listner.error(t.getMessage());
            }
        });
    }
}
