Dropwizard Hello World project
===========================================

This is a simple hello world project that provides an idea on how to work with the dropwizard.io framework.

Most of the code of this project is a copy-paste of the project 
https://github.com/dropwizard/dropwizard/tree/master/dropwizard-example

Most of the details about the project are already well described here :

http://www.dropwizard.io/0.9.2/docs/getting-started.html




The current project  contains sample code on how to do :

* a simple REST resource (see HelloWorldResource)
* database integration (see usages of PersonDAO)
* database migration with liquibase (see src/main/resources/migrations.xml)
* simple CRUD resource for a database backed repository (see PersonResource and PeopleResource)
* usage of different templating engines (see PersonView and person.ftl, person.mustache files)


## Setup of the project

Because the project is backed by an H2 database, first you'll need to execute the application with the following
command line parameters :

```
db migrate hello-world.yml
```

More information here : 

http://www.dropwizard.io/0.9.2/docs/manual/migrations.html


After the database is successfully created, start the application by executing the application with the following
command line parameters:

```
server hello-world.yml
```


In order to create Person entities use the following shell command :

```
curl -H "Content-Type: application/json" -X POST -d '{"fullName": "Coda", "jobTitle": "Pancake Baker"}' http://localhost:8080/people
```



In order to see all the persons stored in the database use the following command:

```
curl http://localhost:8080/people
```


## Useful endpoints

### Administration endpoint

http://localhost:8081/


### Healtcheck

http://localhost:8081/healthcheck?pretty=true

### Metrics

http://localhost:8081/metrics?pretty=true

Worth noting here are the timers beginning with "eu.test.dropwizard.resource.PeopleResource" string, because they give
informations about the metrics for the REST resource operations. 