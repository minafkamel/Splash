package com.helloclue.androidassignment.domain.photos

import com.helloclue.androidassignment.domain.Resource
import com.helloclue.androidassignment.photolist.data.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<Resource<List<PhotoInfo>>> =
        repository.photosFlow
            .map { photos -> photos.map { PhotoInfo(it.id, checkNotNull(it.urlRegular)) } }
            .map { Resource.Success(it) as Resource<List<PhotoInfo>> }
            .catch { emit(Resource.Error) }

    data class PhotoInfo(val id: String, val url: String)
}
