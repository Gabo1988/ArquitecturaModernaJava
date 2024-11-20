# Proyecto de Microservicios con API Gateway y Domain-Driven Design (DDD)

## Descripción

Este proyecto está diseñado para gestionar de manera independiente y escalable los procesos de negocio en diferentes áreas de la empresa. Utilizando **microservicios con API Gateway** y **Domain-Driven Design (DDD)**, buscamos proporcionar una solución robusta, escalable y flexible que permita la integración entre diversos servicios mientras mantenemos un alto nivel de autonomía en cada uno de ellos.

Además, seguimos el enfoque de **Domain-Driven Design (DDD)** para modelar y estructurar el dominio del negocio de forma eficiente.

## Arquitectura

**Microservicios con API Gateway y Domain-Driven Design (DDD)**.

La arquitectura seleccionada combina microservicios con principios de DDD, donde cada microservicio representa un **bounded context** claramente definido. En su interior, la lógica de negocio estará organizada según las prácticas de DDD, separando los dominios del resto de las preocupaciones técnicas.

Los microservicios estarán conectados a través de un **API Gateway**, con comunicación basada en **APIs RESTful** y **eventos**, para mantener la independencia y escalabilidad de cada **bounded context**.

### Estructura de Paquetes:
```plaintext
com.project
│
├── api               // Adaptadores de entrada (controladores y DTOs para entrada/salida)
│   ├── controller     // Clases Controller
│   └── dto            // Objetos de transferencia de datos específicos para las APIs
│
├── application       // Casos de uso y lógica de aplicación
│   ├── usecase         // Clases que implementan la lógica de aplicación
│   └── service         // Interfaces de servicios de aplicación
│
├── domain            // Dominio (entidades, valores, agregados, servicios del dominio)
│   ├── model           // Entidades, Value Objects, y Agregados
│   ├── service         // Servicios del dominio (sin dependencias externas)
│   └── event           // Eventos del dominio (si aplica)
│
├── infrastructure    // Adaptadores de infraestructura (bases de datos, APIs externas, etc.)
│   ├── persistence     // Repositorios e implementación de acceso a datos (JPA, etc.)
│   ├── client          // Clientes HTTP (e.g., Feign)
│   └── config          // Configuraciones específicas de infraestructura
│
├── exception         // Manejo de excepciones personalizadas
│   ├── custom          // Clases de excepciones personalizadas
│   └── handler         // Manejadores de excepciones globales
│
├── shared            // Código compartido entre varios módulos
│   ├── utils           // Clases de utilidad
│   └── constants       // Constantes y enums globales
└── component         // Servicios auxiliares o transversales
    ├── security        // Clases relacionadas con seguridad (e.g., autenticación)
    └── crypto          // Implementación de interfaces como CryptoProvider

```