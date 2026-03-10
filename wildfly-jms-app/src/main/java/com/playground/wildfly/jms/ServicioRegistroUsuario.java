package com.playground.wildfly.jms;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

@ApplicationScoped
public class ServicioRegistroUsuario {

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/jms/queue/CorreoQueue")
    private Queue colaCorreos;

    public void registrarUsuario(String email, String nombre) {
        String mensaje = "ENVIAR_BIENVENIDA:" + email + ":" + nombre;
        context.createProducer().send(colaCorreos, mensaje);
        System.out.println("Mensaje enviado al middleware. El usuario puede seguir navegando.");
    }
}
