## Revolut interview task
RESTful API (including data model and the backing implementation) for money transfers between accounts.

## API documentation
All calls to API must be started with http://localhost:8080/

Application exposes following REST endpoints

| Http method | Endpoint                                        | Request Example                                   | Description                                                                  |
|-------------|-------------------------------------------------|---------------------------------------------------|----------------------------------------------------------------|
| GET         | /account                                        |                                                   | Retreves all clients in the system                            |
| GET         | /account/{accountId}                            |                                                   | Retreves client with the given ID                             |
| POST        | /account                                        | {   "name": "account name",   "ballance": "0" }   | Creates new client                               |
| POST        | /transactions                                   | {   "fromId": "1", "toId": "2", amount: "10"  }   | Transfers specified amount from client with id = "fromid" to client with id = "toId"  |


## Build and run

Run application 
```
gradlew runApplication
```