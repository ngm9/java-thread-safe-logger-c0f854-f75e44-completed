### Task Overview
In this project, you'll work on a logging utility used by a company's multi-threaded application. Currently, log messages written by multiple threads are getting mixed up, making logs hard to read, and rare deadlocks are freezing the application when threads compete for resources. Clear logs are essential for debugging production issues and ensuring reliability. Your task is to refactor and demonstrate a thread-safe, deadlock-free logger that includes meaningful information in each log entry.

### Objectives
- Refactor the logger so each complete message is written to the file atomically and without interleaving.
- Ensure multiple threads writing logs simultaneously do not cause deadlocks when acquiring resources.
- Include the current timestamp and thread name in each log line for traceability.
- Simulate concurrent logging from 3-4 threads to validate correctness and safety of the logger.
- Demonstrate in your provided test that log file entries are readable, ordered, and no deadlocks occur.

### How to Verify
- Run the application to produce a log file with many entries from multiple threads, each.
- Review the generated log to ensure that each message appears as an unbroken line and no entries are interleaved character-wise.
- Confirm there are no application hangs or freezes due to locking or deadlock.
- Check that log entries clearly identify which thread wrote each message and when.
- If possible, simulate fast and slow threads to ensure logging remains consistent.

### Helpful Tips
- Consider how to synchronize code blocks in Java for data consistency.
- Think about how thread interleaving can corrupt shared resources and how atomic operations help.
- Explore basic ways to prevent deadlocks.
- Review how to use Thread, Runnable, and ExecutorService for running and managing concurrent tasks.
- Look into formatting log entries with current timestamps and thread names.
