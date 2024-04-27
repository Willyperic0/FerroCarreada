# FerroCarreada

# Sistema de Gestión de Trenes de Transporte de Pasajeros

Este proyecto de aula, denominado Sistema de Gestión de Trenes de Transporte de Pasajeros, tiene como objetivo principal automatizar el control de cargas y descargas de los trenes de transporte, un proceso actualmente realizado de manera manual. A través de este documento, se detallan los requisitos, funcionalidades y restricciones del sistema, definidos en base a entrevistas realizadas a las partes interesadas en la organización del transporte.

## Propósito del Sistema

El Sistema de Gestión de Trenes de Transporte de Pasajeros busca automatizar el control de cargas y descargas de los trenes de transporte de pasajeros. Esto incluye la gestión de actividades como la compra de boletos, revisión de boletos para abordar, control de equipaje, validación de boletos durante el trayecto, desembarque y entrega de equipaje, así como labores administrativas relacionadas con la gestión de trenes, rutas, horarios y empleados.

## Requerimientos

El sistema debe ser capaz de gestionar múltiples acciones realizadas por los pasajeros, así como las tareas administrativas asociadas con la operación de los trenes de transporte de pasajeros. Estas acciones incluyen:

- Compra de boletos.
- Revisión del boleto para abordar.
- Control de equipaje.
- Validación del boleto durante el trayecto.
- Desembarque y entrega de equipaje.

Además, se deben contemplar las siguientes labores administrativas:

- Agregar nuevos trenes.
- Dar de baja trenes existentes.
- Crear y gestionar rutas.
- Administrar horarios de las rutas.
- Publicar las rutas.
- Ofrecer la mejor ruta.
- Gestionar empleados.

## Almacenamiento de Datos

El sistema debe permitir almacenar los datos de los trenes, incluyendo nombre, identificador, capacidad de carga y kilometraje. La capacidad de carga puede variar dependiendo del tipo de tren. Se debe considerar que cada vagón de pasajeros está dividido en tarifas premium, ejecutiva y estándar, y se permiten dos maletas de 80 kilogramos cada una por pasajero. Se espera un vagón de carga por cada dos vagones de pasajeros.

## Venta de Boletos

Los boletos se venden en máquinas ubicadas en las estaciones. La información básica del boleto incluye detalles del pasajero, identificación del tren, lugar, categoría del pasajero, valor del pasaje y detalles del equipaje. Se debe asumir que las máquinas de la estación pueden leer tarjetas de crédito, dinero en efectivo y pesar el equipaje, aunque esta funcionalidad no será simulada en este ejercicio.

## Administración de Rutas y Horarios

El sistema debe incluir un módulo para la administración de rutas y horarios. Se deben poder modificar horarios y recorridos si no se ha iniciado el trayecto. Además, la máquina de ventas debe proporcionar las rutas y horarios disponibles al pasajero, así como recomendar la ruta personal más corta. Las rutas se configuran con información del tren, origen, destino y estaciones que se pueden o no visitar. Se debe considerar que un pasajero pueda necesitar cambiar de ruta para llegar a su destino.

## Matriz de Rutas

Las rutas se describen en una matriz donde las letras representan las estaciones y los números indican la distancia entre las estaciones medida en kilómetros.

Nota del Proyecto: Los nombres de las estaciones pueden modificarse según criterio y creatividad.

---

Este README proporciona una visión general de los requisitos y funcionalidades del Sistema de Gestión de Trenes de Transporte de Pasajeros para el Proyecto de Aula 2024-10.

