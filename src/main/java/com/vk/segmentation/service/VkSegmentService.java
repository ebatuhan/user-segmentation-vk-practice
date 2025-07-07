package com.vk.segmentation.service;

import java.util.List;

import com.vk.segmentation.dto.VkSegmentDto;
import com.vk.segmentation.dto.VkSegmentPostDto;

public interface VkSegmentService {
	VkSegmentDto findById(Long id);
	List<VkSegmentDto> findAll();
	VkSegmentDto createVkSegment(VkSegmentPostDto vkSegmentPostDto);
	VkSegmentDto updateVkSegment(Long id, VkSegmentPostDto vkSegmentPostDto);
	VkSegmentDto findBySegmentName(String segmentName);
	boolean deleteSegmentById(Long id);
	boolean deleteSegmentByName(String name);

}