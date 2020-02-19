package com.sonnebtb.wowguildmanager.battlenet_oauth2.connections;

import android.os.Parcel;
import android.os.Parcelable;


import com.google.api.client.auth.oauth2.Credential;
import com.sonnebtb.wowguildmanager.battlenet_oauth2.BnConstants;

public class BnOAuth2Params implements Parcelable {

    private final String TOKEN_URL_CN = "https://www.battlenet.com.cn/oauth/token";
    private final String AUTH_URL_CN = "https://www.battlenet.com.cn/oauth/authorize";

    private final String TOKEN_URL_NOT_CN = "https://zone.battle.net/oauth/token";
    private final String AUTH_URL_NOT_CN = "https://zone.battle.net/oauth/authorize";

    private final String ZONE_DEFAULT = "zone";

    private String clientId;
    private String clientSecret;
    private String scope;
    private String rederictUri;
    private String userId;
    private String zone;
    private String tokenServerUrl;
    private String authorizationServerEncodedUrl;

    public BnOAuth2Params(String clientId, String clientSecret, String zone, String rederictUri, String userId, String... scopes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.zone = zone;

        // If we login in China servers, we have different URL
        if (BnConstants.ZONE_CHINA.equalsIgnoreCase(zone)) {
            this.tokenServerUrl = TOKEN_URL_CN;
            this.authorizationServerEncodedUrl = AUTH_URL_CN;
        } else {
            this.tokenServerUrl = TOKEN_URL_NOT_CN.replace(ZONE_DEFAULT, zone);
            this.authorizationServerEncodedUrl = AUTH_URL_NOT_CN.replace(ZONE_DEFAULT, zone);
        }

        this.rederictUri = rederictUri;
        this.userId = userId;

        // Create SCOPES with required pattern (Scope+Scope+...)
        StringBuilder sb = new StringBuilder();
        if (null != scopes && 0< scopes.length) {
            for (String scope: scopes) {
                sb.append(scope).append("+");
            }
        } else {
            // Access to WoW and SC2 data
            sb.append(BnConstants.SCOPE_WOW).append("+");
            sb.append(BnConstants.SCOPE_SC2).append("+");
        }

        this.scope = sb.toString().substring(0, sb.toString().length() - 1);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRederictUri() {
        return rederictUri;
    }

    public void setRederictUri(String rederictUri) {
        this.rederictUri = rederictUri;
    }

    public String getTokenServerUrl() {
        return tokenServerUrl;
    }

    public void setTokenServerUrl(String tokenServerUrl) {
        this.tokenServerUrl = tokenServerUrl;
    }

    public String getAuthorizationServerEncodedUrl() {
        return authorizationServerEncodedUrl;
    }

    public void setAuthorizationServerEncodedUrl(String authorizationServerEncodedUrl) {
        this.authorizationServerEncodedUrl = authorizationServerEncodedUrl;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Credential.AccessMethod getAccessMethod() {
        return BnQueryParamsAccessMethod.getInstance();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.clientId);
        dest.writeString(this.clientSecret);
        dest.writeString(this.scope);
        dest.writeString(this.rederictUri);
        dest.writeString(this.userId);
        dest.writeString(this.zone);
        dest.writeString(this.tokenServerUrl);
        dest.writeString(this.authorizationServerEncodedUrl);
    }

    protected BnOAuth2Params(Parcel in) {
        this.clientId = in.readString();
        this.clientSecret = in.readString();
        this.scope = in.readString();
        this.rederictUri = in.readString();
        this.userId = in.readString();
        this.zone = in.readString();
        this.tokenServerUrl = in.readString();
        this.authorizationServerEncodedUrl = in.readString();
    }

    public static final Creator<BnOAuth2Params> CREATOR = new Creator<BnOAuth2Params>() {
        @Override
        public BnOAuth2Params createFromParcel(Parcel source) {
            return new BnOAuth2Params(source);
        }

        @Override
        public BnOAuth2Params[] newArray(int size) {
            return new BnOAuth2Params[size];
        }
    };
}
