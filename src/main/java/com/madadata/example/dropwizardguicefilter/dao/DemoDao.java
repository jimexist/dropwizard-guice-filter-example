package com.madadata.example.dropwizardguicefilter.dao;

import com.madadata.example.dropwizardguicefilter.pojo.DemoPojo;

/**
 * Created by jiayu on 1/18/17.
 */
public interface DemoDao {

    DemoPojo findById(String id);
}
