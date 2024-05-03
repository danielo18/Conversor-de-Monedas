public class Registros {
    private String origen;
    private String cambio;
    private double cantidad;
    private double resultado;

    public Registros(String origen, String cambio, double cantidad, double resultado) {
        this.origen = origen;
        this.cambio = cambio;
        this.cantidad = cantidad;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Registros{ " +cantidad+" [ "+origen+" ] = "+resultado+ "[ "+cambio+" ]"+"}";
    }
}
