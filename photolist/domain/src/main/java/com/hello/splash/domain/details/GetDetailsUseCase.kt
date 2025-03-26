package com.hello.splash.domain.details

import com.hello.splash.common.domain.Resource
import com.hello.splash.photolist.data.Photo
import com.hello.splash.photolist.data.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(id: String): Flow<Resource<DetailsInfo>> =
        repository.getPhotoById(id)
            .map { createDetailsInfo(it) }
            .map { Resource.Success(it) as Resource<DetailsInfo> }
            .catch { emit(Resource.Error) }

    private fun createDetailsInfo(it: Photo) = DetailsInfo(
        it.locationName ?: "",
        it.likes ?: "",
        it.description ?: "",
        it.createdAt ?: ""
    )

    data class DetailsInfo(
        val locationName: String,
        val likes: String,
        val description: String,
        val creationDate: String
    )
}
