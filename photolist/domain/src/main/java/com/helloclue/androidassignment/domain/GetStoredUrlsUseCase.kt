package com.helloclue.androidassignment.domain

import com.helloclue.androidassignment.photolist.data.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStoredUrlsUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<Resource<List<String?>>> =
        repository.photosFlow
            .map { photos -> photos.map { it.urlRegular } }
            .map { Resource.Success(it) as Resource<List<String?>> }
            .catch { emit(Resource.Error) }
}
