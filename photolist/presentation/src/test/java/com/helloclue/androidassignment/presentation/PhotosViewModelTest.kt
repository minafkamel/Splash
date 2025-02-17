package com.helloclue.androidassignment.presentation

import app.cash.turbine.test
import com.helloclue.androidassignment.domain.AddRandomPhotoUseCase
import com.helloclue.androidassignment.domain.GetStoredUrlsUseCase
import com.helloclue.androidassignment.domain.Resource
import com.helloclue.androidassignment.presentation.photos.PhotosViewModel
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
    private var getStoredUrlsUseCase: GetStoredUrlsUseCase = mock()

    private lateinit var sut: PhotosViewModel

    @Test
    fun `loadStoredUrlsUiState emits Success with urls when getStoredUrlsUseCase emits`() =
        runTest {
            // Arrange
            val urls = listOf("url1", "url2")
            whenever(getStoredUrlsUseCase.invoke()).thenReturn(flowOf(Resource.Success(urls)))

            // Act
            sut = PhotosViewModel(addRandomPhotoUseCase, getStoredUrlsUseCase)

            // Assert
            sut.loadStoredUrlsUiState.test {
                assertEquals(UiState.Loading, awaitItem())
                assertEquals(UiState.Success(urls), awaitItem())
            }
        }

    @Test
    fun `loadStoredUrlsUiState emits error when getStoredUrlsUseCase emits error`() = runTest {
        // Arrange
        whenever(getStoredUrlsUseCase.invoke()).thenReturn(flowOf(Resource.Error))

        // Act
        sut = PhotosViewModel(addRandomPhotoUseCase, getStoredUrlsUseCase)

        // Assert
        sut.loadStoredUrlsUiState.test {
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Error("Error loading photos urls"), awaitItem())
        }
    }

    @Test
    fun `addPhotoUiState emits Success when addRandomPhotoUseCase returns Success`() = runTest {
        // Arrange
        whenever(getStoredUrlsUseCase.invoke()).thenReturn(flowOf(Resource.Success(listOf())))
        whenever(addRandomPhotoUseCase.invoke()).thenReturn(Resource.Success(Unit))

        // Act
        sut = PhotosViewModel(addRandomPhotoUseCase, getStoredUrlsUseCase)
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
        whenever(getStoredUrlsUseCase.invoke()).thenReturn(flowOf(Resource.Success(listOf())))
        whenever(addRandomPhotoUseCase.invoke()).thenReturn(Resource.Error)

        // Act
        sut = PhotosViewModel(addRandomPhotoUseCase, getStoredUrlsUseCase)
        sut.addPhotoClicked()

        // Assert
        sut.addPhotoUiState.test {
            assertEquals(UiState.Init, awaitItem())
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Error("Error adding photo"), awaitItem())
        }
    }
}
