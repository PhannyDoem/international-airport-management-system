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
                postFeedbackDto.status()
        );
        feedback.setFeedbackId(0L);

        if(postFeedbackDto.passengerId() != null){
            Passenger passenger = new Passenger();
            passenger.setPassengerId(postFeedbackDto.passengerId());
            feedback.setPassenger(passenger);
        }
        if(postFeedbackDto.flightId() != null){
            Flight flight = new Flight();
            flight.setFlightId(postFeedbackDto.flightId());
            feedback.setFlight(flight);
        }
        return feedback;
    }

    public Feedback putToFeedback(PutFeedbackDto putFeedbackDto){
        Feedback feedback = new Feedback(
                putFeedbackDto.content(),
                putFeedbackDto.status()
        );
        feedback.setFeedbackId(putFeedbackDto.feedbackId());
        if(putFeedbackDto.passengerId() != null){
            Passenger passenger = new Passenger();
            passenger.setPassengerId(putFeedbackDto.passengerId());
            feedback.setPassenger(passenger);
        }
        if(putFeedbackDto.flightId() != null){
            Flight flight = new Flight();
            flight.setFlightId(putFeedbackDto.flightId());
            feedback.setFlight(flight);
        }
        return feedback;
    }
}
