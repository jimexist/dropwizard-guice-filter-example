package com.madadata.example.dropwizardguicefilter;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.madadata.example.dropwizardguicefilter.dao.DefaultDemoDao;
import com.madadata.example.dropwizardguicefilter.dao.DemoDao;
import io.dropwizard.setup.Environment;

/**
 * Created by jiayu on 1/18/17.
 */
public class DemoGuiceModule extends AbstractModule {

    @Provides
    @Singleton
    MetricRegistry provideMetricRegistry(Environment environment) {
        return environment.metrics();
    }

    @Override
    protected void configure() {
        bind(DemoDao.class).to(DefaultDemoDao.class).in(Scopes.SINGLETON);
    }
}
