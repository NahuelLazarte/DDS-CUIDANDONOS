package recorrido;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Direccion {
    private String Calle;
    private String Altura;

    public Direccion(String direccion) {
        String[] direccionNormalizada = normalizarDireccion(direccion);
        Calle = direccionNormalizada[0];
        Altura = direccionNormalizada[1];
    }

    String[] normalizarDireccion(String direccion){
        String[] DireccionSplited = direccion.split(",");
        return DireccionSplited;
    }
}

