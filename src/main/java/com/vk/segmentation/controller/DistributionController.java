package com.vk.segmentation.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vk.segmentation.exception.BadParamsException;
import com.vk.segmentation.service.DistributionService;

@RestController
@RequestMapping("/Distribution")
public class DistributionController {

	private final DistributionService distributionService;

	public DistributionController(DistributionService distributionService) {
		this.distributionService = distributionService;
	}

	@PutMapping
	public boolean distribute(
			@RequestParam double percentage,
			@RequestParam String segmentName) {

		if (percentage <= 0 || percentage > 100) {
			throw new BadParamsException("Percentage must be greater than 0 and less than or equal to 100.");
		}

		return distributionService.DistributieSegmentOnUsers(percentage, segmentName);
	}

}