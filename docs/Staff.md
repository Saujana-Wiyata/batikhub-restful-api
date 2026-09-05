# Staff API Spec

## Insert
Endpoint: POST /api/v1/staff

Request Body :
```Json
{
    "name" : "Saujana Wiyata",
    "email" : "jana@gmail.com", 
    "password" : "rahasia123",
    "role" : "CEO"
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
Endpoint : PATCH /api/v1/staff/current

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Request Body :
```Json
{
    // fill the part you want to update only
    "name" : "",
    "email" : "",
    "password" : "",
    "role" : "CEO" // <- want to update
}
```

Response Body (Success) :
```Json
{
    "code" : 200,
    "data" : {
        "name" : "Saujana Wiyata",
        "email" : "janahebat@gmail.com",
        "role" : "Staff" // <- updated
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
Endpoint: DELETE /api/v1/staff/current

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

## Find All Staff
Endpoint: GET /api/v1/staff/all

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```Json
{
    "code" : 200,
    "data" : [
        {
            "name" : "Saujana Wiyata",
            "email" : "jana@gmail.com",
            "role" : "CEO"
        },
        {
            "name" : "Khairy Aimar",
            "email" : "ayie@gmail.com",
            "role" : "HR"
        }
    ],

    "paging": {
        "currentPage": 0,
        "totalPage": 3,
        "size": 5,
        "totalElements": 15
    }
}
```

Response Body (Failed) :
```Json
{
    "code" : 500,
    "error" : "Internal server error"
}
```

## Find Specific Staff
Endpoint: GET /api/v1/staff/current

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```Json
{
    "code" : 200,
    "data" : {
        "name" : "Saujana Wiyata",
        "email" : "jana@gmail.com",
        "role" : "CEO"
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
