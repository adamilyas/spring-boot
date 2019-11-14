# Spring Boot with Dynamo DB

## Setup
Important! Checkout compatibility over here: [https://derjust.github.io/spring-data-dynamodb/](https://derjust.github.io/spring-data-dynamodb/)
local dynamo db: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html
```
$ java -Djava.library.path=~/dynamodb_local_latest/DynamoDBLocal_lib -jar ~/dynamodb_local_latest/DynamoDBLocal.jar -sharedDb -port 8000 -inMemory
$ pip install boto3
$ python setup_db.py
```

## Running
For local testing, make sure that Local DB is running using ^ steps in Setup
```
$ cd to-this-folder
$ mvn clean install
$ mvn spring-boot:run
```

## Project Logic
Controller -> Repository -> query from Local DynamoDB

## Credits
[https://github.com/SimpleProgramming/spring-boot-aws-dynamoDB](https://github.com/SimpleProgramming/spring-boot-aws-dynamoDB)
