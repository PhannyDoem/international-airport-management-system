package com.internationalairportmanagementsystem.controller;
import com.internationalairportmanagementsystem.dtos.posts.PostFeedbackDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFeedbackDto;
import com.internationalairportmanagementsystem.enetity.Feedback;
import com.internationalairportmanagementsystem.service.implementations.FeedbackServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackRestController {
    private final FeedbackServiceImpl feedbackServiceImpl;

    @Autowired
    public FeedbackRestController(FeedbackServiceImpl feedbackServiceImpl) {
        this.feedbackServiceImpl = feedbackServiceImpl;
    }
    @Operation(
            description = "Endpoint to get all feedbacks",
            summary = "Endpoint for feedbacks retrieval",
            responses = {
                    @ApiResponse(
                            description = "Successful login",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/feedbacks")
    public ResponseEntity<List<Feedback>> findAll() {
        return new ResponseEntity<>(feedbackServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to get a specific feedback by ID",
            summary = "Retrieve a specific feedback by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the feedback",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Feedback ID does not exist",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/feedbacks/{feedbackId}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable Long feedbackId) {
        return new ResponseEntity<>(feedbackServiceImpl.findById(feedbackId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to add a new feedback",
            summary = "Add a new feedback",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the feedback",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/feedbacks")
    public ResponseEntity<Feedback> addFeedback(@RequestBody PostFeedbackDto postFeedbackDto) {
       return new ResponseEntity<>(feedbackServiceImpl.create(postFeedbackDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Endpoint to update an existing feedback",
            summary = "Update an existing feedback",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the feedback",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Feedback ID does not exist",
                            responseCode = "404"
                    )
            }
    )
    @PutMapping("/private/feedbacks/{feedbackId}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long feedbackId,@RequestBody PutFeedbackDto putFeedbackDto) {
        return new ResponseEntity<>(feedbackServiceImpl.update(feedbackId, putFeedbackDto), HttpStatus.OK);


    }
    @Operation(
            description = "Endpoint to delete a feedback by ID",
            summary = "Delete a feedback by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the feedback",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Feedback ID does not exist",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/feedbacks/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId) {
        return new ResponseEntity<>(feedbackServiceImpl.deleteById(feedbackId), HttpStatus.OK);
    }

}
