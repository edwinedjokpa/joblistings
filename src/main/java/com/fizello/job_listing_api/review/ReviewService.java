package com.fizello.job_listing_api.review;

import com.fizello.job_listing_api.company.Company;
import com.fizello.job_listing_api.company.CompanyRepository;
import com.fizello.job_listing_api.common.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    public ReviewService(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }

    public void addReview(String companyId, Review review) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found"));
        review.setCompany(company);
        reviewRepository.save(review);
    }

    public List<Review> getCompanyReviews(String companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found"));
        return reviewRepository.findByCompanyId(companyId);
    }

    public Review getReviewById(String companyId, String reviewId) {
        List<Review> reviews = this.getCompanyReviews(companyId);
        return reviews.stream().filter(review1 -> review1.getId().equals(reviewId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Review not found"));
    }

    public void updateReview(String companyId, String reviewId, Review reviewData) {
        Review review = getReviewById(companyId, reviewId);
        review.setTitle(reviewData.getTitle());
        review.setDescription(reviewData.getDescription());
        review.setRating(reviewData.getRating());
        reviewRepository.save(review);
    }

    public void deleteReview(String companyId, String reviewId) {
        Review review = getReviewById(companyId, reviewId);
        Company company = review.getCompany();
        company.getReviews().remove(review);
        companyRepository.save(company);
        reviewRepository.delete(review);
    }
}
