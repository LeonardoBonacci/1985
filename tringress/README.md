```
./bin/kafka-topics --bootstrap-server=localhost:9092 --list
./bin/kafka-topics --bootstrap-server=localhost:9092 --delete --topic __transaction_state

./bin/kafka-console-producer --bootstrap-server localhost:9092 --topic transfers_for_coro

./bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic transfers_for_coro --from-beginning
```

```
valid
curl -d '{"poolId": "coro", "from":"foo", "to":"bar", "amount":10.10}' -H "Content-Type: application/json" -X POST localhost:8082/transfers
curl -d '{"poolId": "coro", "from":"baz", "to":"goo", "amount":100.10}' -H "Content-Type: application/json" -X POST localhost:8082/transfers

invalid
```