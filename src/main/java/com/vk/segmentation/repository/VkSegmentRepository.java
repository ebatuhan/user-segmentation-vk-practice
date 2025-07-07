package com.vk.segmentation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.segmentation.entity.VkSegment;

public interface VkSegmentRepository extends JpaRepository<VkSegment, Long>{
	Optional<VkSegment> findBySegmentName(String segmentName);
	boolean existsBySegmentName(String segmentName);
	long deleteBySegmentName(String segmentName);

    
}