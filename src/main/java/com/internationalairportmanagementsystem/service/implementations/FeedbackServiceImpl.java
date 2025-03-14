package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostFeedbackDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFeedbackDto;
import com.internationalairportmanagementsystem.enetity.Feedback;
import com.internationalairportmanagementsystem.mappers.FeedbackMapper;
import com.internationalairportmanagementsystem.repository.FeedbackRepository;
import com.internationalairportmanagementsystem.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepository feedbackRepository;
    private FeedbackMapper feedbackMapper;
    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public Feedback create(PostFeedbackDto postFeedbackDto) {
        Feedback feedback = feedbackMapper.postToFeedback(postFeedbackDto);
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback update(PutFeedbackDto putFeedbackDto) {
        Feedback feedback = feedbackMapper.putToFeedback(putFeedbackDto);
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback findById(Long feedbackId) {
        Optional<Feedback> result = feedbackRepository.findById(feedbackId);
        Feedback feedback = result.get();
        feedback = result.get();
        return feedback;
    }

    @Override
    public String deleteById(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
        return "Deleted";
    }
}
