package com.vk.segmentation.service;

import java.util.List;

import com.vk.segmentation.dto.VkSegmentDto;
import com.vk.segmentation.dto.VkSegmentPostDto;
import com.vk.segmentation.dto.VkUserDto;
import com.vk.segmentation.dto.VkUserPostDto;
import com.vk.segmentation.dto.VkUserVkSegmentDto;

public interface VkUserService {
    VkUserDto findById(Long id) throws RuntimeException;

    List<VkUserDto> findAll() throws RuntimeException;

    VkUserDto createVkUser(VkUserPostDto vkUser) throws RuntimeException;

    VkUserDto updateVkUser(Long id, VkUserPostDto vkUser) throws RuntimeException;

    boolean deleteUser(Long id) throws RuntimeException;

    VkUserVkSegmentDto findAllSegmentsOfVkUserById(Long id);

    VkUserVkSegmentDto addSegmentToVkUser(Long id, VkSegmentPostDto vkSegmentPostDto);
    
    boolean removeSegmentFromVkUser(Long id, VkSegmentPostDto vkSegmentPostDto);

}