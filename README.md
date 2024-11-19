# Proyecto de Microservicios con Arquitectura Hexagonal

## Descripción

Este proyecto está diseñado para gestionar de manera independiente y escalable los procesos de negocio en diferentes áreas de la empresa. Utilizando **microservicios con API Gateway** y **Arquitectura Hexagonal**, buscamos proporcionar una solución robusta, escalable y flexible que permita la integración entre diversos servicios mientras mantenemos un alto nivel de autonomía en cada uno de ellos.

## Arquitectura

El proyecto sigue una **Arquitectura Hexagonal** basada en microservicios, lo que permite una clara separación de responsabilidades y facilita la escalabilidad de los distintos bounded contexts. A continuación, se detalla la elección de la arquitectura y la estructura de paquetes implementada.

### **ADR 001: Selección de Arquitectura para el Proyecto**

#### Decisión: Microservicios con API Gateway

**Motivo**: La arquitectura de microservicios con un API Gateway se eligió por ofrecer una clara separación de responsabilidades, escalabilidad independiente de cada bounded context y la posibilidad de integrarse de forma eficiente a través de APIs RESTful y eventos. 

#### Principales Características:
- **Escalabilidad**: Cada microservicio puede ser escalado de forma independiente.
- **Resiliencia**: Aislación de servicios, permitiendo que el fallo de uno no afecte a los demás.
- **Integración Flexible**: Los servicios se comunican a través de APIs RESTful y eventos.
- **Mantenibilidad**: Facilita la evolución de los servicios sin afectar a los demás.

#### Impacto:
- Mayor complejidad operativa debido a la gestión de múltiples servicios.
- Desarrollo inicial más lento, pero con mayores beneficios a largo plazo en términos de escalabilidad y flexibilidad.

---

### **ADR 002: Estructura de Paquetes para el Proyecto Java**

#### Decisión: Organización Modular de Paquetes

**Motivo**: Para asegurar la mantenibilidad, escalabilidad y comprensión del código, se adoptó una estructura de paquetes modular que separa claramente las distintas responsabilidades del sistema.

#### Estructura de Paquetes:
```plaintext
com.project
│
├── api               // Controladores
│   └── *Controller
│
├── service           // Interfaces de servicios y utilidades técnicas
│   ├── *Service
│   ├── *Mapper
│   └── CryptoProvider
│
├── business          // Implementación de lógica de negocio
│   ├── *ServiceImpl
│   └── *MapperImpl
│
├── repository        // Acceso a datos y repositorios
│   ├── *Repository
│   ├── *Query
│   ├── *Command
│   └── *SP
│
├── client            // Clientes HTTP utilizando Feign
│   └── *FeignClient
│
├── dto               // Objetos de transferencia de datos
│   ├── *Record
│   ├── *Result
│   ├── beans
│   ├── enums
│   └── constants
│
├── model             // Modelos de datos y constantes
│   ├── *Rq
│   ├── *Rs
│   ├── beans
│   ├── enums (errores)
│   └── constants
│
├── exception         // Manejo de excepciones personalizadas
│   ├── *CustomException
│   └── ErrorResponse
│
└── component         // Componentes auxiliares
    ├── ErrorResolver
    └── CryptoProviderImpl
```

### Justificación

- **Claridad y Consistencia**: La estructura modular permite una navegación clara y comprensible del código.
- **Mantenibilidad**: Facilita la evolución del sistema sin generar dependencias excesivas entre los distintos componentes.
- **Patrones de Diseño**: La estructura facilita la aplicación de principios como SOLID, lo que contribuye a una mayor calidad del código.

### Impacto

- **Esfuerzo Inicial**: Requiere un esfuerzo inicial para definir y aplicar consistentemente la estructura.
- **Curva de Aprendizaje**: Los nuevos desarrolladores podrían necesitar tiempo para familiarizarse con la estructura, pero a largo plazo facilita la integración y colaboración.

---

### Desarrollo y Testing

#### **Microservicios y API Gateway**
- **Comunicación**: Se utiliza comunicación a través de **APIs RESTful** y eventos, asegurando integración sin afectar la independencia de cada microservicio.
- **Pruebas Automatizadas**: Se implementarán pruebas unitarias, de integración y de aceptación para garantizar la calidad del sistema.

#### **Estructura de Paquetes**
Se seguirá la estructura definida en el ADR 002 para garantizar la claridad y modularidad del proyecto, con la separación de responsabilidades según el tipo de componente (controladores, servicios, lógica de negocio, repositorios, etc.).

---

### Próximos Pasos

1. **Implementación Inicial**: Crear ejemplos de implementación basados en los ADRs, específicamente para los casos de uso iniciales.
2. **Desarrollo de Servicios**: Desarrollar los primeros microservicios utilizando la estructura de paquetes definida.
3. **Testing**: Implementar y ejecutar pruebas automatizadas para cada microservicio.
4. **Documentación y Buenas Prácticas**: Documentar los patrones de diseño y buenas prácticas para asegurar la alineación de todos los miembros del equipo.
