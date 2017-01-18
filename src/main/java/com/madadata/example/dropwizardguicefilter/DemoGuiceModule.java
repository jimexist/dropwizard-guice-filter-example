package com.madadata.example.dropwizardguicefilter;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.madadata.example.dropwizardguicefilter.auth.DemoAuthenticator;
import com.madadata.example.dropwizardguicefilter.auth.DemoAuthorizor;
import com.madadata.example.dropwizardguicefilter.dao.DefaultDemoDao;
import com.madadata.example.dropwizardguicefilter.dao.DemoDao;
import com.madadata.example.dropwizardguicefilter.pojo.DemoAppUser;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Created by jiayu on 1/18/17.
 */
public class DemoGuiceModule extends AbstractModule {

    @Provides
    @Singleton
    AuthDynamicFeature provideAuthDynamoFeature(Authenticator<BasicCredentials, DemoAppUser> authenticator,
                                                Authorizer<DemoAppUser> authorizer) {
        AuthFilter<BasicCredentials, DemoAppUser> authFilter = new BasicCredentialAuthFilter.Builder<DemoAppUser>()
            .setAuthenticator(authenticator)
            .setAuthorizer(authorizer)
            .setRealm("realm")
            .buildAuthFilter();
        return new AuthDynamicFeature(authFilter);
    }

    @Provides
    @Singleton
    MetricRegistry provideMetricRegistry(Environment environment) {
        return environment.metrics();
    }

    @Override
    protected void configure() {
        bind(RolesAllowedDynamicFeature.class)
            .in(Scopes.SINGLETON);
        bind(AuthValueFactoryProvider.Binder.class)
            .toInstance(new AuthValueFactoryProvider.Binder<>(DemoAppUser.class));
        bind(new TypeLiteral<Authorizer<DemoAppUser>>() {
        }).to(DemoAuthorizor.class)
            .in(Scopes.SINGLETON);
        bind(new TypeLiteral<Authenticator<BasicCredentials, DemoAppUser>>() {
        }).to(DemoAuthenticator.class)
            .in(Scopes.SINGLETON);
        bind(DemoDao.class).to(DefaultDemoDao.class)
            .in(Scopes.SINGLETON);
    }
}
