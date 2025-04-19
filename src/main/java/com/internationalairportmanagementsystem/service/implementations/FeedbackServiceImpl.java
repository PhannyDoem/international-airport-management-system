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
import java.util.Objects;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
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
    public Feedback update(Long feedbackId, PutFeedbackDto putFeedbackDto) {
        if (feedbackId != null){
            feedbackRepository.findById(feedbackId)
                    .stream()
                    .findFirst()
                    .map(
                            updated -> {
                                updated.setStatus(putFeedbackDto.status());
                                updated.setContent(putFeedbackDto.content());
                                updated.setFlight(putFeedbackDto.flight());
                                updated.setPassenger(putFeedbackDto.passenger());
                                Feedback feedback = feedbackRepository.save(updated);
                                return feedbackRepository.save(feedback);
                            }
                    );
        }
        return feedbackRepository.findById(Objects.requireNonNull(feedbackId))
                .orElseThrow(()-> new IllegalArgumentException("Feedback id not found"));
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback findById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new IllegalArgumentException("Feedback id not found"));
    }

    @Override
    public String deleteById(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
        return "Deleted";
    }
}
