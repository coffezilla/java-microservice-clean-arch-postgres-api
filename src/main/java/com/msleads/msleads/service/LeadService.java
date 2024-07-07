package com.msleads.msleads.service;

import com.msleads.msleads.model.Lead;
import com.msleads.msleads.repository.LeadRepository;

public class LeadService {
    private final LeadRepository leadRepository;

    // constructor
    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public Lead createLead(Lead lead) {
        return leadRepository.createLead(lead);
    }
}
