package com.coursy.be.service

import arrow.core.left
import arrow.core.right
import com.coursy.be.model.platform.*
import com.coursy.be.repository.PlatformRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PlatformService(val repo: PlatformRepository) {
    fun getAllPlatforms(): List<PlatformDto> =
        repo.findAll().map(Platform::toDto)

    fun savePlatform(platform: PlatformDto) = repo.save(platform.toModel())
    
    fun deletePlatform(id: Long) = repo.deleteById(id)

    fun getById(id: Long) =
        repo.findByIdOrNull(id)?.toDto()?.right() ?: PlatformFailure.NotFound(id).left()
}