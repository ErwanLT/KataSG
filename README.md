# KataSG
[![Build Status](https://travis-ci.org/ErwanLT/KataSG.svg?branch=v2)](https://travis-ci.org/ErwanLT/KataSG)
[![codecov](https://codecov.io/gh/ErwanLT/KataSG/branch/v2/graph/badge.svg?token=UWGTO8C0S9)](undefined)

- h2-console : http://localhost:8080/h2-console

## User Story
### Create account

- methode : POST
- URL : /bank/createAccount?name&amount
- exemple : /bank/createAccount?name=Erwan&amount=100
- response :
```json
{
    "id": "c0a80116-7501-1e61-8175-01eeea630000",
    "createdOn": "2020-10-07T07:20:36.180Z",
    "name": "Erwan",
    "amount": 100,
    "operations": []
}
```

### Check opération
- methode : GET
- URL : /bank/operations/{idAccount}
- example : /bank/operations/c0a80116-7501-1e61-8175-01eeea630000
- response : 
```json
[
    {
        "id": "c0a80116-7501-1e61-8175-01efdf3f0001",
        "createdOn": "2020-10-07T07:21:11.865Z",
        "typeOperation": "WHITHDRAWAL",
        "amount": 50
    },
    {
        "id": "c0a80116-7501-1e61-8175-01f0d4fa0002",
        "createdOn": "2020-10-07T07:22:00.695Z",
        "typeOperation": "WHITHDRAWAL",
        "amount": 50
    },
    {
        "id": "c0a80116-7501-1e61-8175-01f131ae0003",
        "createdOn": "2020-10-07T07:22:59.276Z",
        "typeOperation": "DEPOSIT",
        "amount": 50
    }
]
```

### Making operation
- methode : GET
- URL : /bank/make/{typeOperation}/account/{idAccount}?amount
- example : /bank/make/DEPOSIT/account/c0a80116-7501-1e61-8175-01eeea630000?amount=50
- possible type operation :
  * DEPOSIT
  * WHITHDRAWAL
- response :
  * 200 OK
  * 500 An Error occured
