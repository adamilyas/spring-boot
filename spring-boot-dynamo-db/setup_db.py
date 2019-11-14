import boto3
import json

# $ pip install boto3
# Setup local dynamo db: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html
# $ java -Djava.library.path=~/dynamodb_local_latest/DynamoDBLocal_lib -jar ~/dynamodb_local_latest/DynamoDBLocal.jar -sharedDb -port 8000 -inMemory

TABLE_NAME = "task_info_table"
REGION = 'ap-southeast-2'
URL = "http://localhost:8000"

def createTable(tableName, hashKey, sortKey):
    dynamodb = boto3.resource(
        'dynamodb', 
        region_name=REGION, 
        endpoint_url=URL )

    table = dynamodb.create_table(
        TableName=tableName,
        KeySchema= [       
            { 'AttributeName': hashKey, 'KeyType': "HASH"},    # Partition key
            { 'AttributeName': sortKey, 'KeyType': "RANGE" }   # Sort key
        ],
        AttributeDefinitions=[
            {
                'AttributeName': hashKey,
                'AttributeType': 'S'
            },
            {
                'AttributeName': sortKey,
                'AttributeType': 'S'
            },

        ],
        ProvisionedThroughput={
            'ReadCapacityUnits': 10,
            'WriteCapacityUnits': 10
        }
    )
    return table

if __name__ == "__main__":
    table = createTable("message_info_table", "recipient", "dateSent")