package com.msleads.msleads.resource;

import com.msleads.msleads.model.Lead;
import com.msleads.msleads.service.LeadService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

@Path("/leads")
public class LeadResource {
    private final LeadService leadService;

    // constructor
    public LeadResource(LeadService leadService) {
        this.leadService = leadService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLead(Lead lead) {
        lead.setCreatedAt(LocalDateTime.now());
        leadService.createLead(lead);
        return Response.status(Response.Status.CREATED).entity(lead).build();
    }
}
