package com.hello.splash

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hello.splash.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddPhotoKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAddPhotoAddsPhotoToGrid() {
        // Arrange
        val initialItems = composeTestRule.onAllNodesWithTag("grid_item")
        val initialCount = initialItems.fetchSemanticsNodes().size

        // Act
        composeTestRule.onNodeWithText("Add photo").performClick()
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            try {
                composeTestRule.onNodeWithTag("card_item").assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }

        // Assert
        val updatedItems = composeTestRule.onAllNodesWithTag("card_item")
        val updatedCount = updatedItems.fetchSemanticsNodes().size
        assert(updatedCount == initialCount + 1)
    }

}
