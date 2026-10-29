package org.example.domain

data class Transaction(
    val eventId: String,
    val timestamp: Long,
    val userId: String,
    val amount: Double,
    val currency: String,
    val location: String,
    val type: String,
    val transactionStatus: TransactionStatus
) : Comparable<Transaction> {
    override fun compareTo(other: Transaction): Int {
        return timestamp.compareTo(other.timestamp)
    }
    fun decline() : Transaction {
        return this.copy(transactionStatus = TransactionStatus.DECLINED)
    }

    fun approve() : Transaction {
        return this.copy(transactionStatus = TransactionStatus.ACCEPTED)
    }
}

enum class TransactionStatus {
    CREATED, ACCEPTED, DECLINED
}