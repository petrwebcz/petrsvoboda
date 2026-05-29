package org.example.infrastructure.kafka

import kotlinx.coroutines.channels.Channel
import java.util.concurrent.ConcurrentHashMap

class PartitionedQueue<T>(
) {
    private val partitions: ConcurrentHashMap<Int, Channel<T>> = ConcurrentHashMap()

    suspend fun add(partition:Int, value: T) {
        val partition = getPartition(partition)
        return partition.send(value)
    }

    suspend fun get(partition: Int): T? {
        val partition = getPartition(partition)
        return partition.receive()
    }

    private fun getPartition(partition: Int): Channel<T> {
       return partitions.computeIfAbsent(partition) { Channel (Channel.UNLIMITED) }
    }
}

