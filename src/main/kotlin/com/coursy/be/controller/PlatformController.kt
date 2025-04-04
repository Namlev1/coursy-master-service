package com.coursy.be.controller

import com.coursy.be.model.platform.PlatformDto
import com.coursy.be.model.platform.PlatformFailure
import com.coursy.be.service.PlatformService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/platform")
class PlatformController(val service: PlatformService) {

    @GetMapping
    fun getAllPlatforms() = service.getAllPlatforms()

    @GetMapping("/{id}")
    fun getPlatformById(@PathVariable id: Long) = service.getById(id).fold(
        { failure -> handleFailure(failure) },
        { platformDto -> ResponseEntity.ok(platformDto) }
    )

    @PostMapping
    fun createPlatform(@RequestBody dto: PlatformDto) = service.savePlatform(dto)

    @DeleteMapping("/{id}")
    fun deletePlatform(@PathVariable id: Long) = service.deletePlatform(id)

    private fun handleFailure(failure: PlatformFailure) =
        when (failure) {
            is PlatformFailure.NotFound -> ResponseEntity.notFound().build<Any>()
        }
}