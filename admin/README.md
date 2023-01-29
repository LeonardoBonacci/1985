# 1985-admin

```
docker-compose exec mysql bash -c 'mysql -u $MYSQL_USER -p$MYSQL_PASSWORD _1985'
```

```
curl -d '{"name": "foo", "description":"bar"}' -H "Content-Type: application/json" -X POST localhost:8083/users
curl -X POST localhost:8083/admins/1
curl -d '{"name": "coro", "type":"sardex"}' -H "Content-Type: application/json" -X POST localhost:8083/pools/admins/1

curl -d '{"name": "accc", "description":"bla"}' -H "Content-Type: application/json" -X POST localhost:8083/accounts/pools/1/users/1

curl -X DELETE localhost:8083/pools/1
```
