# RabbitMQ Test: Producer & Consumer

Этот проект демонстрирует взаимодействие Producer и Consumer через RabbitMQ с использованием Spring Boot и Docker Compose.

## Состав проекта

- `producer` — Spring Boot REST API, отправляет задачи в очередь.
- `consumer` — слушает очередь, обрабатывает задачи.
- `rabbitmq` — брокер сообщений RabbitMQ (c UI на `localhost:15672`).

## Запуск

> Убедитесь, что у вас установлен Docker и Docker Compose.

### Запуск с одним инстансом consumer

```bash
docker compose up -d --build --scale consumer=1
```

### Запуск с 10 инстансами consumer

```bash
docker compose up -d --scale consumer=10
```

> Контейнеры будут автоматически распределять нагрузку (при условии, что очередь настроена без привязки к одному consumer’у).


## Тестирование через JMeter

> Убедитесь, что у вас установлен Apache Jmeter.

### Запуск
1. В меню File -> Open откройте ваш .jmx файл.
2. Нажмите кнопку Start (зеленый треугольник) на панели инструментов.

## Логи

Логи producer и consumer сохраняются в локальные директории:
- `./logs/producer/producer.log`
- `./logs/consumer/consumer.log`
