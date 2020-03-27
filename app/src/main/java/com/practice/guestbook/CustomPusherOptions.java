package com.practice.guestbook;

import com.pusher.client.PusherOptions;

public class CustomPusherOptions extends PusherOptions {

    String SOCKET_URL = "guest-book.naveksoft.com";
    String prefix = "";
    String URI_SUFFIX = "?client=java-client&protocol=5&version=$LIB_VERSION";

    boolean hasEncrypted;

    public CustomPusherOptions(boolean hasEncrypted) {
        this.hasEncrypted = hasEncrypted;
    }

    @Override
    public String buildUrl(String apiKey) {

        if (this.prefix != null) {
            this.prefix += "/";
        } else {
            this.prefix += "";
        }

        String buildUrlCustom = String.format("%s://%s:%s/%sapp/%s%s",
        "wss", //if (encrypted) "wss" else "ws" // always encrypted
                SOCKET_URL,
                "443",
                prefix,
                apiKey,
                URI_SUFFIX);

        if (hasEncrypted) {
            return buildUrlCustom;
        }

        return apiKey;
    }
}
