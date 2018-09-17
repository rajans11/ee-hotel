***Part 1***


**Session based testing session:**

- 30 minute time boxed
- Google Chrome
- Mac OSX
- Areas of testing exploration:
    - Security
    - Functional
    - Accessibility
    - Compliance
    - Usability
    - Localization

*Main findings*

* Not HTTPS, this is a must for any website which requires personal data or payment information.
* No privacy policy or cookie message, going against legal requirements of data privacy and GDPR (<https://eugdpr.org/>)
* Both front-end and back-end are not validating input
* There is a JS error in the console, this only is apparent if there are no bookings.
* Poor UX with regards to validation and understanding what errors are when filling out the form.
* Not made to be responsive, seems to only be viewable with a from a width of 992px upwards.
* If a real life system, there is no authentication with regards to logging in, users and audit trail.
* Accessibility issues, mainly around HTML structures which would be an issue for users with screen readers. Report can be found [here](Accessibility.png)

*Detailed findings*

No http which was an immediate worry and security red flag. No cookie/privacy policy which shows that data isn't managed in the correct way.

Clicking the "Save" button without entering any data and got an HTTP 500 response from the back-end. This is a defect as
the front-end and back-end should validate data. This can be reproduced simply with an HTTP client, for example curl:

```
HTTP/1.1 500 Internal Server Error
Content-Type: text/plain; charset=utf-8
Date: Sun, 16 Sep 2018 21:45:54 GMT
ETag: W/"15-3JQVFLwoG6yepWGqlDPA/A"
X-Powered-By: Express
Content-Length: 21
Connection: keep-alive
```
Looking at the JS console I see the following error message:

```
script.js:15 Uncaught TypeError: Cannot read property 'bookingid' of undefined
    at getBooking (script.js:15)
```

Reading the JS it seems it gets the booking IDs from the `/booking` endpoint and then calling each ID to receive the bookings.

Poor level of validation with the form:
- No date validation
- Currency restrictions
- Error messaging

Trying a SQL injection, but it looks like the application is correctly sanitising input.
My first name is:

``` SQL
' OR 1=1; --
```

Finally, as my exploratory testing timebox is finishing, I try to input letters into the price box. The server responds
with an HTTP 500 error. This is an issue as both the front-end and the back-end should validate the data. The price
textbox allows me to enter a floating point number as long as I use a dot. Using a comma returns a 500. I would love to
test this in other browsers given more time.

***Part 2***

In order to run the tests you will JDK8.

```
./gradlew clean test
```

Some simple extras features useful for a Selenium project are also implemented and ready to use: 
 - Cucumber BDD for readable and re-usable steps
 - Screenshots with Selenium when an error occurs;
 - Cucumber reports in build folder
 - Cucumber Java8 lamba examples
 - JenkinsFile to display how it can be integrated with a CI tool like Jenkins
 - Ability to run different drivers within the project
 
With time permitted a few changes could have been made, one is to change the need to instantiate on each step, the other 
change I would do is to implement the pending features.

