package com.helloclue.androidassignment.domain

import app.cash.turbine.test
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

class GetStoredUrlsUseCaseTest {

    private val repository: Repository = mock()

    lateinit var sut: GetStoredUrlsUseCase

    @Before
    fun setup() {
        sut = GetStoredUrlsUseCase(repository)
    }

    @Test
    fun `emits Resource Success with list of URLs when repository emits photos`(): Unit = runTest {
        // Arrange
        val photos = listOf(
            defaultPhoto.copy(urlRegular = "url1"),
            defaultPhoto.copy(urlRegular = "url2"),
            defaultPhoto.copy(urlRegular = "url3"),
        )
        val expectedUrls = photos.map { it.urlRegular }

        whenever(repository.photosFlow).thenReturn(flowOf(photos))

        // Act
        val resource = sut.invoke().first()

        // Assert
        assertTrue(resource is Resource.Success)
        assertEquals(expectedUrls, (resource as Resource.Success).data)
    }

    val defaultPhoto = Photo(
        id = "dummyId",
        urlRegular = "dummyUrl",
        description = "dummyDescription",
        locationName = "dummyLOcationName",
        likes = "dummyLikes",
    )
}