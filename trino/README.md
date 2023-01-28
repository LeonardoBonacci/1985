
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
...
...

SHOW CATALOGS;
SHOW SCHEMAS IN mysql;
SHOW TABLES;

CREATE TABLE IF NOT EXISTS bla (
  blakey bigint,
  blaname varchar
)
COMMENT 'A table to join.';

INSERT INTO bla VALUES (1, 'foo');

select mysql.heroes.bla.* from mysql.heroes.bla;
select kafka.trans.transfers.* from kafka.trans.transfers;

select bla.*, transfers.* from mysql.heroes.bla as bla join kafka.trans.transfers as transfers on transfers._from = bla.blaname;
```

```
trino> select bla.*, transfers.* from mysql.heroes.bla as bla join kafka.trans.transfers as transfers on transfers._from = bla.blaname;
 blakey | blaname | transferid  | poolid  | _from | to  | amount |    when
--------+---------+-------------+---------+-------+-----+--------+------------
      1 | foo     | i-am-random | bahamas | foo   | bar |   10.1 |       NULL
      1 | foo     | i-am-random | bahamas | foo   | bar |   10.1 | 1674179837
(2 rows)

Query 20230120_025955_00038_hdrcg, FINISHED, 1 node
Splits: 11 total, 11 done (100.00%)
0.20 [6 rows, 544B] [29 rows/s, 2.63KB/s]
```
