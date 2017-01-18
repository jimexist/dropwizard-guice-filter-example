package com.madadata.example.dropwizardguicefilter.auth;

import com.madadata.example.dropwizardguicefilter.pojo.DemoAppUser;
import io.dropwizard.auth.Authorizer;

/**
 * Created by jiayu on 1/18/17.
 */
public class DemoAuthorizor implements Authorizer<DemoAppUser> {
    @Override
    public boolean authorize(DemoAppUser principal, String role) {
        return true;
    }
}
