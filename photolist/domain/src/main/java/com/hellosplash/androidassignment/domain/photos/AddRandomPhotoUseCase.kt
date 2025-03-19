package com.hellosplash.androidassignment.domain.photos

import com.hellosplash.androidassignment.common.domain.Resource
import com.hellosplash.androidassignment.photolist.data.Repository
import javax.inject.Inject

class AddRandomPhotoUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Resource<Unit> = runCatching {
        repository.getRandomPhoto()
        Resource.Success(Unit)
    }.getOrElse { Resource.Error }
}
