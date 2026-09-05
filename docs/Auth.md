# Auth for Member & Staff

## Login By Email & Password
Endpoint : POST /api/v1/auth/login-emailpassword

Request Body : 
```json
{
    "email" : "jana@gmail.com",
    "password" : "rahasia123"
}
```

Response Body (Success): 
```json
{
    "code" : 200,
    "data" : {
        "token" : "hwyd98w",
        "tokenExpired" : "121557157572187" // milisecond
    }
}
```
Response Body (failed): 
```json
{
    "code" : 401,
    "error" : "Unauthorized"
}
```
## Login By Id
Endpoint : POST /api/v1/auth/login-id

Request Body : 
```json
{
    "id" : "ys98shiau98"
}
```
Response Body (Success): 
```json
{
    "code" : 200,
    "data" : {
        "token" : "hwyd98w",
        "tokenExpired" : "121557157572187" // milisecond
    }
}
```
Response Body (failed): 
```json
{
    "code" : 401,
    "error" : "Unauthorized"
}
```

## Logout
Endpoint : DELETE /api/v1/auth/logout

Request Header : 
- X-API-TOKEN : Token (Mandatory)

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
    "code" : 401,
    "data" : "Unauthorized"
}
```