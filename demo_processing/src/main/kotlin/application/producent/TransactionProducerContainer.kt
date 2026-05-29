package org.example.application.producent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.example.domain.TransactionEvent
import org.example.infrastructure.kafka.PartitionSelector
import org.example.infrastructure.kafka.PartitionedQueue
import kotlin.coroutines.cancellation.CancellationException

class TransactionProducerContainer(
    private var partitionQueue: PartitionedQueue<TransactionEvent>,
    private val partitionSelector: PartitionSelector,
    private val coroutineScope: CoroutineScope,
) {
    private var producerJobs : MutableList<Job> = mutableListOf()

    fun start() {
        repeat(20) {
           val job = coroutineScope.launch {
               try {
                   val producer = TransactionProducer(partitionQueue, partitionSelector)
                   producer.produce()
               } catch (e: Exception) {
                   if (e is CancellationException) {
                       throw e;
                   }
                   println("Producer could not be started.")
               }
           }
            producerJobs.add(job)
        }
    }

    fun stop() {
        producerJobs.forEach { it.cancel() }
    }
}

