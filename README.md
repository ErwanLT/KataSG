# KataSG
[![codecov](https://codecov.io/gh/ErwanLT/KataSG/branch/master/graph/badge.svg?token=UWGTO8C0S9)](undefined)

- h2-console : http://localhost:8080/h2-console
- swagger-ui : http://localhost:8080/swagger-ui.html

## User Story
### Create account

- methode : POST
- URL : /bank
- body :
```json
{  
  "name": "some name",
  "amount": 1000
} 
```
- response :
```json
{
    "id": 1,
    "name": "some name",
    "operationsDTO": [],
    "amount": 1000
}
```

### Withdrawal
- methode : GET
- URL : /bank/withdraw
- params : idAccount / amount
- example : /bank/withdraw?idAccount=1&amount=150
- response :
```json
{
    "id": 1,
    "name": "some name",
    "operationsDTO": [
        {
            "idOperation": 2,
            "dateOperation": "2020-10-01T08:28:25.613+0000",
            "typeOperation": "WHITHDRAWAL",
            "amount": 150
        }
    ],
    "amount": 850
}
```

### Deposit
- methode : GET
- URL : /bank/deposit
- params : idAccount / amount
- example : /bank/deposit?idAccount=1&amount=150
- response :
```json
{
    "id": 1,
    "name": "some name",
    "operationsDTO": [
        {
            "idOperation": 2,
            "dateOperation": "2020-10-01T08:28:25.613+0000",
            "typeOperation": "DEPOSIT",
            "amount": 150
        }
    ],
    "amount": 1150
}
```

### Check opération
- methode : GET
- URL : /bank/operations
- params : idAccount
- example : /bank/operations?idAccount=1
- response :
```json
[
    {
        "idOperation": 2,
        "dateOperation": "2020-10-01T08:27:54.607+0000",
        "typeOperation": "DEPOSIT",
        "amount": 200
    },
    {
        "idOperation": 3,
        "dateOperation": "2020-10-01T08:27:57.273+0000",
        "typeOperation": "DEPOSIT",
        "amount": 200
    }
]
```
