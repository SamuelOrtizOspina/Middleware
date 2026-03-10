package com.playground.taller.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;

import java.util.Map;
import java.util.Properties;

public final class ConfigKafka {

    private static final String BOOTSTRAP_SERVERS = env("KAFKA_BOOTSTRAP_SERVERS", "tu-endpoint-de-upstash:9092");
    private static final String CLUSTER_USERNAME = env("KAFKA_USERNAME", "tu-usuario");
    private static final String CLUSTER_PASSWORD = env("KAFKA_PASSWORD", "tu-password");
    public static final String TOPIC_PEDIDOS = env("KAFKA_TOPIC", "pedidos-topic");

    private ConfigKafka() {
    }

    public static Properties getProperties(String tipo) {
        Properties props = new Properties();
        props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
        props.put(
                SaslConfigs.SASL_JAAS_CONFIG,
                "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"" +
                        CLUSTER_USERNAME + "\" password=\"" + CLUSTER_PASSWORD + "\";"
        );

        if ("productor".equalsIgnoreCase(tipo)) {
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        } else {
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, env("KAFKA_CONSUMER_GROUP", "grupo-inventario"));
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        }
        return props;
    }

    private static String env(String key, String defaultValue) {
        return Map.copyOf(System.getenv()).getOrDefault(key, defaultValue);
    }
}
