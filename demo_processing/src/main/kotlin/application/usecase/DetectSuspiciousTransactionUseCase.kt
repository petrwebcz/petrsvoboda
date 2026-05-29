package org.example.application.usecase

import org.example.domain.TransactionEvent
import org.example.domain.TransactionStatus
import org.example.infrastructure.database.EventStore
import java.time.Duration
import java.time.Instant

const val MAXIMUM_TRANSACTIONS_IN_INTERVAL = 5
const val MAXIMUM_TRANSACTIONS_INTERVAL_MINUTES = 1L

class DetectSuspiciousTransactionUseCase(
    private val eventStore: EventStore) {

    fun processTransaction(transaction: TransactionEvent) {
        val lastTransactions = eventStore.getEvents(
            transaction.userId,
            Instant.ofEpochMilli(transaction.timestamp)
                .minus(Duration.ofMinutes(MAXIMUM_TRANSACTIONS_INTERVAL_MINUTES))
                .toEpochMilli()
        )
        if (lastTransactions.count() > MAXIMUM_TRANSACTIONS_IN_INTERVAL) {
            println("Transaction blocked, transaction count: ${lastTransactions.count()}, user id: ${transaction.userId}")
            eventStore.addEvent(transaction.copy(transactionStatus = TransactionStatus.DECLINED))
        }

        println("Transaction accepted, transaction count: ${lastTransactions.count()}, user id: ${transaction.userId}")
        eventStore.addEvent(transaction.copy(transactionStatus = TransactionStatus.ACCEPTED))
    }
}
