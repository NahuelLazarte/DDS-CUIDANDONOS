# Cuidándonos - Tarea de a pares

## Integrantes

  - Laureano Enrique
  - Carlos Nahuel Lazarte

# Punto 2 - Modelo de Dominio
## 1. Justificación 

La decisión de modelar tanto al viajero como al cuidador como una única clase *Persona* se debe a los atributos compartidos entre ambas entidades. Para discernir entre los distintos tipos de usuarios se diseñó una clase adicional denominada Usuario. Para ello, se empleó el **Patrón Stage**, priorizando la mantenibilidad y extensibilidad del sistema. Dado que en el futuro podrían surgir nuevos requisitos relacionados con usuario activo y pasivo.

Para los diferentes estados a lo largo del viaje, se aplicó nuevamente el **Patrón Stage**. Dado que permite manejar de manera eficiente las transiciones entre los estados.

La integración de la API Distance Matrix para calcular la distancia entre dos ubicaciones fue realizada mediante el **Patrón Adapter**. Una solución que aumenta la cohesión del sistema, previendo también que futuras modificaciones de la API puedan ser integradas. 

La reacción frente a un incidente se abordó mediante el **Patrón Stage**. Este patrón se seleccionó debido a su extensibilidad, para poder agregar nuevas formas de reaccionar, de ser necesario. 

## 2. Nuevo requerimiento

Para la diversas paradas que puede realizar el viajero, se consideró una clase parada que contendrá la dirección, tiempo de espera y un atributo *notificarEstado* para determinar si el viajero avisará parada a parada si llegó bien. El orden de cada parada será determinado por la posición de la misma en la lista *parada* de la clase *Viaje*
### Calculo de demora

```java

public double calcularDistanciaViaje(DistanciaEntreDosDirecciones caculador); // Adapter de la API

public void calcularTiempoDemora(Double distancia, Double velocidad); // Devuelve el tiempo de demora dada una distancia y velocidad


public double calcularDemoraEntreSeccionesActuales() {
      Parada parada = lista.get(this.numeroParadaActual);
      double acumulador = calcularTiempoDemora(calcularDistanciaViaje(caculador, this.direccionActual, this.direccionDestino), 7);
      return acumulador + parada.tiempo;
}

public double calcularDemoraTotal() {
  double contador = 0;
  List<Parada> auxParadas = new ArrayList<>(paradas);
  double suma = 0;

  Dirección auxDireccion = this.direccionActual;

  for (Parada parada : auxParadas) {
    suma += calcularTiempoDemora(calcularDistanciaViaje(caculador, auxDireccion, parada.getDirección()), 7);
    auxDireccion = parada.getDirección();
  )
  suma += calcularTiempoDemora(calcularDistanciaViaje(caculador, auxDireccion, this.direccionDestino, 7);
  
  return suma;
}
```
  