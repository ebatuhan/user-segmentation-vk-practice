package com.vk.segmentation.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.vk.segmentation.entity.VkSegment;
import com.vk.segmentation.entity.VkUser;
import com.vk.segmentation.exception.ResourceNotFoundException;
import com.vk.segmentation.repository.VkSegmentRepository;
import com.vk.segmentation.repository.VkUserRepository;
import com.vk.segmentation.service.DistributionService;


import jakarta.transaction.Transactional;

@Service
public class DistributionServiceImpl implements DistributionService {

	private final VkSegmentRepository vkSegmentRepository;
	private final VkUserRepository vkUserRepository;

	public DistributionServiceImpl(VkSegmentRepository vkSegmentRepository, VkUserRepository vkUserRepository) {
		this.vkSegmentRepository = vkSegmentRepository;
		this.vkUserRepository = vkUserRepository;
	}

	@Override
	@Transactional
	public boolean DistributieSegmentOnUsers(Double percentage, String segmentName) {
		VkSegment segment = vkSegmentRepository.findBySegmentName(segmentName)
				.orElseThrow(() -> new ResourceNotFoundException("Segment with name " + segmentName + " not exists."));

		List<VkUser> users = new ArrayList<>(new HashSet<>(vkUserRepository.findAll()));
		Collections.shuffle(users);

		int limit = (int) Math.round(users.size() * percentage / 100.0);

		users.stream()
			.limit(limit)
			.forEach(user -> {
				user.getVkSegments().add(segment);
			});

		vkSegmentRepository.save(segment);
		return true;
	}

}