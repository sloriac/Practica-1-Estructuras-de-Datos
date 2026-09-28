import java.time.LocalDate;
import java.util.Scanner;
// Rutina main que permite correr el programa y contiene el menu principal quer lleva a los menus secundarios (usuario y administrador)
public class Main {

    //VARIABLES
    static Scanner entrada = new Scanner(System.in); //permite entrada del usuario
    int seleccion; //seleccion de opcion del menu
    ColaEstaticaTicket pendientes = new ColaEstaticaTicket(5); //Inicializa la longitud de la lista de tickets
    ListaEnlazadaSimpleTicket resueltos = new ListaEnlazadaSimpleTicket(); //Permite llevar los tickets resueltos a la lista enlazada simple

    // Funcion que limpia la consola del menu (estetica)
    public void limpiarConsola(){
        try{
            String sistemaOperativo = System.getProperty("os.name");   //Detectar el sistema operativo
            if (sistemaOperativo.contains("Windows")) {
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                pb.environment().putIfAbsent("TERM", "xterm");
                pb.inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("NO se pudo limpiar la consola . . .");
        }
    }

    //Funcion que realiza una pausa para la visualizacion de la informacion del menu
    public void pausa() {
        System.out.println("Enter para continuar.");
        entrada.nextLine();
    }

    //Previene que se ingresen letras en el menu, solo numeros
    public int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada incorrecta, ingrese un numero: ");
            }
        }
    }

    //MENUS
    public void menu() {
        while (true) { //Menu principal
            limpiarConsola();
            System.out.println("==================================================");
            System.out.println("Bienvenido al menu de tickets");
            System.out.println("==================================================");
            System.out.println("     1. Rol de administrador.");
            System.out.println("     2. Rol de usuario.");
            System.out.println("     3. Salir.");
            System.out.println("--------------------------------------------------");
            System.out.print("Seleccione su rol: ");
            seleccion = leerEntero();
            if (seleccion == 1) { //Menu de administrador
                System.out.println("==================================================");
                System.out.println("Bienvenido al menu de tickets (Administrador)");
                System.out.println("==================================================");
                System.out.println("     1. Visualizar el ticket al frente de la cola.");
                System.out.println("     2. Resolver el ticket al frente de la cola.");
                System.out.println("--------------------------------------------------");
                System.out.print("Seleccione una opcion: ");
                seleccion = leerEntero();
                if (seleccion == 1) {
                    System.out.println("==================================================");
                    System.out.println("Menu de Administrador - Visualizacion del primer ticket");
                    System.out.println("==================================================");
                    Ticket frente = pendientes.verFrente();
                    if (frente != null) {
                        System.out.println(frente);
                    }
                    pausa();
                } else if (seleccion == 2) {
                    Ticket resuelto = pendientes.eliminar();
                    if (resuelto != null) {
                        resuelto.setFechaResolucion(LocalDate.now());
                        resueltos.insertarInicio(resuelto);
                        System.out.println("Ticket resuelto:\n" + resuelto);
                    }
                    pausa();
                } else {
                    System.out.println("Seleccion invalida, intente de nuevo.");
                    pausa();
                    continue;
                }
            } else if (seleccion == 2) { //Menu de usuario
                System.out.println("==================================================");
                System.out.println("Bienvenido al menu de tickets (Usuario)");
                System.out.println("==================================================");
                System.out.println("     1. Crear un ticket.");
                System.out.println("     2. Buscar un ticket resuelto."); // Si no esta resuelto o no se ha creado, da un mensaje simbolico
                System.out.println("--------------------------------------------------");
                System.out.print("Seleccione una opcion: ");
                seleccion = leerEntero();
                if (seleccion == 1) {
                    System.out.println("==================================================");
                    System.out.println("Menu de Usuario - Creacion de Tickets");
                    System.out.println("==================================================");
                    System.out.print("Descripcion: ");
                    String descripcion = entrada.nextLine();
                    System.out.print("Nombre completo: ");
                    String nombreCompleto = entrada.nextLine();
                    System.out.print("Prioridad \n1 = Baja \n2 = Media \n3 = Alta \nIndique el nivel de prioridad: ");
                    int prioridad = leerEntero();
                    if (prioridad < 1 || prioridad > 3) {
                        System.out.println("Numero de prioridad invalida, intente de nuevo . . .");
                        pausa();
                        continue;
                    }
                    // Creacion del ticket
                    Ticket ticket = new Ticket(descripcion, nombreCompleto, prioridad);
                    boolean insertado = pendientes.insertar(ticket);
                    if (insertado) {
                        System.out.println("Ticket creado exitosamente. \nID: " + ticket.getId());
                    }
                    pausa();
                } else if (seleccion == 2) {
                    System.out.println("==================================================");
                    System.out.println("Menu de Usuario - Busqueda de tickets");
                    System.out.println("==================================================");
                    System.out.print("ID del ticket: ");
                    int id = leerEntero();
                    Ticket encontrado = resueltos.buscar(id);
                    if (encontrado != null) {
                        System.out.println(encontrado);
                    }
                    pausa();
                } else {
                    System.out.println("Seleccion invalida, intente de nuevo.");
                    pausa();
                    continue;
                }
            } else if (seleccion == 3) { //Sale del programa con la opcion 3, rompe el ciclo
                break;
            } else {
                System.out.println("La seleccion es invalida, intente de nuevo (1 / 2 / 3)");
                pausa();
                continue;
            }
        }
        entrada.close(); //Cierra el scanner, buena practica
    }

    //Rutina main
    public static void main(String[] args){
        //Crea una variable de tipo Main para llamar al menu
        Main menu = new Main();
        menu.menu();
    }
}
