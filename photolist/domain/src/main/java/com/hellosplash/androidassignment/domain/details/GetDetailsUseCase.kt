package com.hellosplash.androidassignment.domain.details

import com.hellosplash.androidassignment.common.domain.Resource
import com.hellosplash.androidassignment.photolist.data.Photo
import com.hellosplash.androidassignment.photolist.data.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(id: String): Flow<Resource<DetailsInfo>> =
        repository.getPhotoById(id)
            .map { createDetailsInfo(it) }
            .map { Resource.Success(it) as Resource<DetailsInfo> }
            .catch { emit(Resource.Error) }

    private fun createDetailsInfo(it: Photo) = DetailsInfo(
        it.locationName ?: "",
        it.likes ?: "",
        it.description ?: ""
    )

    data class DetailsInfo(val locationName: String, val likes: String, val description: String)
}