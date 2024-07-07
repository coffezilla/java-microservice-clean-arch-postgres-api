package com.msleads.msleads.service;

import com.msleads.msleads.model.Lead;
import com.msleads.msleads.repository.LeadRepository;

import java.util.List;

public class LeadService {
    private final LeadRepository leadRepository;

    // constructor
    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public Lead createLead(Lead lead) {
        return leadRepository.createLead(lead);
    }

    public List<Lead> getAllLeads() {
        return leadRepository.getAllLeads();
    }

    public void deleteLead(Long leadId) {
        leadRepository.deleteLead(leadId);
    }

    public Lead findLeadById(Long leadId) {
        return leadRepository.findLeadById(leadId);
    }

    public void updateLead(Lead lead) {
        leadRepository.updateLead(lead);
    }
}
