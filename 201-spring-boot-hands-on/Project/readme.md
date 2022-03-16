# **Shopping Cart Application**

Lets you to create user, product and manage cart by adding, updating and removing products from the cart

The application is exposed via port **8082**

The swagger API is exposed at http://localhost:8082/swagger-ui.html

### _**Initial Data**_

##### User

````json
{
    "id": 1,
    "name": "Test User",
    "emailId": "testuser@gmail.com",
    "mobileNumber": "9977884411"
}
````

##### Products

#### Book

````json
{
    "productId": 1,
    "productName": "The Clean Coder",
    "price": 450,
    "genre": "Programming",
    "author": "Robert C Martin",
    "publications": "Pearson"
}
````

#### Apparel

````json
{
     "productId": 2,
     "productName": "Green Chino",
     "price": 650,
     "type": "Chino",
     "brand": "Louis Vuitton",
     "design": "Strips"
}
````

##### Cart

````json
{
  "products": [
    {
      "productId": 1,
      "productCategory": "BOOK",
      "productName": "The Clean Coder",
      "productPrice": 450,
      "quantity": 10
    },
    {
      "productId": 2,
      "productCategory": "APPAREL",
      "productName": "Green Chino",
      "productPrice": 650,
      "quantity": 1
    }
  ],
  "totalPrice": 5150,
  "user": {
    "id": 1,
    "name": "Test User",
    "emailId": "testuser@gmail.com",
    "mobileNumber": "9977884411"
  }
}
````

The operations for user, product and cart are documented in the Swagger API. The same can be used for validating the project.

