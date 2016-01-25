# Get A Haircut! Salon Manager

#### A Database Management App for Hair Salons, January 24th, 2016

#### By Midori Bowen

## Description

Get A Haircut! Salon Manager is a Java application that allows a user to input stylists and clients into a salon database in order to manage the salon's lists of stylists and clients. Each client is assigned to one stylist, but a stylist can have many clients.

## Setup/Installation Requirements

* Install Java, PostgreSQL, and Gradle
* Clone this repository
* Navigate to your terminal and open Postgres by entering `postgres` in your command line
* Open another terminal window and enter the command `psql` and create a database by entering `CREATE DATABASE salon;` in your command line. Enter `\c salon` to open this database.
* Open yet another terminal window, navigate to your project directory and enter the command `psql salon < salon.sql`. This will create the tables you need to get this database running.
* In order to test, in the terminal window where you are tracking your database, enter `CREATE DATABASE salon_test WITH TEMPLATE salon;` before each time you test. You may need to periodically enter `DROP DATABASE salon_test;` in order to clear data from your test database. After you delete this test database, you can always create it again, using the same `CREATE DATABASE` command listed above.

* To run this program in your browser, navigate to your salon directory, enter `gradle run` in your command line, and go to http://localhost:4567/ in your browser.

## Known Bugs

Currently there is a bug that does not allow the user to create new clients under a stylist's page. Although this functionality is passing integration tests, there is a problem with being able to access the entire list of stylists, causing an error when the user tries to enter a new client. The error is a NumberFormatException: null.

## Support and contact details

https://github.com/midoribowen

## Technologies Used

Java, Spark, JUnit, Fluentlenium, Velocity, PostgreSQL, Bootstrap

### License

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Copyright (c) 2016 Midori BOwen
