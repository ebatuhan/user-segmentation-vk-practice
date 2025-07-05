package com.vk.segmentation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vk.segmentation.service.SegmentService;
import com.vk.segmentation.entity.Segment;

@RestController
@RequestMapping("/segment")
public class SegmentController {

	private final SegmentService segmentService;

	public SegmentController(SegmentService segmentService){
		this.segmentService = segmentService;
	}

	@GetMapping("/segments")
	public List<Segment> findAll(){
		return segmentService.findAll();
	}

    
}