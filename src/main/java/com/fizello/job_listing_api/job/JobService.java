package com.fizello.job_listing_api.job;

import com.fizello.job_listing_api.company.Company;
import com.fizello.job_listing_api.company.CompanyRepository;
import com.fizello.job_listing_api.common.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public void createJob(Job job) {
        jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(String jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new NotFoundException("Job not found"));
    }

    public void updateJobById(String jobId, Job updatedJob) {
        Job job = getJobById(jobId);
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setLocation(updatedJob.getLocation());
        job.setSalary(updatedJob.getSalary());
        jobRepository.save(job);
    }

    public void deleteJobById(String jobId) {
        Job job = getJobById(jobId);
        jobRepository.delete(job);
    }

}
