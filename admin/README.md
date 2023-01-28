# 1985-admin

```
docker-compose exec mysql bash -c 'mysql -u $MYSQL_USER -p$MYSQL_PASSWORD _1985'

```


```
curl -d '{"name": "foo", "description":"bar"}' -H "Content-Type: application/json" -X POST localhost:8080/users
curl -X POST localhost:8080/admins/1
curl -d '{"name": "coro1", "type":"sardex"}' -H "Content-Type: application/json" -X POST localhost:8080/pools/admins/1
curl -d '{"name": "coro2", "type":"sardex"}' -H "Content-Type: application/json" -X POST localhost:8080/pools/admins/1
curl -d '{"name": "coro3", "type":"sardex"}' -H "Content-Type: application/json" -X POST localhost:8080/pools/admins/1

curl -d '{"name": "aaaaa", "type":"sardex"}' -H "Content-Type: application/json" -X POST localhost:8080/pools/admins/1
curl -d '{"name": "abc", "type":"sardex"}' -H "Content-Type: application/json" -X POST localhost:8080/pools/admins/1



curl -d '{"name": "accc", "description":"bla"}' -H "Content-Type: application/json" -X POST localhost:8080/accounts/pools/1/users/1
curl -d '{"name": "accc", "description":"bla"}' -H "Content-Type: application/json" -X POST localhost:8080/accounts/pools/2/users/1
curl -d '{"name": "accc", "description":"bla"}' -H "Content-Type: application/json" -X POST localhost:8080/accounts/pools/3/users/1


curl -X DELETE localhost:8080/pools/1
```
