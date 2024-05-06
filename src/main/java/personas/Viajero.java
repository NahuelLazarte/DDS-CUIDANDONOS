package personas;
import lombok.Getter;
import lombok.Setter;
import recorrido.Direccion;
import utils.funcionesUtiles;
import utils.sexo;
import incidentes.Incidente;
import recorrido.Viaje;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Viajero {
    private funcionesUtiles utils;
    private String nombre;
    private String apellido;
    private Integer edad;
    private sexo sexo;
    private Boolean notificacionesActivadas = null;
    private Incidente reaccionarIncidente;
    public List<Cuidador> cuidadores = null;

    public Viajero(utils.sexo sexo, Integer edad, String apellido, String nombre, Incidente reaccionarIncidente) {
        this.sexo = sexo;
        this.edad = edad;
        this.apellido = apellido;
        this.nombre = nombre;
        this.reaccionarIncidente = reaccionarIncidente;
    }

    public void RealizarViaje(String origen, String destino) throws InterruptedException { // No sabemos como nos va a pasar la direccion el usuario

    Direccion dirOrigen = new Direccion(origen);
    Direccion dirDestino = new Direccion(destino);


    List<Cuidador> cuidadoresSeleccionados= elegirCuidador();

    Viaje nuevoViaje = new Viaje(cuidadoresSeleccionados,dirOrigen,dirDestino,this);

    if (algunCuidadorAcepto(cuidadoresSeleccionados)){
        nuevoViaje.comenzar();
    }

    utils.esperarTiempo(nuevoViaje.tiempoSegundos());

        nuevoViaje.llegueBien();


  }


    private boolean algunCuidadorAcepto(List<Cuidador> cuidadoresSeleccionados) {
        return !cuidadoresSeleccionados.isEmpty();
    }

    private List<Cuidador> elegirCuidador() {
        List<Cuidador> cuidadoresSeleccionados = new ArrayList<>();
        for (Cuidador cuidador : cuidadores) {
            boolean respuesta = cuidador.solicitarViaje(this);
            if (respuesta) {
                cuidadoresSeleccionados.add(cuidador);
            }
        }
        return cuidadoresSeleccionados;
    }

    public void desactivarNotificaciones() {
        this.notificacionesActivadas = false;
    }

    public void activarNotificaciones() {
        this.notificacionesActivadas=true;
    }

    public void reaccionarIncidente() {
        reaccionarIncidente.reaccionar();
    }
}
