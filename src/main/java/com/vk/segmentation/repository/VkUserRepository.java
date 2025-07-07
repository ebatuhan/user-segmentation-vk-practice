package com.vk.segmentation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vk.segmentation.entity.VkUser;

public interface VkUserRepository extends JpaRepository<VkUser, Long> {
    
}