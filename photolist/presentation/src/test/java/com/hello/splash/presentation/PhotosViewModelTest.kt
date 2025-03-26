package com.hello.splash.presentation

import app.cash.turbine.test
import com.hello.splash.domain.photos.AddRandomPhotoUseCase
import com.hello.splash.domain.photos.GetPhotosUseCase
import com.hello.splash.domain.photos.GetPhotosUseCase.PhotoInfo
import com.hello.splash.common.domain.Resource
import com.hello.splash.presentation.photos.PhotosViewModel
import com.hello.splash.presentation.photos.grid.PhotoUi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class PhotosViewModelTest {

    @get:Rule
    val testDispatcherRule = MainDispatcherRule()

    private var addRandomPhotoUseCase: AddRandomPhotoUseCase = mock()
    private var getPhotosUseCase: GetPhotosUseCase = mock()

    private lateinit var sut: PhotosViewModel

    @Test
    fun `loadStoredUrlsUiState emits Success with urls when getStoredUrlsUseCase emits`() =
        runTest {
            // Arrange
            val photos = listOf(PhotoInfo("123", "url1"), PhotoInfo("456", "url2"))
            val photoUi = listOf(PhotoUi("123", "url1"), PhotoUi("456", "url2"))
            whenever(getPhotosUseCase.invoke()).thenReturn(flowOf(Resource.Success(photos)))

            // Act
            sut = PhotosViewModel(addRandomPhotoUseCase, getPhotosUseCase)

            // Assert
            sut.loadStoredUrlsUiState.test {
                assertEquals(UiState.Loading, awaitItem())
                assertEquals(UiState.Success(photoUi), awaitItem())
            }
        }

    @Test
    fun `loadStoredUrlsUiState emits error when getStoredUrlsUseCase emits error`() = runTest {
        // Arrange
        whenever(getPhotosUseCase.invoke()).thenReturn(flowOf(Resource.Error))

        // Act
        sut = PhotosViewModel(addRandomPhotoUseCase, getPhotosUseCase)

        // Assert
        sut.loadStoredUrlsUiState.test {
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Error("Error loading photos urls"), awaitItem())
        }
    }

    @Test
    fun `addPhotoUiState emits Success when addRandomPhotoUseCase returns Success`() = runTest {
        // Arrange
        whenever(getPhotosUseCase.invoke()).thenReturn(flowOf(Resource.Success(listOf())))
        whenever(addRandomPhotoUseCase.invoke()).thenReturn(Resource.Success(Unit))

        // Act
        sut = PhotosViewModel(addRandomPhotoUseCase, getPhotosUseCase)
        sut.addPhotoClicked()

        // Assert
        sut.addPhotoUiState.test {
            assertEquals(UiState.Init, awaitItem())
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Success(Unit), awaitItem())
        }
    }

    @Test
    fun `addPhotoUiState emits Error when addRandomPhotoUseCase returns Error`() = runTest {
        // Arrange
        whenever(getPhotosUseCase.invoke()).thenReturn(flowOf(Resource.Success(listOf())))
        whenever(addRandomPhotoUseCase.invoke()).thenReturn(Resource.Error)

        // Act
        sut = PhotosViewModel(addRandomPhotoUseCase, getPhotosUseCase)
        sut.addPhotoClicked()

        // Assert
        sut.addPhotoUiState.test {
            assertEquals(UiState.Init, awaitItem())
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Error("Error adding photo"), awaitItem())
        }
    }
}
