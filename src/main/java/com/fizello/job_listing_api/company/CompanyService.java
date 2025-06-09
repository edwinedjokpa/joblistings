package com.fizello.job_listing_api.company;

import com.fizello.job_listing_api.job.Job;
import com.fizello.job_listing_api.job.JobRepository;
import com.fizello.job_listing_api.common.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;

    public CompanyService(CompanyRepository companyRepository, JobRepository jobRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
    }

    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(String companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }

    public void updateCompany(String companyId, Company updatedCompany) {
        Company company = getCompanyById(companyId);
        company.setName(updatedCompany.getName());
        company.setDescription(updatedCompany.getDescription());
        companyRepository.save(company);
    }

    public void deleteCompany(String companyId) {
        Company company = getCompanyById(companyId);
        companyRepository.delete(company);
    }

    public void createJob(String companyId, Job job) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found"));
        job.setCompany(company);
        jobRepository.save(job);
    }

    public List<Job> getAllJobs(String companyId) {
        return jobRepository.findByCompanyId(companyId);
    }
}
