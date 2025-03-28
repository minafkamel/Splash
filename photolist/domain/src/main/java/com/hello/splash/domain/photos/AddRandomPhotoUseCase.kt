package com.hello.splash.domain.photos

import com.hello.splash.common.domain.Resource
import com.hello.splash.photolist.data.Repository
import javax.inject.Inject

class AddRandomPhotoUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Resource<Unit> = runCatching {
        repository.getRandomPhoto()
        Resource.Success(Unit)
    }.getOrElse { Resource.Error }
}
