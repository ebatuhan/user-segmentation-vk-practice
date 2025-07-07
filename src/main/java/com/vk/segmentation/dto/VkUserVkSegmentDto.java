package com.vk.segmentation.dto;

import java.util.HashSet;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VkUserVkSegmentDto {
	private Long id;
	private Set<VkSegmentDto> vkSegments = new HashSet<>();
}