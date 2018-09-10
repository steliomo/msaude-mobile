package mz.co.msaude.mobile.infra;

import android.util.Base64;

public class TokenFactory {

    private final String username;
    private final String password;

    public TokenFactory(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getToken() {

        StringBuilder builder = new StringBuilder();

        builder.append(username)
                .append(":")
                .append(password);

        String token = "Basic " + Base64.encodeToString(builder.toString().getBytes(), Base64.NO_WRAP);

        return token;
    }
}
