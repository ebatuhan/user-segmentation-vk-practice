package com.vk.segmentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vk.segmentation.entity.Segment;

public interface SegmentRepository extends JpaRepository<Segment, Long>  {

	Segment findByName(String name);
    
}