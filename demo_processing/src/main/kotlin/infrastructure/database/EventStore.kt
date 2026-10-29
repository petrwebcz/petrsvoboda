package org.example.infrastructure.database

import org.example.domain.TransactionEvent
import java.util.Collections
import java.util.SortedSet
import java.util.TreeSet
import java.util.concurrent.ConcurrentHashMap

class EventStore {
    private val eventStore: ConcurrentHashMap<String, TreeSet<TransactionEvent>> = ConcurrentHashMap()

    fun addEvent(event: TransactionEvent) {
        val events = eventStore.computeIfAbsent(event.userId) {sortedSetOf<TransactionEvent>()}
        events.add(event)
    }

    fun getEvents(userId: String, timestamp: Long): SortedSet<TransactionEvent> {
        val events = eventStore.computeIfAbsent(userId) { sortedSetOf<TransactionEvent>() }
        val iterator = events.descendingIterator()

        while (iterator.hasNext()) {
            val event = iterator.next()

            if (event.timestamp < timestamp) {
                return events.tailSet(event)
            }
        }
        return events
    }
}
