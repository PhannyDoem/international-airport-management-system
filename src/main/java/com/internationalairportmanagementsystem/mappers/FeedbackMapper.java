package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostFeedbackDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFeedbackDto;
import com.internationalairportmanagementsystem.enetity.Feedback;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;
import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {
    public Feedback postToFeedback(PostFeedbackDto  postFeedbackDto){
        Feedback feedback = new Feedback(
                postFeedbackDto.content(),
                postFeedbackDto.status(),
                postFeedbackDto.passenger(),
                postFeedbackDto.flight()
        );
        feedback.setFeedbackId(0L);
        return feedback;
    }

    public Feedback putToFeedback(PutFeedbackDto putFeedbackDto){
        Feedback feedback = new Feedback(
                putFeedbackDto.content(),
                putFeedbackDto.status(),
                putFeedbackDto.passenger(),
                putFeedbackDto.flight()
        );
        feedback.setFeedbackId(0L);
        return feedback;
    }
}
