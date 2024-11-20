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
├── api               // Adaptadores de entrada (controladores)
│   └── *Clases Controller
│
├── application       // Casos de uso y orquestadores de la aplicación
│   └── *Clases que implementan la lógica de aplicación (aplicación de la lógica de dominio)
│
├── domain            // Dominio (entidades, valores, agregados, servicios del dominio)
│   └── *Bounded Context
│       ├── *Entidades
│       └── *Servicios
│
├── infrastructure    // Adaptadores de infraestructura (bases de datos, APIs externas)
│   ├── *Repositorios
│   ├── *Acceso a datos (JPA, repositorios)
│   └── *Adaptadores APIs externas
│
├── service           // Interfaces de servicios técnicos y utilidades
│   ├── *Interfaz Service
│   ├── *Interfaz Mapper
│   └── *Interfaz CryptoProvider
│
├── business          // Implementación lógica de negocio
│   ├── *Clase que implementa la interfaz Service
│   └── *Clases que implementan las Interfaces Mapper
│
├── repository        // Implementación de acceso a datos, repositories
│   ├── *Repositorios
│   ├── *Queries
│   ├── *Comandos
│   └── *Stored Procedures
│
├── client            // Clientes HTTP utilizando Feign
│   └── *Interfaces que implementan la librería Feign para llamadas HTTP
│
├── dto               // Objetos de transferencia de datos
│   ├── *Clases Record
│   ├── *Clases Result
│   ├── *Beans de las clases Record y Result
│   ├── *Enums
│   └── *Constantes
│
├── model             // Modelos de datos y constantes (generalmente los de comunicación)
│   ├── *Rq, *Rs
│   ├── *Beans para las clases Rq y Rs
│   ├── *Enums y Constants
│   └── *Interfaces Repository
│
├── exception         // Manejo de excepciones personalizadas
│   ├── *Clases excepciones custom
│   └── *ErrorResponse
│
└── component         // Componentes auxiliares (servicios transversales)
    ├── *ErrorResolver
    └── *Implementación de interfaces como CryptoProvider
```