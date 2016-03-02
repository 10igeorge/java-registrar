# University Registrar

#### An app for a registrar to add students and courses and assign them

#### By Abby Rolling & Izzy

## Description

This Java app allows a university registrar to manage courses and students by adding new instances of both and associating them. It allows them to add a class only if the student is not yet enrolled in it.

## Setup/Installation Requirements

Clone this repository:
```
$ cd ~/Desktop
$ git clone github address
$ cd folder-name
```

Open terminal and run Postgres:
```
$ postgres
```

Open a new tab in terminal and create the `registrar` database:
```
$ psql
$ CREATE DATABASE registrar;
$ psql registrar < registrar.sql
```

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```

## Technologies Used

* Java
* junit
* Gradle
* Spark
* fluentlenium
* PostgreSQL
* Bootstrap

### License

Licensed under the GPL.

Copyright (c) 2016 **Abigail Rolling and **
