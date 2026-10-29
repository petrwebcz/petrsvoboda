package org.example.application.factories

import org.example.consumers.TransactionConsumer
import org.example.domain.Transaction
import org.example.infrastructure.kafka.PartitionedQueue

class TransactionConsumerFactory (
    private val partitionedQueue: PartitionedQueue<Transaction>,
    private val detectSuspiciousTransactionUseCaseFactory: DetectSuspiciousTransactionUseCaseFactory) {
    fun create(partition: Int): TransactionConsumer {
        return TransactionConsumer(partitionedQueue, partition, detectSuspiciousTransactionUseCaseFactory)
    }
}