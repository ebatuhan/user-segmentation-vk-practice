package com.vk.segmentation.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.vk.segmentation.dto.VkSegmentDto;
import com.vk.segmentation.dto.VkSegmentPostDto;
import com.vk.segmentation.dto.VkUserDto;
import com.vk.segmentation.dto.VkUserPostDto;
import com.vk.segmentation.dto.VkUserVkSegmentDto;
import com.vk.segmentation.entity.VkSegment;
import com.vk.segmentation.entity.VkUser;
import com.vk.segmentation.exception.ResourceNotFoundException;
import com.vk.segmentation.repository.VkSegmentRepository;
import com.vk.segmentation.repository.VkUserRepository;
import com.vk.segmentation.service.VkUserService;

@Service
public class VkUserServiceImpl implements VkUserService {

	private final VkUserRepository vkUserRepository;
	private final ModelMapper modelMapper;
	private final VkSegmentRepository vkSegmentRepository;

	public VkUserServiceImpl(VkUserRepository vkUserRepository, ModelMapper modelMapper,
			VkSegmentRepository vkSegmentRepository) {
		this.vkUserRepository = vkUserRepository;
		this.modelMapper = modelMapper;
		this.vkSegmentRepository = vkSegmentRepository;
	}

	@Override
	public VkUserDto findById(Long id) throws RuntimeException {
		VkUser user = vkUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

		return modelMapper.map(user, VkUserDto.class);
	}

	@Override
	public List<VkUserDto> findAll() throws RuntimeException {
		List<VkUser> vkUsers = Optional.of(vkUserRepository.findAll())
				.filter(list -> !list.isEmpty())
				.orElseThrow(() -> new ResourceNotFoundException("No users found"));

		return vkUsers.stream()
				.map(user -> modelMapper.map(user, VkUserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public VkUserDto createVkUser(VkUserPostDto vkUser) {
		VkUser user = modelMapper.map(vkUser, VkUser.class);
		VkUser savedUser = vkUserRepository.save(user);

		return modelMapper.map(savedUser, VkUserDto.class);
	}

	@Override
	public boolean deleteUser(Long id) throws RuntimeException {
		if (!vkUserRepository.existsById(id)) {
			throw new ResourceNotFoundException("User with id " + id + " not found");
		}

		vkUserRepository.deleteById(id);
		return true;
	}

	@Override
	public VkUserDto updateVkUser(Long id, VkUserPostDto vkUserDto) throws RuntimeException {
		VkUser existingUser = vkUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found."));

		modelMapper.map(vkUserDto, existingUser);

		VkUser updatedUser = vkUserRepository.save(existingUser);
		return modelMapper.map(updatedUser, VkUserDto.class);
	}

	@Override
	public VkUserVkSegmentDto findAllSegmentsOfVkUserById(Long id) {
		VkUser existingUser = vkUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found."));

		VkUserVkSegmentDto vkUserVkSegmentDto = new VkUserVkSegmentDto();
		vkUserVkSegmentDto.setId(existingUser.getId());

		Set<VkSegmentDto> vkSegmentsDto = existingUser.getVkSegments()
				.stream()
				.map(segment -> modelMapper.map(segment, VkSegmentDto.class))
				.collect(Collectors.toSet());

		vkUserVkSegmentDto.setVkSegments(vkSegmentsDto);

		return vkUserVkSegmentDto;

	}

	@Override
	public VkUserVkSegmentDto addSegmentToVkUser(Long userId, VkSegmentPostDto vkSegmentPostDto) {
		VkUser existingUser = vkUserRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found."));

		VkSegment vkSegment = vkSegmentRepository.findBySegmentName(vkSegmentPostDto.getSegmentName())
				.orElseThrow(() -> new ResourceNotFoundException("Such segment not exists"));

		existingUser.getVkSegments().add(vkSegment);
		VkUser updatedUser = vkUserRepository.save(existingUser);

		VkUserVkSegmentDto dto = new VkUserVkSegmentDto();
		dto.setId(updatedUser.getId());

		Set<VkSegmentDto> segmentDtos = updatedUser.getVkSegments()
				.stream()
				.map(segment -> modelMapper.map(segment, VkSegmentDto.class))
				.collect(Collectors.toSet());

		dto.setVkSegments(segmentDtos);
		return dto;
	}

	@Override
	public boolean removeSegmentFromVkUser(Long userId, VkSegmentPostDto vkSegmentPostDto) {
		VkUser existingUser = vkUserRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found."));

		VkSegment vkSegment = vkSegmentRepository.findBySegmentName(vkSegmentPostDto.getSegmentName())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Segment with name " + vkSegmentPostDto.getSegmentName() + " not found."));

		boolean removed = existingUser.getVkSegments().remove(vkSegment);
		if (!removed) {
			throw new ResourceNotFoundException("Segment is not associated with the user.");
		}

		vkUserRepository.save(existingUser);
		return true;
	}
}
