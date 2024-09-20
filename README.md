
# Campaign Manager

This project is a web application split into two parts:
- **Backend**: A Spring Boot application using H2 in-memory database.
- **Frontend**: A React application served via Nginx.

The application is containerized using Docker, with both frontend and backend running in separate containers managed by Docker Compose.

## Prerequisites

Ensure you have the following installed:
- **Docker**: [Install Docker](https://www.docker.com/products/docker-desktop).
- **Docker Compose**: It comes pre-installed with Docker Desktop. For Linux, you may need to install it separately.

## Running the Application with Docker

This guide will help you set up and run the Campaign Manager application using Docker.

### Step 1: Clone the Repository

First, clone this repository to your local machine:

```bash
git clone https://github.com/your-repo/campaign-manager.git
cd campaign-manager
```

### Step 2: Build and Run the Docker Containers

To build and start the containers for both the backend and frontend, use Docker Compose:

```bash
docker-compose up --build
```

This command will:
- Build the **backend** (Spring Boot application) and the **frontend** (React application).
- Start both containers and connect them via Docker's networking.

### Step 3: Access the Application

Once the containers are running, you can access the services:

- **Frontend (React App)**: [http://localhost:3000](http://localhost:3000)  
  This is the user-facing interface.

- **Backend (Spring Boot API)**: [http://localhost:8080](http://localhost:8080)  
  This is the API where data is processed.


#### H2 Database Console Details:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `user`
- **Password**: `user`

### Step 4: Stopping the Containers

To stop the containers, press `CTRL+C` in the terminal where Docker Compose is running.

Alternatively, you can stop the containers by running:

```bash
docker-compose down
```

This will stop and remove the containers but leave the built images intact.

### Step 5: Removing Containers and Images

If you want to remove both the containers and the images (to rebuild everything from scratch), use:

```bash
docker-compose down --rmi all
```

## Application Details

### Backend (Spring Boot)

- **Port**: `8080`
- **Database**: H2 (in-memory)
- **Environment Variables** (defined in `docker-compose.yml`):
  - `SPRING_APPLICATION_NAME=backend`
  - `SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb`
  - `SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.h2.Driver`
  - `SPRING_DATASOURCE_USERNAME=user`
  - `SPRING_DATASOURCE_PASSWORD=user`
  - `SPRING_JPA_HIBERNATE_DDL_AUTO=create-drop`
  - `SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect`

### Frontend (React)

- **Port**: `3000`
- The React app is built using Node.js and served via Nginx.

## Project Structure

- **backend/**: Contains the Spring Boot application code.
- **src/**: Contains the React frontend application.
- **docker-compose.yml**: Defines the Docker services and how the backend and frontend containers should run.

---

## License

[MIT License](LICENSE)
