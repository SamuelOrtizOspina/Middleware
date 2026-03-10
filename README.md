# Correo Messaging (Spring Boot + CloudAMQP)

Proyecto base para declarar y usar una cola RabbitMQ en arranque:

- Cola durable creada como bean.
- Declaracion en startup para fallar rapido si no hay conectividad.
- Publicador de mensajes con `RabbitTemplate`.

## Variables de entorno

Configura estas variables antes de arrancar:

- `RABBITMQ_HOST`
- `RABBITMQ_PORT` (default `5672`)
- `RABBITMQ_USERNAME`
- `RABBITMQ_PASSWORD`
- `RABBITMQ_VHOST` (default `/`)
- `APP_QUEUE_NAME` (default `java:/jms/queue/CorreoQueue`)

## Ejecutar

```bash
mvn spring-boot:run
```

Si el broker no esta accesible o la cola no puede declararse, la app falla al iniciar.

Si `mvn` falla por `JAVA_HOME`, en PowerShell usa:

```powershell
$env:JAVA_HOME='C:\Program Files\Microsoft\jdk-21.0.8.9-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
mvn -v
```

## Ejecucion local sin broker (solo para pruebas de arranque)

Si necesitas que la app arranque sin RabbitMQ para validar estructura/logs:

```bash
mvn -s .mvn-settings.xml "-Dspring-boot.run.arguments=--app.messaging.startup-verify=false" spring-boot:run
```

En modo normal, `app.messaging.startup-verify` se mantiene en `true`.

## Parte 2 y 3 (Jakarta EE JMS + MDB)

Se agregaron estas clases de ejemplo para WildFly/Jakarta EE:

- `com.playground.taller.jms.ServicioRegistroUsuario`
- `com.playground.taller.jms.ProcesadorCorreos`

Notas:

- Ambas usan `java:/jms/queue/CorreoQueue`.
- `ProcesadorCorreos` usa `destinationType=jakarta.jms.Queue` (Jakarta EE actual).
- Estas clases se despliegan en un contenedor Jakarta EE (ej. WildFly), no se ejecutan con `spring-boot:run`.

## Parte 4 (Kafka con Upstash)

Se agregaron estas clases:

- `com.playground.taller.kafka.ConfigKafka`
- `com.playground.taller.kafka.ProductorPedidos`
- `com.playground.taller.kafka.ConsumidorInventario`

Variables de entorno para Kafka:

- `KAFKA_BOOTSTRAP_SERVERS`
- `KAFKA_USERNAME`
- `KAFKA_PASSWORD`
- `KAFKA_TOPIC` (opcional, default `pedidos-topic`)
- `KAFKA_CONSUMER_GROUP` (opcional, default `grupo-inventario`)

Compilar:

```bash
mvn -s .mvn-settings.xml compile
```

Ejecutar consumidor:

```bash
mvn -s .mvn-settings.xml compile exec:java -Dexec.mainClass="com.playground.taller.kafka.ConsumidorInventario"
```

Ejecutar productor:

```bash
mvn -s .mvn-settings.xml compile exec:java -Dexec.mainClass="com.playground.taller.kafka.ProductorPedidos"
```
