## 

### Running it from curl

```bash
export API_PASSWORD="" # Get the password from the log
curl -XPOST --data '{"name": "buy milk"}' \
-H "Content-Type: application/json" \
"http://user:$API_PASSWORD@localhost:8080/api/v1/cards"
```

