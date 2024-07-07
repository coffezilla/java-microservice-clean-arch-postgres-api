package com.msleads.msleads.resource;

import com.msleads.msleads.config.Secured;
import com.msleads.msleads.model.Lead;
import com.msleads.msleads.service.LeadService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Secured
    public Response createLead(Lead lead) {
        lead.setCreatedAt(LocalDateTime.now());
        leadService.createLead(lead);
        return Response.status(Response.Status.CREATED).entity(lead).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response getAllLeads() {
        List<Lead> leads = leadService.getAllLeads();
        return Response.ok(leads).build();
    }

    @DELETE
    @Path("/{leadId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response deleteLead(@PathParam("leadId") Long leadId) {
        leadService.deleteLead(leadId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Lead deleted successfully");
        return Response.ok(response).build();
    }


    @GET
    @Path("/{leadId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response getLeadById(@PathParam("leadId") Long leadId) {
        Lead lead = leadService.findLeadById(leadId);
        if (lead == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lead).build();
    }

    @PUT
    @Path("/{leadId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response updateLead(@PathParam("leadId") Long leadId, Lead updateLead) {
        // find lead
        Lead lead = leadService.findLeadById(leadId);
        if(lead == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        lead.setName(updateLead.getName());
        leadService.updateLead(lead);
        return Response.ok(lead).build();
    }

}
