package org.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.runBlocking
import org.example.application.factories.DetectSuspiciousTransactionUseCaseFactory
import org.example.application.factories.TransactionConsumerFactory
import org.example.application.producent.TransactionProducerContainer
import org.example.consumers.ConsumersOrchestrator
import org.example.domain.TransactionEvent
import org.example.infrastructure.database.EventStore
import org.example.infrastructure.database.EventStoreMonitoring
import org.example.infrastructure.kafka.PartitionSelector
import org.example.infrastructure.kafka.PartitionedQueue

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val name = "Kotlin"
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
  runBlocking {
      val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
      val partitionSelector = PartitionSelector()
      val partitionedQueue = PartitionedQueue<TransactionEvent>();
      val eventStore = EventStore();

      val detectSuspiciousTransactionUseCaseFactory = DetectSuspiciousTransactionUseCaseFactory(eventStore)
      val transactionConsumerFactory = TransactionConsumerFactory(partitionedQueue, detectSuspiciousTransactionUseCaseFactory)
      val detectSuspiciousTransactionOrchestrator = ConsumersOrchestrator(coroutineScope, transactionConsumerFactory)

      val producerContainers = TransactionProducerContainer(
          partitionedQueue,
          partitionSelector,
          coroutineScope)


      detectSuspiciousTransactionOrchestrator.start()
      producerContainers.start()
      awaitCancellation()
  }
}