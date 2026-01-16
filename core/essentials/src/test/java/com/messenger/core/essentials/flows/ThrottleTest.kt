package com.messenger.core.essentials.flows

import com.uandcode.flowtest.runFlowTest
import kotlinx.coroutines.flow.MutableSharedFlow
import org.junit.Assert.assertEquals
import org.junit.Test

class ThrottleTest {
    @Test
    fun `GIVEN first item WHEN throttle THEN emit immediately`() = runFlowTest {
        val inputFlow = MutableSharedFlow<String>()
        val collector = inputFlow
            .throttle(100)
            .startCollecting()

        inputFlow.emit("item1")

        assertEquals(1, collector.count)
        assertEquals("item1", collector.lastItem)
    }

    @Test
    fun `GIVEN second item WHEN throttle THEN emit after delay`() = runFlowTest {
        val inputFlow = MutableSharedFlow<String>()
        val collector = inputFlow
            .throttle(100)
            .startCollecting()

        inputFlow.emit("item1")
        inputFlow.emit("item2")

        advanceTimeBy(99)
        assertEquals(1, collector.count)
        assertEquals("item1", collector.lastItem)

        advanceTimeBy(2)
        assertEquals(2, collector.count)
        assertEquals("item2", collector.lastItem)
    }

    @Test
    fun `GIVEN second item after large delay WHEN throttle THEN emit immediately`() = runFlowTest {
        val inputFlow = MutableSharedFlow<String>()
        val collector = inputFlow
            .throttle(100)
            .startCollecting()

        inputFlow.emit("item1")
        advanceTimeBy(101)
        inputFlow.emit("item2")

        assertEquals(2, collector.count)
        assertEquals("item2", collector.lastItem)
    }

}