package com.helloclue.androidassignment.domain.photos

import com.helloclue.androidassignment.common.domain.Resource
import com.helloclue.androidassignment.photolist.data.Repository
import javax.inject.Inject

class AddRandomPhotoUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Resource<Unit> = runCatching {
        repository.getRandomPhoto()
        Resource.Success(Unit)
    }.getOrElse { Resource.Error }
}
