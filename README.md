                    What is it?
You run a small theater and each month, you have patrons mail in requests for pre-sale tickets.
You need to process these ticket requests and either tell them where their party will sit or explain to the patron why you can't complete their order.
 
You have a few rules that you need to follow when you fill the orders
Fill as many orders as possible
Put parties as close to the front as possible.
If there are not enough seats available in the theater to handle a party, tell them "Sorry, we can't handle your party."
Each party must sit in a single row in a single section.  If they won't fit, tell them "Call to split party".

Sample input:
6 6
3 5 5 3
4 6 6 4
2 8 8 2
6 6 
 
Smith 2
Jones 5
Davis 6
Wilson 100
Johnson 3
Williams 4
Brown 8
Miller 12

Sample output:
 
Smith Row 1 Section 1
Jones Row 2 Section 2
Davis Row 1 Section 2
Wilson Sorry, we can't handle your party.
Johnson Row 2 Section 1
Williams Row 1 Section 1
Brown Row 4  Section 2
Miller Call to split party.


                    How to setup?
This Exercise is setup as a maven project.
Build the project using the mvn clean install command.
Require's Java 8 compiler & runtime.

                    How to execute?
Run the main method in com.theater.demo.DemoTheaterSeating.
It instantiates the ProcessPreSalesRequests object, which in turn will invoke other classes that are tasked to
process and finally print the seating arrangement.
The only input that is required is the name of the seating request file.

                    How does it work?
com.theater.process.ProcessPreSaleRequests : This class locates and loads the input file with seating request information.

com.theater.seating.ExtractSeatingAndCustomerOrder: Extracts Theater seating and Customer order information from the InputFileInfo modal object created by ProcessPreSalesRequests.

com.theater.seating.AssignCustomerSeating : Takes in the output generated from ExtractSeatingAndCustomerOrder and assigns seating to Customers based off the seating availablility.

com.theater.seating.PrintTheaterSeating: Prints out designated seating/comments for all Customers based on the outcome from AssignCustomerSeating class.

                    Where are JUnits?
com.theater.process.ProcessPreSaleRequestsTest
com.theater.seating.ExtractSeatingAndCustomerOrderTest
com.theater.seating.AssignCustomerSeatingTest
com.theater.seating.PrintTheaterSeatingTest



                    Where are resource files?
resources/theater_layout.txt
resources/test_resource.txt


                    Any Assumptions for the design?
1. The Theater seating is 5 rows and 5 sections each.
2. Always strive to fill in the first 3 rows.
