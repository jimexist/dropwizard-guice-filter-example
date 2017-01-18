package com.madadata.example.dropwizardguicefilter.auth;

import com.madadata.example.dropwizardguicefilter.pojo.DemoAppUser;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiayu on 1/18/17.
 */
public class DemoAuthenticator implements Authenticator<BasicCredentials, DemoAppUser> {

    private static final Logger logger = LoggerFactory.getLogger(DemoAuthenticator.class);

    @Override
    public Optional<DemoAppUser> authenticate(BasicCredentials credentials) throws AuthenticationException {
        logger.info("credentials {}", credentials);
        return Optional.of(new DemoAppUser(
            credentials.getUsername(),
            credentials.getUsername(),
            "random-email@mail.com"
        ));
    }
}
