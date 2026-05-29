package org.example.application.producent

import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import org.example.domain.TransactionEvent
import org.example.domain.TransactionStatus
import org.example.infrastructure.kafka.PartitionSelector
import org.example.infrastructure.kafka.PartitionedQueue
import java.time.Instant
import java.util.UUID
import kotlin.coroutines.coroutineContext

class TransactionProducer(
    private val partitionedQueue: PartitionedQueue<TransactionEvent>,
    private val partitionSelector: PartitionSelector
) {
    suspend fun produce() {
        while (coroutineContext.isActive) {
            val userId = (1..20).random().toString()
            val partition = partitionSelector.getPartition(userId, 10)
            partitionedQueue.add(
                partition, TransactionEvent(
                    eventId = UUID.randomUUID().toString(),
                    timestamp = Instant.now().toEpochMilli(),
                    userId = userId,
                    amount = 1.25,
                    currency = "CZK",
                    location = "Prague",
                    type = "CARD_PAYMENT",
                    transactionStatus = TransactionStatus.CREATED,
                )
            )
            delay(100)
        }
    }
}