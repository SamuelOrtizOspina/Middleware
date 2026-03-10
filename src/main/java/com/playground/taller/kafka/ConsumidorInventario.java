package com.playground.taller.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumidorInventario {

    public static void main(String[] args) {
        Properties props = ConfigKafka.getProperties("consumidor");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(ConfigKafka.TOPIC_PEDIDOS));

        System.out.println("Esperando pedidos en el servicio de Inventario...");

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("\nNuevo evento recibido.");
                    System.out.println("Key (ID): " + record.key());
                    System.out.println("Value (Datos): " + record.value());
                    System.out.println("Offset: " + record.offset());
                    System.out.println("Procesando: restando 1 unidad del inventario...");
                }
            }
        } finally {
            consumer.close();
        }
    }
}
