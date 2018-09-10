package mz.co.msaude.mobile.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public static final String BASE_URL = "http://192.168.43.23:8080/services/";

    private OkHttpClient configureClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
    }

    public Retrofit build() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(configureClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
