package com.fizello.job_listing_api.company;

import com.fizello.job_listing_api.job.Job;
import com.fizello.job_listing_api.common.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Object> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>(ApiResponse.success("Company created successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(
                ApiResponse.success("All Companies retrieved successfully", companies), HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Object> getCompanyById(@PathVariable String companyId) {
        Company company = companyService.getCompanyById(companyId);
        return new ResponseEntity<>(
                ApiResponse.success("Company data retrieved successfully", company), HttpStatus.OK);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Object> updateCompany(@PathVariable String companyId, @RequestBody Company updatedCompany) {
        companyService.updateCompany(companyId, updatedCompany);
        return new ResponseEntity<>(
                ApiResponse.success("Company data updated successfully"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Object> deleteCompany(@PathVariable String companyId) {
        companyService.deleteCompany(companyId);
        return new ResponseEntity<>(ApiResponse.success("Company deleted successfully"), HttpStatus.OK);
    }

    @PostMapping("/{companyId}/jobs")
    public ResponseEntity<Object> createJob(@PathVariable String companyId, @RequestBody Job job) {
        companyService.createJob(companyId, job);
        return new ResponseEntity<>(
                ApiResponse.success("Job created successfully"), HttpStatus.OK);
    }

    @GetMapping("/{companyId}/jobs")
    public ResponseEntity<Object> getAllJobs(@PathVariable String companyId) {
        List<Job> jobs = companyService.getAllJobs(companyId);
        return new ResponseEntity<>(
                ApiResponse.success("All Jobs retrieved successfully", jobs), HttpStatus.OK);
    }
}
