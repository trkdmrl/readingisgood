
## Reading is good

ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. 


## Technologies

Java 11

Spring Boot

H2

Maven

Docker

Junit

  
## API Usage

#### Firstly, You need a token. You can get token like below.

```http
  POST /authenticate
```
Request Body:
```
{
    "username": "trkdmrl",
    "password": "trkdmrl"
}
```

And result like :

```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cmtkbXJsIiwiaWF0IjoxNjY3MjQ5MTM4LCJleHAiOjE2NjcyNjcxMzh9.AwAlhKwk2_bM_D5epl_GcgZUbMd9YStMvhtoTo51psw50G2ffAjqfuNZu5AbAr-qd_yCkWXjkEi1o3SXvJojQg"
}
```
You must add above token to all requests.


Save Book API:
```http
  POST /book/add
```
Request Body:
```
{
	"stockNumber": "15",
	"price": "75.50"
}
```

Save Customer API:
```http
  POST /customer/add
```
Request Body:
```
{
	"username": "trkdmrl",
	"password": "12341",
	"email": "trkdmrl10@gmail.com"
}
```

Save Order API:
```http
  POST /order/add
```
Request Body:
```
{
   "customerId": "4",
	  "books": [
			{
				"bookId": "1",
				"quantity": "1"
			},
			{
				"bookId": "3",
				"quantity": "1"
			}
		]
}
```

Get Order By Id API:
```http
  GET /order/getbyid?id=6
```

List By Date Interval API:
```http
  GET /order/listbydateinterval?startDate=2022-10-31T10:03:17.272489&endDate=2022-11-01T00:03:17.272489
```

Get Customer's Monthly Statistic API:
```http
  GET /statistic/getcustomersmonthlystatistic
```
Request Body:
```
{
	"month": "October",
	"customerId": "5"
}
```

Update Book Stock API:
```http
  PUT /book/updatestock?number=5&id=1
```

  