```
./bin/kafka-topics --bootstrap-server=localhost:9092 --list

./bin/kafka-console-producer --bootstrap-server localhost:9092 --topic foo

./bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic foo --from-beginning
```

```
valid
curl -d '{"poolId": "coro", "from":"foo", "to":"bar", "amount":10.10}' -H "Content-Type: application/json" -X POST localhost:8080/transfers
curl -d '{"poolId": "coro", "from":"baz", "to":"goo", "amount":100.10}' -H "Content-Type: application/json" -X POST localhost:8080/transfers

invalid
```