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

========
POST   /notifications

GET    /notifications/users/{userId}

GET   /notifications/users/{userId}/unread-count

PATCH  /notifications/{id}/read

PATCH  /notifications/users/{userId}/read-all

