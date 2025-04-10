package com.hello.splash.domain.delete

import com.hello.splash.common.domain.Resource
import com.hello.splash.photolist.data.Repository
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(id: String): Resource<Unit> = runCatching {
        repository.deletePhotoBy(id)
        Resource.Success(Unit)
    }.getOrElse { Resource.Error }
}
