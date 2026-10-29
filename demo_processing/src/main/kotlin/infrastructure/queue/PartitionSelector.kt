package org.example.infrastructure.kafka

class PartitionSelector {
    fun getPartition(key: String, partitionCount: Int) : Int {
        val partition = Math.abs(key.hashCode()) % partitionCount;
        println("${key} with hashcode ${key.hashCode()} belongs to partition: ${key.hashCode() % partitionCount}")
        return partition
    }
}