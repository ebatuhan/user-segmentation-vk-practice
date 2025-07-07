package com.vk.segmentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vksegment")
public class VkSegment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;

	private String segmentName;	
    
}