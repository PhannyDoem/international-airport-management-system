package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostFeedbackDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFeedbackDto;
import com.internationalairportmanagementsystem.enetity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback create(PostFeedbackDto  postFeedbackDto);
    Feedback update(Long feedbackId, PutFeedbackDto putFeedbackDto);
    List<Feedback> findAll();
    Feedback findById(Long feedbackId);
    String deleteById(Long feedbackId);
}
