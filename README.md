

## Installation

 Ensure that Java 8 and Maven 3.2 are installed

## Usage 

Navigate to the directory into which you cloned the repo and execute this:

     mvn spring-boot:run

Once started you can access the APIs on port 9080, e.g.

##### Basic prime calculation
`http://localhost:9080/prime/basic/10`

##### Parallel prime calculation (Fork Join)
`http://localhost:9080/prime/parallel/10`

##### Stream prime calculation
`http://localhost:9080/prime/stream/10`

###### Stream Parallel prime calculation
`http://localhost:9080/prime/stream/parallel/10`


### How to build docker image


        mvn package

        mvn docker:build


### How to run docker container

        docker run -d --name prime -p 9080:9080 prime-calculator

Once run a Docker container you can access the APIs on port 9080, e.g.
`http://192.168.99.100:9080/prime/basic/10`
`http://192.168.99.100:9080/prime/parallel/10`
`http://192.168.99.100:9080/prime/stream/10`
`http://192.168.99.100:9080/prime/stream/parallel/10`


The port number can be changed by editing the port property in `src/main/resources/application.yml`

