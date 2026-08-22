# Transaction API Spec

## Insert
Endpoint: POST /api/v1/transaction

Request Header:
- X-API-TOKEN : Token (Mandatory)

Request Body :
```Json
{
    "total_pembelian" : 1,
    "purchase_date" : "11-01-2010",
    "arrival_date" : "17-01-2010",
    "id_pembeli" : "uoqwdu",
    "id_produk" : "A01"
}
```

Response Body (success) :
```Json
{
    "code" : 200,
    "data" : {
        "id" : "01",
        "total_pembelian" : 1,
        "purchase_date" : "11-01-2010",
        "arrival_date" : "17-01-2010",
        "id_pembeli" : "uoqwdu",
        "id_produk" : "A01"
    }
}
```

Response Body (Failed) :
```Json
{
    "code" : 400,
    "error" : "Fail to make transaction"
}
```

## Find All
Enpoint : GET /api/v1/transaction/all

Request Header:
- X-API-TOKEN : Token (Mandatory)

Response Body (success) :
```Json
{
    "code" : 200,
    "data" : [{
        "id" : "01",
        "total_pembelian" : 1,
        "purchase_date" : "11-01-2010",
        "arrival_date" : "17-01-2010",
        "id_pembeli" : "uoqwdu",
        "id_produk" : "A01"
    },
    {
        "id" : "02",
        "total_pembelian" : 3,
        "purchase_date" : "12-01-2010",
        "arrival_date" : "18-01-2010",
        "id_pembeli" : "iiowj",
        "id_produk" : "A31"
    }],

    "paging" : {
        "currentPage": 0,
        "totalPage": 5,
        "size": 5,
        "totalElements": 25
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

## Delete 
Enpoint : DELETE /api/v1/transaction/{id}

Request Header:
- X-API-TOKEN : Token (Mandatory)

Response Body (SUccess) :
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
    "error" : "Fail to delete transaction"
}
```