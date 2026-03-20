
# Use cases notes


## Use case 1

- Multiple request on parallel
- Wait for all to be completed
- Construct a result based on all of them completed

### Example where the tasks are different

try (var scope = StructuredTaskScope.open()){

Subtask<OperResultType1> asyncOperation1 =  scope.fork(() => service1.operation(inputParams));
Subtask<OperResultType2> asyncOperation2 =  scope.fork(() => service2.operation(inputParams));

scope.join();

return OperResultType3(asyncOperation1.get,asyncOperation3.get)

} catch (Interrupted Exception e){

	throw some exception
}

### Another example, all tasks are identical

try (var scope = StructuredTaskScope.open(Joiner.<>.allSuccessfulOrThrow)) {

collection.forEach(collectionItem => scope.fork(() => service.operation(collectionItem)));

va result = scope.join().map(Subtask::get).toList();

return result;

} catch (Interrupted Exception) {

	throw something here
}



## Use case 2

- Multiple requests in parallel
- Wait for the first to complete
- Return based on the first completed

try (var scope = StructuredTaskScope.open(Joiner.<CommonResultType>anySuccessfulResultOrThrow())){

	scope.fork(() => service1.operation1(input));
	scope.fork(() => service2.operation1(input));

	return scope.join();


} catch (Interrupted Exception e){


}

## Use case 3

- Multiple requests in parallel (let's say 2)
- For one of them, launch (two post requests in parallel)
- Wait for the subrequests to complete to join to the second requests

## Use case 4