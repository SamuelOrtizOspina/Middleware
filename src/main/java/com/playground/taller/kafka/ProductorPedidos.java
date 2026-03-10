package com.playground.taller.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProductorPedidos {

    public static void main(String[] args) {
        Properties props = ConfigKafka.getProperties("productor");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        String idPedido = "PED-1001";
        String datosPedido = "Cliente: Carlos, Producto: Laptop, Valor: $2000";
        ProducerRecord<String, String> record = new ProducerRecord<>(ConfigKafka.TOPIC_PEDIDOS, idPedido, datosPedido);

        try {
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.println("Evento enviado exitosamente.");
                    System.out.println("Topic: " + metadata.topic());
                    System.out.println("Partition: " + metadata.partition());
                    System.out.println("Offset: " + metadata.offset());
                } else {
                    exception.printStackTrace();
                }
            });

            producer.flush();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            producer.close();
            System.out.println("Productor cerrado.");
        }
    }
}
