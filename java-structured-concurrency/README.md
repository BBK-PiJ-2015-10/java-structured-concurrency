
# Use cases notes


## Use case 1

- Multiple request on parallel
- Wait for all to be completed
- Construct a result based on all of them completed

try (var scope = StructuredTaskScope.open()){

Subtask<OperResultType1> asyncOperation1 =  scope.fork(() => service1.operation(inputParams));
Subtask<OperResultType2> asyncOperation2 =  scope.fork(() => service2.operation(inputParams));

scope.join();

return OperResultType3(asyncOperation1.get,asyncOperation3.get)

} catch (Interrupted Exception e){

	throw some exception
}



## Use case 2

- Multiple requests in parallel
- Wait for the first to complete
- Return based on the first completed

## Use case 3

- Multiple requests in parallel (let's say 2)
- For one of them, launch (two post requests in parallel)
- Wait for the subrequests to complete to join to the second requests

## Use case 4