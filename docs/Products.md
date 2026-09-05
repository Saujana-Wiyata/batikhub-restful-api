# Products API Specs
## Insert
Enpoint : POST /api/v1/products

Request Header:
- X-API-TOKEN : Token (Mandatory)

Request Body (multipart/form-data) :
- id : "BTK-631-KL"
- nama : "Kain Motif Parang"
- stock : 10
- harga : 15.76
- productCategory : "KAIN"
- gambar : [file binary png/jpeg]

Response Body (Success) : 
```json
{
    "code" : 200,
    "data" : "OK"
}
```

Response Body (Failed) : 
```json
{
    "code" : 400,
    "data" : "Fail to insert the product"
}
```

## Update
Enpoint : PATCH /api/v1/products/{id}

Request Header:
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
    "nama" : "", 
    "stock" : 15, // want to updated
    "harga" : 0.0,
    "productCategory" : "",
    "gambar" : ""/null // byte
}
```

Response Body (Success) : 
```json
{
    "code" : 200,
    "data" : {
        "idProduct" : "A002",
        "nama" : "Kain Mega Mendung", 
        "stock" : 21, // updated
        "harga" : 12.56,
        "productCategory" : "KAIN",
        "gambar" : "298109" // byte
    }
}
```

Response Body (Failed) : 
```json
{
    "code" : 400,
    "data" : "Fail to insert the product"
}
```
## Delete
Endpoint : DELETE /api/v1/products/{id}

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) : 
```json
{
    "code" : 200,
    "data" : "OK"
}
```

Response Body (Failed) : 
```json
{
    "code" : 400,
    "data" : "Fail to delete the product"
}
```

## Get All Products
Endpoint : GET /api/v1/products

Request Header : 
- X-API-TOKEN : Token (Mandatory)

```json
{
    "code" : 400,
    "data" : [{
        "idProduct" : "A002",
        "nama" : "Kain Mega Mendung", 
        "stock" : 21,
        "harga" : 12.56,
        "productCategory" : "KAIN",
        "gambar" : "298109" // byte
    },
    {
        "idProduct" : "A001",
        "nama" : "Baju Batik", 
        "stock" : 5, 
        "harga" : 10.00,
        "productCategory" : "BAJU",
        "gambar" : "298109" // byte
    }],

    "paging" : {
        "currentPage": 0,
        "totalPage": 5,
        "size": 6,
        "totalElements": 30
    }
}
```
## Search Product
Endpoint : GET /api/v1/products/search

Query Param : 
- productName : String

Request Header : 
- X-API-TOKEN : Token (Mandatory)

```json
{
    "code" : 400,
    "data" : {
        "idProduct" : "A001",
        "nama" : "Baju Batik", 
        "stock" : 5, 
        "harga" : 10.00,
        "productCategory" : "BAJU",
        "gambar" : "298109" // byte
    },

    "paging" : {
        "currentPage": 0,
        "totalPage": 5,
        "size": 6,
        "totalElements": 30
    }
}
```