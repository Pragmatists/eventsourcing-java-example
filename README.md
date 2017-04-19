# Event sourcing example in Java
A simplified (in memory) example of Event Sourcing implementation in Java for banking domain.
Repository is splitted into exercises adding step by step more functionality towards good design of event sourcing with CQRS.
You can play around and try to implement exercises or You can check out solution branches.


## Step 1 - In memory iplementation of event sourcing
![alt tag](https://raw.githubusercontent.com/michal-lipski/eventsourcing-example/master/event_store_exercise_1.png)
- Provide simple in-memory implementation of Event Store
- Make all test passing using event sourcing
#### soultion
 - branch [exercise_1_solution](https://github.com/michal-lipski/eventsourcing-example/tree/excercise_1_solution)

## Step 1a (optional) - Unit of work pattern
- Implement [Unit of Work](https://martinfowler.com/eaaCatalog/unitOfWork.html) pattern where events are stored outside of aggregate
#### soultion
 - WIP

## Step 1b (optional) - Projections
- Implement Projections on Account to get number of transactions performed on account
- eventStore.store() method shoud accept Event playload instead of domain Events
- what should be api of eventStream()?
#### soultion
 - WIP
 
## Step 2 (optional) - Optimistic locking
- add optimistic locking
#### soultion
 - WIP
 
## Step 3 - new Aggregate extraction
![alt tag](https://raw.githubusercontent.com/michal-lipski/eventsourcing-example/master/event_store_exercise_2.png)
- Refactor to move all money transfer related stuff to separate aggregate
- New aggregate will be also using Event Store
#### soultion
 - WIP
 
## Step 4 - adding CQRS
![alt tag](https://raw.githubusercontent.com/michal-lipski/eventsourcing-example/master/event_store_exercise_3.png)
- Apply CQRS rule and separate the command and reading side
- Solution will use Eventual Consistency approach
#### soultion
 - WIP
 
## Step 5
- Provide additional (not transient) implementation of Event Store. (https://geteventstore.com/)
#### soultion
 - WIP
