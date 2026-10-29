package org.example.consumers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.example.application.factories.TransactionConsumerFactory
import org.example.domain.TransactionEvent
import org.example.infrastructure.kafka.PartitionedQueue
import kotlin.coroutines.cancellation.CancellationException

class ConsumersOrchestrator (
    private val coroutineScope: CoroutineScope,
    private val transactionConsumerFactory: TransactionConsumerFactory
) {
    private var consumersJobs : MutableList<Job> = mutableListOf()

    fun start() {
        val range = 1..10
        for (i in range) {
            val job = coroutineScope.launch {
                try {
                    val consumer = transactionConsumerFactory.create(i);
                    consumer.consume()
                } catch (e: Exception) {
                    if (e is CancellationException) {
                        throw e;
                    }
                    println("Consumer could not be started.")
                }
            }
            consumersJobs.add(job)
        }
    }

    fun stop() {
        consumersJobs.forEach { it.cancel() }
    }
}