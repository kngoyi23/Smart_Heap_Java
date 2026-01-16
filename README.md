# Smart Heap (Java)

Smart Heap is a Java implementation of a dynamic **priority queue** that supports both **Min-Heap** and **Max-Heap** behaviour within the same data structure.  
Unlike traditional heaps, this implementation allows the heap to **toggle its state at runtime** while preserving heap invariants.

Each heap entry consists of a **(key, value)** pair, where:
- **key** represents a numeric priority
- **value** is a `String` associated with that key

---

##  Features

- Supports **Min-Heap** and **Max-Heap** modes
- Runtime **heap state toggling** (Min ↔ Max)
- Insertion and removal of arbitrary entries
- Efficient rebalancing using heapify operations
- Heap merging functionality
- Interactive **menu-driven console application**

---

## Core Operations

- `insert(key, value)` — insert a new entry into the heap  
- `top()` — display the root element  
- `removeTop()` — remove the root of the heap  
- `remove(entry)` — remove a specific entry  
- `toggle()` — switch between Min-Heap and Max-Heap  
- `replace(key/value)` — update an entry and rebalance  
- `merge()` — merge two heaps  
- `size()` / `isEmpty()` — heap status checks  
- `showHeap()` — display the current heap structure  

---

## Technical Highlights

- Maintains a **complete binary tree** structure
- Implements both **heapify-up** and **heapify-down** operations
- Preserves runtime efficiency during heap state transitions
- Demonstrates strong understanding of:
  - Data Structures (Heaps / Priority Queues)
  - Algorithmic invariants
  - Java object-oriented design

---

## How to Run

1. Clone the repository  
2. Compile the Java source files  
3. Run the main program  
4. Use the interactive menu to create, modify, and merge heaps  

---

## Interactive Menu Options

- Insert keys and values into the heap  
- Change heap state (Min-Heap ↔ Max-Heap)  
- Remove the top entry  
- Display the top entry  
- Remove specific entries  
- Replace an entry’s key or value  
- Check heap size and empty state  
- Display the current heap  

---

## Skills Demonstrated

- Java  
- Data Structures & Algorithms  
- Priority Queues / Heaps  
- Object-Oriented Programming  
- Console-based User Interfaces  

---

## Notes

This project was designed to showcase both **algorithmic depth** and **practical usability**, combining classic data structure principles with dynamic runtime behaviour.

