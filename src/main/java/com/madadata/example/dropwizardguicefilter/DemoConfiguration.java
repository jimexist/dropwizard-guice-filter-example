package com.madadata.example.dropwizardguicefilter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by jiayu on 1/18/17.
 */
public class DemoConfiguration extends Configuration {

    @JsonProperty("maxFileSize")
    @Max(100_000L)
    @Min(0L)
    private int maxFileSize;
}
