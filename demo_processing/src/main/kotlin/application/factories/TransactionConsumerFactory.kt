package org.example.application.factories

import org.example.consumers.TransactionConsumer
import org.example.domain.TransactionEvent
import org.example.infrastructure.kafka.PartitionedQueue

class TransactionConsumerFactory (
    private val partitionedQueue: PartitionedQueue<TransactionEvent>,
    private val detectSuspiciousTransactionUseCaseFactory: DetectSuspiciousTransactionUseCaseFactory) {
    fun create(partition: Int): TransactionConsumer {
        return TransactionConsumer(partitionedQueue, partition, detectSuspiciousTransactionUseCaseFactory)
    }
}