# Omset API Specs

properties : 
- omset
- totalProductSold
- omsetPerproduct (id, name, totalItemsSoldout, omset)
- paging

## Take all information for dashboard
Endpoints : GET /api/v1/omset/dashboard

Request Header :
- X-API-TOKEN : Token (Mandatory)

```json
{
    "code" : 200,
    "data" : {
        "omset" : 2700,
        "totalProductSold" : 110,
        "omsetPerproduct" : [{
            "idProduct" : "A001",
            "productName" : "Baju Batik",
            "totalItemsSoldout" : 12,
            "omsetProduct" : 450
        },
        {
            "idProduct" : "A002",
            "productName" : "Kain Mega Mendung",
            "totalItemsSoldout" : 98,
            "omsetProduct" : 2250
        }]
    },

    "paging" : {
        "currentPage": 0,
        "totalPage": 3,
        "size": 5,
        "totalElements": 15
    }
}
```