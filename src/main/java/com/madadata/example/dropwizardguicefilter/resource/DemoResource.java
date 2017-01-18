package com.madadata.example.dropwizardguicefilter.resource;

import com.google.inject.Inject;
import com.madadata.example.dropwizardguicefilter.dao.DemoDao;
import com.madadata.example.dropwizardguicefilter.pojo.DemoPojo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by jiayu on 1/18/17.
 */
@Path("/demo")
public class DemoResource {

    private final DemoDao demoDao;

    @Inject
    public DemoResource(DemoDao demoDao) {
        this.demoDao = demoDao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DemoPojo getPing(@QueryParam("id") String id) {
        return demoDao.findById(id);
    }
}
