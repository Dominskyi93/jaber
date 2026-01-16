@file:OptIn(ExperimentalCoroutinesApi::class)

package com.messenger.core.essentials.flows

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

/**
 * Emits at most one value per [periodMillis] from the upstream Flow.
 *
 * Implementation details:
 * - Collects the upstream Flow into a conflated Channel, keeping only the latest value.
 * - Continuously receives values from that Channel.
 * - For each received value:
 *   - Emits it downstream.
 *   - Waits for [periodMillis] before allowing the next emission.
 *
 * Important notes:
 * - Uses channelFlow to allow concurrent send and delay.
 * - Launches two child coroutines per emission (one for send, one for delay).
 * - Backpressure is handled via Channel.CONFLATED (older values are dropped).
 * - Cancellation is cooperative but fragile due to manual channel management.
 */
fun <T> Flow<T>.throttle(
    periodMillis: Long,
): Flow<T> {
    return channelFlow {
        val channel = produce(capacity = Channel.CONFLATED) {
            collect { send(it) }
        }
        while (true) {
            channel.receiveCatching()
                .onSuccess {
                    val sendJob = launch {
                        send(it)
                    }
                    val delayJob = launch {
                        delay(periodMillis)
                    }
                    sendJob.join()
                    delayJob.join()
                }
                .onClosed { break }
                .onFailure { it?.let { throw it } }
        }
    }
}