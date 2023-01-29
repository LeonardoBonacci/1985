
Blocker: https://github.com/trinodb/trino/issues/15891

```
./bin/kafka-console-producer --bootstrap-server localhost:9092 --topic trans.coro --property "parse.key=true" --property "key.separator=:"
bahamas: {"transferId": "i-am-random", "poolId": "bahamas", "from":"foo", "to":"bar", "amount":10.10, "when":1674179837 }
bahamas: {"transferId": "i-am-random", "poolId": "bahamas", "from":"bar", "to":"goo", "amount":5.10 , "when":1674189838 }

./bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic trans.coro --property print.key=true --property key.separator=" >" --from-beginning
```

```
USE kafka.trans;

WITH
  debit AS (SELECT sum(amount) AS SMILE FROM coro where to = 'bar'),
  credit AS (SELECT sum(amount) AS CRY FROM coro where _from = 'bar')
SELECT coalesce(SMILE, 0) - coalesce(CRY, 0) AS balance FROM debit, credit;
```


```
SHOW CATALOGS;
SHOW SCHEMAS IN mysql;
USE mysql._1985;
SHOW TABLES;
```

To supply this info
```
  private PoolType poolType; 
  SELECT type FROM mysql._1985.pool WHERE name = 'coro';

  private Boolean fromIsValid; // means is part of poolId
  private Boolean toIsValid; // means is part of pool
WITH
  inpool AS (SELECT users.name FROM mysql._1985.user_info AS users 
             JOIN mysql._1985.account AS accounts ON accounts.user_id = users.id
             JOIN mysql._1985.pool AS pools ON pools.id = accounts.pool_id
             WHERE users.name = 'foo' OR users.name = 'bar')
SELECT name FROM inpool;

  private BigDecimal fromsBalance;
WITH
  debit AS (SELECT sum(amount) AS SMILE FROM kafka.trans.coro where to = 'foo'),
  credit AS (SELECT sum(amount) AS CRY FROM kafka.trans.coro where _from = 'foo')
SELECT coalesce(SMILE, 0) - coalesce(CRY, 0) AS balance FROM debit, credit;
```
