package com.vk.segmentation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vk.segmentation.dto.VkSegmentDto;
import com.vk.segmentation.dto.VkSegmentPostDto;
import com.vk.segmentation.service.VkSegmentService;

@RestController
@RequestMapping("/segment")
public class VkSegmentController {

	private final VkSegmentService vkSegmentService;

	public VkSegmentController(VkSegmentService vkSegmentService) {
		this.vkSegmentService = vkSegmentService;
	}

	@GetMapping("/all")
	public List<VkSegmentDto> findAll() {
		return vkSegmentService.findAll();
	}

	@GetMapping("/{id}")
	public VkSegmentDto findById(@PathVariable Long id) {
		return vkSegmentService.findById(id);
	}

	@GetMapping("/name/{name}")
	public VkSegmentDto findByName(@PathVariable String name) {
		return vkSegmentService.findBySegmentName(name);
	}

	@PostMapping
	public VkSegmentDto createSegment(@RequestBody VkSegmentPostDto vkSegmentPostDto) {
		return vkSegmentService.createVkSegment(vkSegmentPostDto);
	}

	@PutMapping("/{id}")
	public VkSegmentDto updateSegment(@PathVariable Long id, @RequestBody VkSegmentPostDto vkSegment) {
		return vkSegmentService.updateVkSegment(id, vkSegment);
	}

	@DeleteMapping("/{id}")
	public boolean deleteSegmentById(@PathVariable Long id) {
		return vkSegmentService.deleteSegmentById(id);
	}

	@DeleteMapping("/name/{name}")
	public boolean deleteSegmentByName(@PathVariable String name) {
		return vkSegmentService.deleteSegmentByName(name);
	}
}
