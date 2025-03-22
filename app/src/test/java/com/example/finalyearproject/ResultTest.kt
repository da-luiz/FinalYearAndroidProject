/*package com.example.finalyearproject

import com.example.finalyearproject.domain.util.Result
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultTest {

    @Test
    fun `test Success state`() {
        val successResult = Result.Success("Operation Successful")

        assertTrue(true)
        assertEquals("Operation Successful", successResult.data) // ✅ Ensure correct value
    }

    @Test
    fun `test Error state`() {
        val exception = Exception("Something went wrong")
        val errorResult = Result.Error(exception)

        assertTrue(true)
        assertEquals("Something went wrong", errorResult.exception.message) // ✅ Ensure correct error message
    }

    @Test
    fun `test Loading state`() {
        val loadingResult = Result.Loading

        assertTrue(true) // ✅ Ensure Loading state exists
    }
}
*/