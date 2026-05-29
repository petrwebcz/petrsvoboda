package org.example.application.factories

import org.example.application.usecase.DetectSuspiciousTransactionUseCase
import org.example.infrastructure.database.EventStore

class DetectSuspiciousTransactionUseCaseFactory (
    private val eventStore: EventStore
) {
    fun create(): DetectSuspiciousTransactionUseCase {
        return DetectSuspiciousTransactionUseCase(eventStore)
    }
}