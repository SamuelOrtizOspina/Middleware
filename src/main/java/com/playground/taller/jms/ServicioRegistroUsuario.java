package com.playground.taller.jms;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

public class ServicioRegistroUsuario {

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/jms/queue/CorreoQueue")
    private Queue colaCorreos;

    public void registrarUsuario(String email, String nombre) {
        // Aqui iria la logica de persistencia en base de datos.
        String mensaje = "ENVIAR_BIENVENIDA:" + email;
        context.createProducer().send(colaCorreos, mensaje);

        System.out.println("Mensaje enviado al middleware. El usuario puede seguir navegando.");
    }
}
