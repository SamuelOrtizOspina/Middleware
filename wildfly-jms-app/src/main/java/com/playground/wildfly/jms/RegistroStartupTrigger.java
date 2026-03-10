package com.playground.wildfly.jms;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class RegistroStartupTrigger {

    @Inject
    private ServicioRegistroUsuario servicioRegistroUsuario;

    @PostConstruct
    public void enviarMensajeDemo() {
        servicioRegistroUsuario.registrarUsuario("demo@umb.edu", "Demo");
    }
}
