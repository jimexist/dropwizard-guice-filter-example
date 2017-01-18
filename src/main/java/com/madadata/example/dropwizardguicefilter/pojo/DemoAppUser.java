package com.madadata.example.dropwizardguicefilter.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import java.security.Principal;

/**
 * Created by jiayu on 1/18/17.
 */
public class DemoAppUser implements Principal {

    private final String userId;
    private final String username;
    private final String email;

    @JsonCreator
    public DemoAppUser(@JsonProperty("userId") String userId,
                       @JsonProperty("username") String username,
                       @JsonProperty("email") String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    @JsonProperty
    public String getUserId() {
        return userId;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getName() {
        return getUserId();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("userId", userId)
            .add("username", username)
            .add("email", email)
            .toString();
    }
}
