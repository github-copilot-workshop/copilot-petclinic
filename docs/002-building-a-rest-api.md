# Building a REST API

The `src/main/java/org/springframework/samples/petclinic/api/ApiController.java` file contains the basis for a REST API. It is a Spring `@RestController` that exposes a few endpoints that return JSON data.

The file has 2 endpoints:

- `/api/owners` - returns a list of owners
- `/api/pets/{name}` - returns a list of pets with a given name

You can run the application and test the endpoints using `curl`:

```bash
$ curl http://localhost:8080/api/owners

$ curl http://localhost:8080/api/pets/Leo
```

> Note : You must have the application running in order to test the endpoints: `mvn spring-boot:run`

## 001 - Adding a new endpoint

Create a new enpoint that returns a list of pets for a given owner. The endpoint should be `/api/owners/{ownerId}/pets`.

Once you have implemented the endpoint, you can test it using `curl`:

```bash
$ curl http://localhost:8080/api/owners/2/pets
```
should return
```json
[
  {
    "id": 2,
    "name": "Basil",
    "birthDate": "2012-08-06",
    "type": null,
    "visits": [],
    "new": false
  }
]
```


<details>

<summary>Possible Flow</summary>

1. Add a comment with `Create a new enpoint that returns a list of pets for a given owner. The endpoint should be /api/owners/{ownerId}/pets.`
2. Let Copilot drive your through the implementation
3. If you have compilation errors, for example related to the `Date`, you can right click on the error in the terminal and  and ask Copilot to explain.
4. You can also select the line where the error is and ask Copilot `how to change the type of the date in the selected code`

</details>

## 002 - Improving the code

### Documenting the code

The code does not have any documentation. You can ask Copilot to generate the documentation for you.

<details>

<summary>Possible Flow</summary>

1. Select the method code
2. Right Click and go to `Copilot` -> `Generate Doc`

</details>

### Making the code more robust

So you have created a new method, but let's look carefully at the code. You will notice that the method does not handle the case where the owner does not exist. You can ask Copilot to generate the code for you.

You also notice that the code may contains some vulnerabilities, a risk of SQL Injection.

Using Copilot Chat & Inline Chat ask it to :
- make the code more robust with a try/catch block
- ask if the code is safe if not ask it to make it safe

<details>

<summary>Possible Flow</summary>

</details>
1. In Copilot Inline Chat you can for example ask `make this code more robust with a try catch bloc and deal with empty owner id`
2. Select the function, and ask Copilot to `is this code safe?`
3. See the response and apply the fix

</details>

## 003 - Testing the API

The class `APIController` is currently not tested. Go to the `src/test/java/org/springframework/samples/petclinic/api/` folder and create a new test class `ApiControllerTest.java`.

Use Copilot to generate the test class and the first test method.

<details>

<summary>Possible Flow</summary>

</details>
1. Go in the `ApiControllerTest.java`
2. Using the inline chat (ctrl + i) ask Copilot to `create a test for the api/owners endpoint same as the PetClinitIntegrationTest`
3. Adapt the code if needed using Copilot (or not...)
4. Run the test using `mvn test` or using the IDE (`mvn clean spring-javaformat:apply  test -Dtest="ApiControllerTest"`)

</details>

## 004 - Understanding bug

The APIController class contains an existing endpoint `/pets/{name}` mapped by the method `getPetsByName`. This method is supposed to return a list of pets with a given name. 

The method is very basic. It does the job but you should improve it by:
- checking if the code is safe (risk of sql injection)
- fix the issue 
- add try catch block to make it robust
- add javadoc
- add a test where you explicitly test the sql injection fix


<details>

<summary>Possible Flow</summary>

1. Select the method and ask Copilot Chat to `explain this`
1. The explanation explains that code but also mentions that the code is not safe
1. Look at the next question from Copilot chat
1. Ask Copilot to fix this with a `place holder` variable
1. Add a try catch block using Inline Chat
1. Add a javadoc using Inline Chat
1. When done, create a test for example asking `@workspace Add new test to check that the code is safe from sql injection using a drop table statement`

</details>