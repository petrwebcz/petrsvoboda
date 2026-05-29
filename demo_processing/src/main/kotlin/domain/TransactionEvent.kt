package org.example.domain

data class TransactionEvent(
    val eventId: String,
    val timestamp: Long,
    val userId: String,
    val amount: Double,
    val currency: String,
    val location: String,
    val type: String,
    val transactionStatus: TransactionStatus
) : Comparable<TransactionEvent> {
    override fun compareTo(other: TransactionEvent): Int {
        return timestamp.compareTo(other.timestamp)
    }
}

enum class TransactionStatus {
    CREATED, ACCEPTED, DECLINED
}