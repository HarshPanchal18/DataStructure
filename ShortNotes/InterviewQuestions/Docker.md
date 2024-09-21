# Docker Interview Questions

1. What is the difference between a container and a virtual machine?

* A container is an `isolated and lightweight` runtime environment that shares the host system's OS kernel, libraries, and resources. It `provides process-level isolation` and `allows applications to run consistently across different environments`.

* On the other hand, a virtual machine is `a complete and independent` OS installation running on virtualized hardware, providing full isolation and running multiple instances of OS and applications.

2. What is Docker Engine?

* Docker Engine is a `client-server application` that provides the core functionality for `building, running, and managing` Docker containers.
* It consists of a `Docker daemon (server)` and a `Docker CLI (client)` that communicate with each other.
* The Docker Engine manages the `container lifecycle, networking, storage, and other essential aspects` of the Docker platform.

3. What is a Docker Image?

* A Docker image is a `lightweight, standalone, and executable software package` that _includes everything needed to run a piece of software including the code, runtime, libraries, dependencies, and system tools._
* It is created from a set of instructions defined in a `Dockerfile` and can be used to create Docker containers.

4. What is Docker hub?

* Docker Hub is a `cloud-based registry provided by Docker` that allows developers to `store, share, and distribute` Docker images.
* It provides a _central repository of public and private Docker images._
* This makes it easy to discover and access pre-built images created by the Docker community and other organizations.

5. What is Dockerfile?

* A Dockerfile is `a text file` that contains _a set of instructions for building a Docker image._
* It provides a declarative and reproducible way to define the software stack, dependencies, and configuration needed for an application.
* Dockerfiles include commands to _copy files, install packages, set environment variables, and execute other actions `required to create a Docker image.`_

6. How do you build a Docker image using a Dockerfile?

* To build a Docker image using a Dockerfile, you use the `docker build` command followed by the _path to the directory containing the Dockerfile._

> For example, `docker build -t myimage:tag`. will build an image named `myimage` with the specified tag using the Dockerfile in the current directory.

* The Docker daemon reads the instructions from the Dockerfile and builds the image `layer by layer`.

6. List down the components of Docker.

* The following are the three primary Docker components:

* `Docker Client:` Performs To `communicate with the Docker Host`, use the `Docker build` and run procedures. The Docker command then uses the Docker API to conduct any queries that need to be run.

* `Docker Host:` It is `a service` that _allows you to host Docker containers._ The Docker `daemon, containers, and accompanying images` are all included in this package. A connection is established between the Docker daemon and the Registry. The type of metadata related to containerized apps is `saved pictures`.

* `Registry:` _Docker images are kept in this folder._ A public register and a private registry are also available. _Docker Hub_ and _Docker Cloud_ are two open registries that anybody can utilize.

7. How do you remove a Docker container?

* To remove a Docker container, you use the `docker rm` command followed by the `container ID or name`.

> For example, `docker rm mycontainer` will remove a container named mycontainer.

* If the container is currently running, you need to stop it first using the docker stop command.

8. What is a Docker Compose?

* Docker Compose is an essential tool in the Docker ecosystem that `facilitates the management of multi-container applications.`
* It is a command-line tool that _allows developers to define and run multi-container Docker applications using a simple YAML file called a `docker-compose.yml`._

* With Docker Compose, developers can `define the services, networks, and volumes` required for their application stack, streamlining the process of deploying and orchestrating interconnected containers.

9. How do you scale Docker containers horizontally?

* To scale Docker containers horizontally, you can use `Docker Swarm` or a `container orchestration` tool like `Kubernetes`.
* With `Docker Swarm`, you can create a cluster of Docker nodes and use the `docker service` command to scale the desired number of replicas for service across multiple nodes.

> For example, docker service scale myservice=5 will scale the service named myservice to 5 replicas.
