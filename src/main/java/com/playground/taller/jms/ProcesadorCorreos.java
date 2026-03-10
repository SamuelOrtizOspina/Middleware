package com.playground.taller.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/CorreoQueue"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
        }
)
public class ProcesadorCorreos implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage textMessage) {
                String texto = textMessage.getText();
                System.out.println("Middleware entrego: " + texto);
                enviarEmailReal(texto);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void enviarEmailReal(String datos) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Correo enviado exitosamente con payload: " + datos);
    }
}
