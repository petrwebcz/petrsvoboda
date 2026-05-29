# Processing of Suspicious Transactions

# 

# This application demonstrates the use of scalable processing.

# 

# A producer sends data to a partitioned queue.

# 

# The producer ensures that users with the same userId are assigned to the same partition.

# 

# The partition number is calculated as:

# 

# key.hashCode() % partitionCount

# 

# EventStore is implemented as an in-memory event-sourced database backed by a sorted set (TreeSet).

