# Springboot RabbitMQ

Simple backend application for publishing and consuming recurses from RabbitMQ queue

## Installing

- Update the docker-compose.yaml file with your settings like following:

```
version: '3'

services:
 rabbitmq:
   image: rabbitmq:management
   container_name: rabbitmq
   environment:
     - RABBITMQ_DEFAULT_USER=YOUR_RMQ_USER
     - RABBITMQ_DEFAULT_PASS=RMQP4ssw0rd#
   ports:
     - "5672:5672"
     - "15672:15672"

networks:
 default:
   driver: bridge
```

- Update the src/main/resources/application.properties file with your settings like following:

```
spring.application.name=springboot-rabbitmq
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=YOUR_RMQ_USER
spring.rabbitmq.password=RMQP4ssw0rd#
rabbitmq.exchange.user.name=user_exchange
rabbitmq.queue.user.name=user_creation_queue
rabbitmq.routing.user.key=user_routing_json_key
```

- Run the SpringBoot application and make a POST REST Api call to send a JSON message to RabbitMQ:

```
# URL:
http://localhost:8080/api/v1/publish

# Payload:
{
    "id": 1,
    "firstName": "Peter",
    "lastName": "Parker"
}
```
