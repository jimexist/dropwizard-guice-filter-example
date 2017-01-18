package com.madadata.example.dropwizardguicefilter;

import com.google.inject.Guice;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.madadata.example.dropwizardguicefilter.resource.DemoResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by jiayu on 1/18/17.
 */
public class DemoApplication extends Application<DemoConfiguration> {

    private GuiceBundle<DemoConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "DemoApplication";
    }

    @Override
    public void initialize(Bootstrap<DemoConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false))
        );
        guiceBundle = GuiceBundle.<DemoConfiguration>newBuilder()
            .setConfigClass(DemoConfiguration.class)
            .addModule(new DemoGuiceModule())
            // https://github.com/HubSpot/dropwizard-guice/issues/19
            // because we are using singletons that depend on environment and configuration
            // we must force it to be lazily initialized, and then force initialization on
            // the #run method below
            .setInjectorFactory((state, modules) -> Guice.createInjector(Stage.DEVELOPMENT, modules))
            .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(DemoConfiguration demoConfiguration, Environment environment) throws Exception {
        environment.jersey().register(guiceBundle.getInjector().getInstance(DemoResource.class));
    }
}

