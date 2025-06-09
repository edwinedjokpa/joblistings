package com.fizello.job_listing_api.job;

import com.fizello.job_listing_api.common.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Object> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>(ApiResponse.success("Job created successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(
                ApiResponse.success("All Jobs retrieved successfully", jobs), HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Object> getJobById(@PathVariable String jobId) {
        Job job = jobService.getJobById(jobId);
        return new ResponseEntity<>(
                ApiResponse.success("Job data retrieved successfully", job), HttpStatus.OK);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Object> updateJobById(@PathVariable String jobId, @RequestBody Job updatedJob) {
        jobService.updateJobById(jobId, updatedJob);
        return new ResponseEntity<>(ApiResponse.success("Job data updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Object> deleteJobById(@PathVariable String jobId) {
        jobService.deleteJobById(jobId);
        return new ResponseEntity<>(ApiResponse.success("Job deleted successfully"), HttpStatus.OK);
    }

}
