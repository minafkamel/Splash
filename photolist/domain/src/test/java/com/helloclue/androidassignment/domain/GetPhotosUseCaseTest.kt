package com.helloclue.androidassignment.domain

import com.helloclue.androidassignment.common.domain.Resource
import com.helloclue.androidassignment.domain.photos.GetPhotosUseCase
import com.helloclue.androidassignment.domain.photos.GetPhotosUseCase.PhotoInfo
import com.helloclue.androidassignment.photolist.data.Photo
import com.helloclue.androidassignment.photolist.data.Repository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class GetPhotosUseCaseTest {

    private val repository: Repository = mock()

    lateinit var sut: GetPhotosUseCase

    @Before
    fun setup() {
        sut = GetPhotosUseCase(repository)
    }

    @Test
    fun `emits Resource Success with list of URLs when repository emits photos`(): Unit = runTest {
        // Arrange
        val photos = listOf(
            defaultPhoto.copy(id = "123", urlRegular = "url1"),
            defaultPhoto.copy(id = "456", urlRegular = "url2"),
            defaultPhoto.copy(id = "789", urlRegular = "url3"),
        )
        val expectedPhotos = photos.map { PhotoInfo(it.id, it.urlRegular!!) }

        whenever(repository.photosFlow).thenReturn(flowOf(photos))

        // Act
        val resource = sut.invoke().first()

        // Assert
        assertTrue(resource is Resource.Success)
        assertEquals(expectedPhotos, (resource as Resource.Success).data)
    }

    @Test
    fun `emits Resource Error with an url is null`(): Unit = runTest {
        // Arrange
        val photos = listOf(defaultPhoto.copy(urlRegular = null))

        whenever(repository.photosFlow).thenReturn(flowOf(photos))

        // Act
        val resource = sut.invoke().first()

        // Assert
        assertTrue(resource is Resource.Error)
    }

    val defaultPhoto = Photo(
        id = "dummyId",
        urlRegular = "dummyUrl",
        description = "dummyDescription",
        locationName = "dummyLOcationName",
        likes = "dummyLikes",
    )
}