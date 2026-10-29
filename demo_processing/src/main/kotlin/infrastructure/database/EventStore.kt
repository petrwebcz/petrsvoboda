package org.example.infrastructure.database

import org.example.domain.Transaction
import java.util.SortedSet
import java.util.TreeSet
import java.util.concurrent.ConcurrentHashMap

class EventStore {
    private val eventStore: ConcurrentHashMap<String, TreeSet<Transaction>> = ConcurrentHashMap()

    fun addEvent(event: Transaction) {
        val events = eventStore.computeIfAbsent(event.userId) {sortedSetOf<Transaction>()}
        events.add(event)
    }

    fun getEvents(userId: String, timestamp: Long): SortedSet<Transaction> {
        val events = eventStore.computeIfAbsent(userId) { sortedSetOf<Transaction>() }
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
