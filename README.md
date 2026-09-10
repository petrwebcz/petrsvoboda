Repository to demonstrate my code abilities. 

1) Leetcode/sliding_window_find_longest_substring_with_unique_characters
 * It demonstrates solving algorythm https://leetcode.com/problems/longest-substring-without-repeating-characters/description/ 
 
2)  Processing of Suspicious Transactions
 This application demonstrates
a) Knowladge about architecture of scalable processing. Kafka si replaced by custom paritionier where is load is distributed among partition in balanced way.
b) Knowladge java structures like TreeSet.
 
 A producer sends data to a partitioned queue.
 The producer ensures that users with the same userId are assigned to the same partition.
 The partition number is calculated as: key.hashCode() % partitionCount

EventStore is implemented as an in-memory event-sourced database backed by a sorted set (TreeSet).

