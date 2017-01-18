package com.madadata.example.dropwizardguicefilter.resource;

import com.google.inject.Inject;
import com.madadata.example.dropwizardguicefilter.dao.DemoDao;
import com.madadata.example.dropwizardguicefilter.pojo.DemoAppUser;
import com.madadata.example.dropwizardguicefilter.pojo.DemoPojo;
import io.dropwizard.auth.Auth;
import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiayu on 1/18/17.
 */
@Path("/demo")
public class DemoResource {

    private static final Logger logger = LoggerFactory.getLogger(DemoResource.class);

    private final DemoDao demoDao;

    @Inject
    public DemoResource(DemoDao demoDao) {
        this.demoDao = demoDao;
    }

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public DemoPojo getPing(@QueryParam("id") String id, @Auth DemoAppUser appUser) {
        logger.info("user {}", appUser);
        return demoDao.findById(id);
    }
}
