package com.msvc.ai_scanner.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Parfinanciero - Registro de Gastos e Ingresos",
                description = "Micro servicio encargado del control de gastos e ingresos del usuario permitiendo hacer uso de IA para lectura de facturas, en este microServicio el usuario podra ingresar los movimientos de dinero que realiza diariamente.",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {
}
