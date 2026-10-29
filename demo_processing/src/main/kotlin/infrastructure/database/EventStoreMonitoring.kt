package org.example.infrastructure.database

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit

class EventStoreMonitoring(
    private val eventStore: EventStore) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun start() {
        val users = 1..10

       repeat(10) {user ->
           scope.launch {
               val userId = user;
               while(coroutineContext.isActive) {
                   val allEvents = eventStore.getEvents(
                       userId.toString(),
                       Instant.now().minus(5, ChronoUnit.MINUTES).toEpochMilli())

                   val lastEvents = eventStore.getEvents(
                       userId.toString(),
                       Instant.now().minus(24, ChronoUnit.HOURS).toEpochMilli())

                   delay(10000)
                   println("User: $userId, number of total events: ${allEvents.count()}" +
                                   " last events: ${lastEvents.count()}")
               }
           }
       }
    }

    fun stop() {
        scope.cancel()
    }
}