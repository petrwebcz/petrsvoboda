package org.example.consumers

import org.example.domain.TransactionEvent
import org.example.infrastructure.kafka.PartitionedQueue
import kotlinx.coroutines.*
import org.example.application.factories.DetectSuspiciousTransactionUseCaseFactory
import kotlin.coroutines.coroutineContext

class TransactionConsumer(
    private var partitionedQueue: PartitionedQueue<TransactionEvent>,
    private var partition: Int,
    private val detectSuspiciousTransactionUseCaseFactory: DetectSuspiciousTransactionUseCaseFactory) {

    suspend fun consume() {
        println("Consumer for partition $partition has been started")
        while(coroutineContext.isActive) {
            val transaction = partitionedQueue.get(partition);
            if (transaction != null) {
                val detectSuspiciousTransactionUseCase = detectSuspiciousTransactionUseCaseFactory.create()
                detectSuspiciousTransactionUseCase.processTransaction(transaction)
            }
        }
    }
}