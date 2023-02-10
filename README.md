# features-management

## Run the project

- clone the project
- build gradle project (the .jar is needed for the docker image)
- navigate to /docker directory
- execute the ./start.sh bash file

The start.sh file will execute the docker-compose command which will create 3 containers:
1. The database - a MongoDB instance
2. The backend service - a SpringBoot application
3. The frontend service - an Angular application

The docker-compose will expose the containers ports in order to access them from our local machine.
Once the process is ready the application will be available at http://localhost:4200.

The backend is available at http://localhost:8080. There is also a src/test/java/com/swisscom/featuresmanagement/TestRequests.http
file for helping with the requests execution directly to the backend (requires Intelij IDEA Ultimate).

The database is available at mongodb://localhost:27017/feature-management-db, the username and password are stored in the application.yml.


## Stop the project
- execute the ./stop.sh bash file

## Assigment understanding
The service is an independent one, used only to store the new features, thus the persistence layer is not so complex. The
application must provide basic functionalities like get, add, modify or delete. Each feature will be available to a specific group of customers,
so a feature must contain the customer ids. Of course, there are many possibilities to implement the same thing, but this
application can be used as a skeleton for future improvements.


## Functionalities
- Add a new feature
- Get a list of features filtered by enabled/disabled (active/archived)
- Edit an existing feature
- Get the existing features based on filters such as "technicalName" or "customerId"
- Automatic disable the expired features

## Possible improvements
- Add a start date for a feature to be able to activate it automatically
- Add a date picker for adding or editing the expiring date
- Add pagination for the features page to not load all the features in memory
- Do the enabled/disabled filter logic in the backend and lazy load the disabled features
- Add more filter options for the features and create a web-ui filter
- Add error messages in the frontend to help user understand if the added values are incorrect
- Spring Security with role based authentication. A user can see only a specific range of features, depending on its team.
- Of course, more unit tests :)