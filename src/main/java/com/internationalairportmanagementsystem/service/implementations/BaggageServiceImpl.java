package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostBaggageDto;
import com.internationalairportmanagementsystem.dtos.puts.PutBaggageDto;
import com.internationalairportmanagementsystem.enetity.Baggage;
import com.internationalairportmanagementsystem.mappers.BaggageMapper;
import com.internationalairportmanagementsystem.repository.BaggageRepository;
import com.internationalairportmanagementsystem.service.interfaces.BaggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaggageServiceImpl implements BaggageService {

    private BaggageRepository baggageRepository;
    private BaggageMapper baggageMapper;

    @Autowired
    public BaggageServiceImpl(BaggageRepository baggageRepository, BaggageMapper baggageMapper) {
        this.baggageRepository = baggageRepository;
        this.baggageMapper = baggageMapper;
    }
    @Override
    public Baggage create(PostBaggageDto postBaggageDto) {
        Baggage baggage = baggageMapper.postToBaggage(postBaggageDto);
        return baggageRepository.save(baggage);
    }

    @Override
    public Baggage update(Long baggageId, PutBaggageDto putBaggageDto) {
        Baggage baggage =  baggageMapper.putToBaggage(putBaggageDto);
        return baggageRepository.save(baggage);
    }

    @Override
    public List<Baggage> findAll() {
        return baggageRepository.findAll();
    }

    @Override
    public Baggage findById(Long baggageId) {
        Optional<Baggage> result = baggageRepository.findById(baggageId);
        Baggage baggage = null;
        if(result.isPresent()) {
            baggage = result.get();
        }else {
            throw new RuntimeException("Not Found Baggage with Id " + baggageId);
        }
        return baggage;
    }

    @Override
    public String deleteById(Long baggageId) {
        baggageRepository.deleteById(baggageId);
        return "Deleted Baggage with Id " + baggageId;
    }

    @Override
    public Optional<Baggage> findByPassengerId(Long passengerId) {
        return baggageRepository.findByPassengerId(passengerId);
    }

    @Override
    public String deleteAll() {
        baggageRepository.deleteAll();
        return "Deleted All Baggage";
    }
}
