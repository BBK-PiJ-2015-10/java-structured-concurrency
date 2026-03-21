
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


} catch (InterruptedException e){


}

## Use case 3

- Multiple requests in parallel (let's say 2)
- For one of them, launch (two post requests in parallel)
- Wait for the subrequests to complete to join to the second requests


try (var scope= StructuredTaskScope.open()){

    SubTask<CombinedResult1> combinedResult1SubTask = scope.fork(() => operationS1(input));
    SubTask<Result2> result2SubTask = scope.ford(() => operation2(input));

    scope.join();

    CombinedResult1 combinedResult1 = combinedResult1SubTask.get();
    Result2 result2 = result2Subtask.get();

} catch(InterruptedException e) {

}


private Result1 operation1(Input input) {

}

private Result1A operation1A(Result1 result1) {

}

private ResultAB operation1A(Result1 result1) {

}

private Result2 operation2(Input input) {

}

private CombinedResult1 operations1(Input input) {

    var operation1Result = operation1(input);

    try (var scope = StructuredScope.open()){

        Subtask<Result1A> result1A = scope.fork(() => operation1A(operation1Result) );
        Subtask<Result1B> result1B = scope.forkd(() => operation1B(operation1Result));

        scope.join();

        Result1A = result1A.get();
        Result1B = result1B.get()

        CombinedResult1(Result1A,Result1B);

    }

    var operation1A = 



}



## Use case 4