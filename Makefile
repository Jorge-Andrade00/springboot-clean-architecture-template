.PHONY: build start down clean help

# Name of the service for docker-compose
SERVICE_NAME=ms-users-java

# Build the Docker image and start the containers
build:
	docker-compose -p $(SERVICE_NAME) up --build -d

# Start the containers without rebuilding
start:
	docker-compose -p $(SERVICE_NAME) up -d

# Stop the containers
down:
	docker-compose -p $(SERVICE_NAME) down

# Stop and remove containers, networks, volumes, and orphans
clean:
	docker-compose -p $(SERVICE_NAME) down -v --remove-orphans

# Display help message
help:
	@echo "Usage: make [target]"
	@echo "Targets:"
	@echo "  build   - Build the Docker image and start the containers"
	@echo "  start   - Start the containers without rebuilding"
	@echo "  down    - Stop the containers"
	@echo "  clean   - Stop and remove containers, networks, volumes, and orphans"
