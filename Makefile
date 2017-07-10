.PHONY: build
build:
	./mvnw package

.PHONY: test
test:
	./mvnw test

.PHONY: clean
clean:
	./mvnw clean

.PHONY: install
install:
	./mvnw install -DskipTests=true -Dmaven.javadoc.skip=true -B -V

.PHONY: run
run:
	./mvnw spring-boot:run

.PHONY: heroku-cli
heroku-cli:
	echo "change to /app; then heroku login"
	docker run -it -v ${PWD}:/app heroku-runner /bin/bash

.PHONY: heroku-ps
heroku-ps:
	heroku ps

.PHONY: heroku-logs
heroku-logs:
	heroku logs

.PHONY: heroku-stop
heroku-stop:
	heroku ps:stop web.1

.PHONY: heroku-get-password
heroku-get-password:
	heroku logs -n 100 | grep "Using default security password"

