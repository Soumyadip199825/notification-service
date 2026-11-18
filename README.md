# notificationservice
![img.png](img.png)

Projects

Notification Platform

• Built event-driven notification service using Spring Boot, Kafka and Redis.

• Containerized services using Docker.

• Stored user-generated content in AWS S3.

• Deployed backend services on AWS EC2.

• Integrated PostgreSQL and Redis for persistence and caching.

POST /events

GET /users/{userId}/notifications

PUT /notifications/{id}/read

GET /users/{userId}/notifications/unread-count

Notification Table:

id

userId

sequenceNumber

type

title

message

status

createdAt

