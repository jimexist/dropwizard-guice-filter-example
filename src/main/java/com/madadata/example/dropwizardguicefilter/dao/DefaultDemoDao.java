package com.madadata.example.dropwizardguicefilter.dao;

import com.madadata.example.dropwizardguicefilter.pojo.DemoPojo;

/**
 * Created by jiayu on 1/18/17.
 */
public class DefaultDemoDao implements DemoDao {

    @Override
    public DemoPojo findById(String id) {
        return new DemoPojo(id, "random name");
    }
}
