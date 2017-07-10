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

