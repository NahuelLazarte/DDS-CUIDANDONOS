package recorrido;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import lombok.Getter;
import lombok.Setter;
import personas.Cuidador;
import personas.Viajero;
import utils.adapterApi;
import utils.estadoViaje;
import utils.funcionesUtiles;

import java.io.IOException;
import java.util.List;

import static utils.estadoViaje.INCIDENTE;
import static utils.estadoViaje.OK;

@Getter
@Setter
public class Viaje {
    private funcionesUtiles utils;
    private List<Cuidador> cuidadores;
    public Direccion origen;
    public Direccion destino;
    private Viajero viajero;
    public estadoViaje estado;
    private adapterApi apiGoogle= new adapterApi();



    public Viaje(List<Cuidador> cuidadores, Direccion origen, Direccion destino, Viajero viajero) {
        this.cuidadores = cuidadores;
        this.origen = origen;
        this.destino = destino;
        this.viajero = viajero;

    }

    public void comenzar() throws InterruptedException {
        this.viajero.desactivarNotificaciones();
        notificarCuidadores("Inicio del viaje!");

        Integer tiempoSegundos = tiempoSegundos();
        Distance distancia = distanciaMetros();
        utils.esperarTiempo(tiempoSegundos);
        if (this.estado == OK) {
        this.viajero.activarNotificaciones();
        notificarCuidadores("Finalizacion del viaje");

        }
        else if (this.estado == INCIDENTE) {
            this.viajero.reaccionarIncidente();
        }


    }

    private void notificarCuidadores(String s) {
        for (Cuidador c : cuidadores) {
            c.recibirNotificacion(s,this.viajero);
        }
    }

    public Integer tiempoSegundos() {
        String tiempoString = this.apiGoogle.getTiempo();
        return Integer.parseInt(tiempoString);
    }


    public Distance distanciaMetros(){
        return this.apiGoogle.getDistancia();
    }


    public void llegueBien() {
        setEstado(OK);
    }
}
