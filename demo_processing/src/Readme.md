**Processing of suspicious transactions**
Application demonstrate usage of scalable processing.
Producer sends data into partitioned queue. 
Producer ensure that users with the same userid belongs to the same partition.
Partition number is calculated as result of key.hashCode() % partitionCount

EventStore is implemented as In Memory Event sourced database is backed by sorted set (TreeSet) . 