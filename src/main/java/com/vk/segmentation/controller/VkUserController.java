package com.vk.segmentation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.segmentation.dto.VkSegmentPostDto;
import com.vk.segmentation.dto.VkUserDto;
import com.vk.segmentation.dto.VkUserPostDto;
import com.vk.segmentation.dto.VkUserVkSegmentDto;
import com.vk.segmentation.service.VkUserService;

@RestController
@RequestMapping("/user")
public class VkUserController {
	private final VkUserService vkUserService;

	public VkUserController(VkUserService vkUserService){
		this.vkUserService = vkUserService;
	}	

	@GetMapping("/all")
	public List<VkUserDto> findAll(){
		return vkUserService.findAll();
	}

	@GetMapping("/{id}")
	public VkUserDto findById(@PathVariable Long id){
		return vkUserService.findById(id);
	}

	@PostMapping
	public VkUserDto VkUserDto(@RequestBody VkUserPostDto vkUser){
		return vkUserService.createVkUser(vkUser);
	}

	@PutMapping("/{id}") //added to mock, nothing to update, postdto has no fields yet...
	public VkUserDto updateVkUser(@PathVariable Long id, @RequestBody VkUserPostDto vkUser){
		return vkUserService.updateVkUser(id, vkUser);
	}

	@DeleteMapping("/{id}")
	public boolean deleteVkUser(@PathVariable Long id){
		return vkUserService.deleteUser(id);
	}

	@GetMapping("/{id}/segments")
	public VkUserVkSegmentDto findAllSegmentsOfVkUserById(@PathVariable Long id){
		return vkUserService.findAllSegmentsOfVkUserById(id);
	}

	@PutMapping("/{id}/segments")
	public VkUserVkSegmentDto addSegmentToVkUser(@PathVariable Long id, @RequestBody VkSegmentPostDto vkSegmentPostDto){
		return vkUserService.addSegmentToVkUser(id, vkSegmentPostDto);
	}

	@DeleteMapping("/{id}/segments")
	public boolean removeSegmentFromVkUser(Long id, VkSegmentPostDto vkSegmentPostDto){
		return vkUserService.removeSegmentFromVkUser(id, vkSegmentPostDto);
	}
}
    
