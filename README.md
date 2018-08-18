# Checkout, a simple Shopping Cart exercise

This is a little exercise to help us understand a little more about your skills and how you work. 

* Clone the repository, and create a branch with your work, and make a pull request once done.

* Inspect the codebase and get familiar with the unit tests as they explain what the product does.

* This minimalist shopping cart order service that only accepts two functions
  * Posting a new order, which immediately completes the order
  * And getting an order, which allows you to see the status of orders.

* Some automated test cases have already been stubbed out in the /test/java directory. Add more where needed.

## What we're looking for
* Clean code
* Unit tests
* Updated documentation

## Assignments

                            Order
                            +-----------------------+
        GET                 |                       |
     +---------------------->                       |
                            |    Delivery Address   |
                            |                       |
                            |                       |
                            |    Payment Method     |
        POST                |                       |
     +---------------------->                       |
                            |    Shopping Cart      |
                            |                       |
                            |                       |
                            +-----------------------+

Assignment 1: Complete the purchase (GET and POST) functionality on PurchaseWebAPI.java
You'll have to replace the dummy implementation PurchaseWebAPI and OrderService.
What are the pros and cons of your approach? Do you have design suggestions? 

You can put your notes right here in the README.md file.

- An order should consist of a delivery address (just a street name is sufficient), 
a payment method (a single text field to capture how payment will take place is also sufficient),
a shopping cart (which includes a single item and with a name, quantity, and unit price),
and an order total price.

Assignment 2: Add idempotency support to the purchase functionality. 
As a business rule, if someone orders the same items within 3 seconds, we consider it a
duplicate order and should not be added as a new order, but rather return the current one.
Again, what are the pros and cons of your approach? Do you have design suggestions?

You can put your notes right here in the README.md file.

## Please note

- The following shell commands may be a good start if you use a Mac

  - mvn clean spring-boot:run

  - curl -X GET -H "content-type: application/json" -H "accept: application/json" http://localhost:8080/

  - curl -X POST -d '{"deliveryAddress":{"street":"main"},"paymentMethod":{"method":"visa"},"shoppingCart":{"item":"coffee"}}' -H "content-type: application/json" -H "accept: application/json" http://localhost:8080/

- The existing classes already in place serve as a foundation for this exercise. You can use them
to get going.

- Everything runs in a h2 in-memory database so everytime you start the product, you have a fresh db.


##Brandons Notes
To handle a duplicate order I have changed hibernate to auto update depending on the class structure. With this I was able to create a timestamp
column that throws a stamp when the order is first generated. I can then pull the timestamp from the most recent order by Max ID (using @Query annotation). (the most recent order should be the one with the highest id in the db.) I then can 
compare the timestamps of the order I'm testing and the order thats already in the db to see if they are within 3 seconds.
### Pros and Cons to duplicate order approach
- Con - would be that if the most recent order did not have the highest ID number. (Shouldnt happen tho)
- Pro - this is a very fast approach to solve the issue since I'm doing a custom hibernate query
- Pro -  the code is very easy to understand and it is very readable for maintence

- i initially recieved a list of all orders then checked the timestamp based on this. If orders
became a huge size then this operation would be a horrible way to do things so instead I let a query take care of it