# dunnit-event-server
Event Server which takes events posted to /notification and publishes them to the "dunnit" topic in redis.  

Start a local instance of redis

Start the dunnit server in the repl using
lein repl

Then issue the go command to start processing events
user => (go)

Using POSTMAN or CURL use the following dummy event taken from the googles Push documentation https://developers.google.com/gmail/api/guides/push

{
    "message": {
        "data": "eyJlbWFpbEFkZHJlc3MiOiAidXNlckBleGFtcGxlLmNvbSIsICJoaXN0b3J5SWQiOiAiMTIzNDU2Nzg5MCJ9",
        "message_id": "1234567890"
    },
    "subscription": "projects/myproject/subscriptions/mysubscription"
}
