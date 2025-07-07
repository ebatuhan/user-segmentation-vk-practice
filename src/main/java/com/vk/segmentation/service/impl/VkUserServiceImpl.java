package com.vk.segmentation.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.vk.segmentation.dto.VkUserDto;
import com.vk.segmentation.dto.VkUserPostDto;
import com.vk.segmentation.entity.VkUser;
import com.vk.segmentation.exception.ResourceNotFoundException;
import com.vk.segmentation.repository.VkUserRepository;
import com.vk.segmentation.service.VkUserService;


@Service
public class VkUserServiceImpl implements VkUserService {

	private final VkUserRepository vkUserRepository;
	private final ModelMapper modelMapper;

	public VkUserServiceImpl(VkUserRepository vkUserRepository, ModelMapper modelMapper) {
		this.vkUserRepository = vkUserRepository;
		this.modelMapper = modelMapper;
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
}
