package com.fizello.job_listing_api.review;

import com.fizello.job_listing_api.common.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<Object> addReview(@PathVariable String companyId, @RequestBody Review review) {
        reviewService.addReview(companyId, review);
        return new ResponseEntity<>(ApiResponse.success("Review added successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/reviews")
    public ResponseEntity<Object> getCompanyReviews(@PathVariable String companyId) {
        List<Review> reviews = reviewService.getCompanyReviews(companyId);
        return new ResponseEntity<>(
                ApiResponse.success("Reviews retrieved successfully", reviews), HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Object> getReviewById(@PathVariable String companyId, @PathVariable String reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        return new ResponseEntity<>
                (ApiResponse.success("Review data retrieved successfully", review), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Object> updateReview(@PathVariable String companyId, @PathVariable String reviewId,
                                               @RequestBody Review reviewData) {
        reviewService.updateReview(companyId, reviewId, reviewData);
        return new ResponseEntity<>(ApiResponse.success("Review data updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Object> deleteReview(@PathVariable String companyId, @PathVariable String reviewId) {
        reviewService.deleteReview(companyId, reviewId);
        return new ResponseEntity<>(ApiResponse.success("Review deleted successfully"), HttpStatus.OK);
    }
}
