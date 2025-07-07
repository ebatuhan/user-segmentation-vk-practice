package com.vk.segmentation.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.vk.segmentation.dto.VkSegmentDto;
import com.vk.segmentation.dto.VkSegmentPostDto;
import com.vk.segmentation.entity.VkSegment;
import com.vk.segmentation.exception.ResourceNotFoundException;
import com.vk.segmentation.repository.VkSegmentRepository;
import com.vk.segmentation.service.VkSegmentService;

import jakarta.transaction.Transactional;

@Service
public class VkSegmentServiceImpl implements VkSegmentService {

	private final VkSegmentRepository vkSegmentRepository;
	private final ModelMapper modelMapper;

	public VkSegmentServiceImpl(VkSegmentRepository vkSegmentRepository, ModelMapper modelMapper) {
		this.vkSegmentRepository = vkSegmentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public VkSegmentDto findById(Long id) {
		VkSegment segment = vkSegmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Segment with id " + id + " not found."));

		return modelMapper.map(segment, VkSegmentDto.class);
	}

	@Override
	public List<VkSegmentDto> findAll() {
		List<VkSegment> vkSegments = Optional.of(vkSegmentRepository.findAll())
				.filter(list -> !list.isEmpty())
				.orElseThrow(() -> new ResourceNotFoundException("No segment found"));

		return vkSegments.stream()
				.map(segment -> modelMapper.map(segment, VkSegmentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public VkSegmentDto createVkSegment(VkSegmentPostDto vkSegmentPostDto) {
		VkSegment segment = modelMapper.map(vkSegmentPostDto, VkSegment.class);

		VkSegment savedSegment = vkSegmentRepository.save(segment);

		return modelMapper.map(savedSegment, VkSegmentDto.class);
	}

	@Override
	public VkSegmentDto updateVkSegment(Long id, VkSegmentPostDto vkSegmentPostDto) {
		VkSegment segment = vkSegmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Segment with " + id + " not found."));

		modelMapper.map(vkSegmentPostDto, segment);

		VkSegment updatedUser = vkSegmentRepository.save(segment);

		return modelMapper.map(updatedUser, VkSegmentDto.class);

	}

	@Override
	public VkSegmentDto findBySegmentName(String segmentName) {
		VkSegment segment = vkSegmentRepository.findBySegmentName(segmentName)
				.orElseThrow(() -> new ResourceNotFoundException("Segment with name " + segmentName + " not found"));

		return modelMapper.map(segment, VkSegmentDto.class);
	}

	@Override
	public boolean deleteSegmentById(Long id) {
		if (!vkSegmentRepository.existsById(id)) {
			throw new ResourceNotFoundException("Segment with id " + id + " not found.");
		}

		vkSegmentRepository.deleteById(id);

		return true;
	}

	@Override
	@Transactional
	public boolean deleteSegmentByName(String name) {
		if (!vkSegmentRepository.existsBySegmentName(name)) {
			throw new ResourceNotFoundException("Segment with id " + name + " not found.");
		}

		vkSegmentRepository.deleteBySegmentName(name);

		return true;
	}

}