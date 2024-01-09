# Deploying the Application

To deploy the application you may have to use many different tools and services:

- [Docker](https://www.docker.com/) to build and run the application
- [Github Actions](https://github.com/features/actions) to build and push the application image
- [Kubernetes](https://kubernetes.io/) to manage the application


## 001 - Docker Compose

The Petclinic application uses a Docker Composer file to run some of the services locally. The file is located in the root of the project and is called `docker-compose.yml`.

The file contains the following services:

-  mysql
-  postgres

Let's say that you want to add a caching layer powered by Redis, use GitHub Copilot to add Redis and expose the default port (`6379`). See how you can also select specific version of the Redis image.

<details>
<summary>Possible Flow</summary>

1. Open the `docker-compose.yml` file
2. Using Copilot completion add the service `redis:` and let Copilot suggest the image and the version
3. You can also use comments to select a specific version of the image and map the port, something like:

    `# add redis service using version 7 and map the default port and expose it to 7777`


</details>


## 002 - Create a Dockerfile for the application

Lets now use Copilot to create a Dockerfile for the application. The Dockerfile will be used to build the application image.

You can also use Copilot chat to see how to build the image, and run it. In Copilot Chat you can also copy the commands to build and run the image.

<details>
<summary>Possible Flow</summary>

1. Open GitHub Copilot Chat 
2. Type `@workspace create a Dockerfile to package the petclinic springboot application using Java 17 and expose port 8080`
3. Copilot will create a Dockerfile for you
4. If you have access to Docker you can build the image using `docker build -t copilot-petclinic .`

Note: Copilot will create a Dockerfile that is not necessary the best one for your application. You may have to tweak to make it smaller and matches your enterprise standards.


When the file is created:

1. Open Copilot Chat and ask `how to build the image`
2. Use the icon to copy the command in the terminal
3. then ask `how to run the image`
4. Use the icon to copy the command in the terminal

</details>


## 003 - Adding Docker Image Build & Push to GitHub Actions

Now that you have a docker file we can use GitHub Actions to build and push the image to a registry.

Open the `.github/workflows/maven-build.yml` file and use Copilot to add a new job to build and push the image.

<details>
<summary>Possible Flow</summary>

1. Open the `.github/workflows/maven-build.yml` file
2. Use Copilot to add a new job to build and push the image using `add a new job to build and publish the docker image to GitHub Packages`

The new job looks like:

```yaml
  publish-docker:
    needs: build
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Login to GitHub Packages
        uses: docker/login-action@v3
        with:
          registry: ghcr.io
          username: ${{ github.actor }}
          password: ${{ secrets.GITHUB_TOKEN }}

      - name: Build and push Docker image
        uses: docker/build-push-action@v5
        with:
          context: .
          push: true
          tags: ghcr.io/${{ github.repository_owner }}/copilot-petclinic:${{ github.sha }}
```

</details>


## 004 - Preparing the Kubernetes Deployment

Now that you have a Dockerfile and a GitHub Actions workflow to build and push the image, you can use Copilot to create a Kubernetes deployment.

<details>

<summary>Possible Flow</summary>

1. Open Copilot Chat and ask `@workspace how to create a Kubernetes deployment for the petclinic application using MySQL and Java app in 2 differents container on the same pod`

This is just to get an idea about how Copilot can help you. The generated file is not perfect and you may have to tweak it to match your enterprise standards.
</details>

## Optional excercises

In addition to what we have done so far, you can also use Copilot to:

- Add new GitHub Actions Worflows to create a new Container Image when a new release is created
- Add a new GitHub Actions Workflow to deploy the application to Kubernetes
- Use GitHub Copilot to create Terraform files to prepare the Kubernetes cluster