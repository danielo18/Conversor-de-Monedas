import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        int reinicio=0;
        String monedaOrigen = "";
        String monedaCambio = "";
        double cantidadMoneda;
        double tipoCambio;
        int salida=0;
        List<Registros> registro =new ArrayList<>();
        while (salida==0) {
            reinicio = 0;
            System.out.println("""
                    ******************************************
                    Bienvenidos al conversor de monedas.
                    
                    1.-Dolar -->Peso Mexicano
                    2.-Peso Mexicano -->Dolar
                    3.-Dolar -->Real brasileño
                    4.-Real brasileño -->Dolar
                    5.-Dolar -->Yen Japones
                    6.-Yen Japones -->Dolar
                    7.-Historial de Conversiones
                    8.-Salir
                    ELIJA UNA OPCION VALIDA:
                    ****************************************** """);
            try {
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        monedaOrigen = "USD";
                        monedaCambio = "MXN";
                        break;
                    case 2:
                        monedaOrigen = "MXN";
                        monedaCambio = "USD";
                        break;
                    case 3:
                        monedaOrigen = "USD";
                        monedaCambio = "BRL";
                        break;
                    case 4:
                        monedaOrigen = "BRL";
                        monedaCambio = "USD";
                        break;
                    case 5:
                        monedaOrigen = "USD";
                        monedaCambio = "JPY";
                        break;
                    case 6:
                        monedaOrigen = "JPY";
                        monedaCambio = "USD";
                        break;
                    case 7:
                        for (Registros reg: registro){
                            System.out.println(reg);
                        }
                        reinicio=1;
                        break;
                    case 8:
                        salida=1;
                        break;
                    default:
                        System.out.println("Opcion no disponible");
                        salida=1;
                        break;

                }
            }catch (InputMismatchException e) {
                System.out.println("Opcion no disponible");
                salida = 1;
            }

            if(salida==1) {
                System.out.println("Vuelve pronto");
                break;
            }if(reinicio==0) {
                System.out.println("Ingresa la cantidad que desea convertir: ");
                cantidadMoneda = teclado.nextDouble();
                ConsultarApi consultar = new ConsultarApi();
                try {
                    String json = consultar.consulta(monedaOrigen, monedaCambio);
                    ExtraerDatosJSON cambioMoneda = new ExtraerDatosJSON();
                    tipoCambio = cambioMoneda.obtenerValorMoneda(json, monedaCambio);
                    Operaciones cambio = new Operaciones();
                    System.out.println("El valor " + cantidadMoneda + "[ " + monedaOrigen + " ] corresponde a " +
                            cambio.opearcionMoneda(tipoCambio, cantidadMoneda) + " [ " + monedaCambio + " ] ");
                    Registros registros = new Registros(monedaOrigen, monedaCambio, cantidadMoneda, cambio.opearcionMoneda(tipoCambio, cantidadMoneda));
                    registro.add(registros);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);

                }
            }
        }
    }
}
