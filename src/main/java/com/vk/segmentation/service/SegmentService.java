package com.vk.segmentation.service;
import com.vk.segmentation.repository.SegmentRepository;
import com.vk.segmentation.repository.UserRepository;
import com.vk.segmentation.entity.User;
import java.util.List;
import java.util.Optional;

import com.vk.segmentation.entity.Segment;

public class SegmentService {

    private final SegmentRepository segmentRepository;
    private final UserRepository userRepository;

    public SegmentService(SegmentRepository segmentRepository, UserRepository userRepository){
    	this.segmentRepository = segmentRepository;
    	this.userRepository = userRepository;
    }

    public List<Segment> findAll(){
    	return segmentRepository.findAll();
    }

    public Segment findByName(String name){
    	return segmentRepository.findByName(name);
    }

    public Segment postSegment(Segment segment){

    	return segmentRepository.save(segment);
    }

    public Segment updateSegment(Long id, Segment segment){

    Segment target = segmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Segment not found with id: " + id));

        target.setId(segment.getId());
        target.setSegmentName(segment.getSegmentName());

        return segmentRepository.save(target);
    }

    public void deleteSegment(Long id){
    	segmentRepository.deleteById(id);
    }

    public void addUser(Long segmentId, User user){
    	    Segment target = segmentRepository.findById(segmentId)
        .orElseThrow(() -> new RuntimeException("Segment not found with id: " + segmentId));

        target.getUserSet().add(user);

        segmentRepository.save(target);
    }

    //Hash ile denenecek

        public void deleteUser(Long segmentId, User user){
    	    Segment target = segmentRepository.findById(segmentId)
        .orElseThrow(() -> new RuntimeException("Segment not found with id: " + segmentId));

        target.getUserSet().remove(user);

        segmentRepository.save(target);
    }

        public void deleteUserById(Long segmentId, User user){
    	    Segment target = segmentRepository.findById(segmentId)
        .orElseThrow(() -> new RuntimeException("Segment not found with id: " + segmentId));

        boolean removed = target.getUserSet().removeIf(u -> u.getId().equals(user.getId()));

        if(removed){
        	throw new RuntimeException("Segment not found with id: " + segmentId);
        }

        segmentRepository.save(target);
    }



}