# Auth for Member & Staff

## Login
Endpoint : POST /api/v1/auth/login

Request Body (login by email & password): 
```json
{
    "email" : "jana@gmail.com",
    "password" : "rahasia123"
}
```

Request Body (login by id): 
```json
{
    "id" : "ys98shiau98"
}
```
Response Body (Success): 
```json
{
    "code" : 200
    "data" : {
        "token" : "hwyd98w",
        "tokenExpired" : 121557157572187 // milisecond
    }
}
```
Response Body (failed): 
```json
{
    "code" : 405,
    "error" : "Unauthorized"
}
```
## Logout
Endpoint : POST /api/v1/auth/logout

Request Header : 
- X-API-TOKEN : Token (Mendatory)

Response Body (Success): 
```json
{
    "code" : 200,
    "data" : "OK"
}
```

Response Body (Failed): 
```json
{
    "code" : 405,
    "data" : "Unauthorized"
}
```