# Member API Docs Spec

## Insert
Endpoint: POST /api/v1/member

Request Body :
```Json
{
    "name" : "Saujana Wiyata",
    "email" : "jana@gmail.com", 
    "password" : "rahasia123",
    "alamat" : {
        "jalan" : "jalan ABC",
        "kota" : "Jakarta Pusat", 
        "provinsi" : "DKI Jakarta"
    }
}
```

Response Body (success) :
```Json
{
    "code" : 200,
    "data" : "OK"
}
```

Response Body (Failed) :
```Json
{
    "code" : 400,
    "error" : "Your input data is incorrect"
}
```

## Update
Endpoint : PATCH /api/v1/member/current

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Request Body :
```Json
{
    // fill the part you want to update only
    "name" : "Saujana Wiyata",
    "email" : "jana@gmail.com", // <- want to update
    "password" : "rahasia123",
    "alamat" : {
        "jalan" : "jalan ABC",
        "kota" : "Jakarta Pusat", 
        "provinsi" : "DKI Jakarta"
    }
}
```

Response Body (Success) :
```Json
{
    "code" : 200,
    "data" : {
        "name" : "Saujana Wiyata",
        "email" : "janahebat@gmail.com", // <- updated
        "alamat" : {
            "jalan" : "jalan ABC",
            "kota" : "Jakarta Pusat", 
            "provinsi" : "DKI Jakarta"
        }
    }
}
```

Response Body (Failed) :
```Json
{
    "code" : 400,
    "error" : "Fail to update your data"
}
```

## Delete
Endpoint: DELETE /api/v1/member/current

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Response Body (Failed) :
```Json
{
    "code" : 200,
    "data" : "OK"
}
```

Response Body (Failed) :
```Json
{
    "code" : 400,
    "error" : "Fail to delete your account"
}
```

## Find Specific Member
Endpoint: GET /api/v1/member/current

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```Json
{
    "code" : 200,
    "data" : {
        "name" : "Saujana Wiyata",
        "email" : "jana@gmail.com",
        "alamat" : {
            "jalan" : "jalan ABC",
            "kota" : "Jakarta Pusat", 
            "provinsi" : "DKI Jakarta"
        }
    }
}
```

Response Body (Failed) :
```Json
{
    "code" : 404,
    "error" : "Fail to find your account"
}
```
