This is a repo with java 25 structured concurrency examples


# Use cases being modeled

Use case 1

- Multiple request on parallel
- Wait for all to be completed
- Construct a result based on all of them completed


Use case 2

- Multiple requests in parallel
- Wait for the first to complete
- Return based on the first completed

Use case 3

- Multiple requests in parallel (let's say 2)
- For one of them, launch (two post requests in parallel)
- Wait for the subrequests to complete to join to the second requests
