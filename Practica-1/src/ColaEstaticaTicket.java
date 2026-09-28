//Se utiliza una cola estatica para que tenga parametros de llenado, asi se puede comprobar
//si la lista esta llena

public class ColaEstaticaTicket {
    //Atributos
    private Ticket[] cola;
    private int frente;
    private int fin;
    private int cantidad;

    //Metodos
    //Constructor - construye una lista con lngitud especifica
    public ColaEstaticaTicket(int longitud) {
        cola = new Ticket[longitud];
        frente = cantidad = 0;
        fin = -1;
    }

    //Operaciones - permiten saber si esta llena/vacia
    private boolean estaVacia() {
        return cantidad == 0;
    }
    private boolean estaLlena() {
        return cantidad == cola.length;
    }

    //Inserta un ticket si tiene campo, de lo contrario muestra un mensaje de error
    public boolean insertar(Ticket nodo) {
        if (estaLlena()) {
            System.out.println("La cola esta llena.\n");
            return false;
        }
        int posicion = 0; // 0 = frente de la cola, donde colocara el ticket con mas prioridad
        while (posicion < cantidad) {
            int indiceReal = (frente + posicion) % cola.length;
            if (nodo.getPrioridad() > cola[indiceReal].getPrioridad()) {
                break;
            }
            posicion++;
        }
        for (int i = cantidad; i > posicion; i--) {
            int destino = (frente + i) % cola.length;
            int origen = (frente + i - 1) % cola.length;
            cola[destino] = cola[origen];
        }
        int indiceInsertar = (frente + posicion) % cola.length;
        cola[indiceInsertar] = nodo;

        fin = (frente + cantidad) % cola.length;
        cantidad++;
        return true;
    }

    //Elimina un ticket de la lista (cuando se marca como resuelto)
    public Ticket eliminar() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia.\n");
            return null;
        }
        Ticket nodo = cola[frente++];
        if (frente == cola.length) {
            frente = 0;
        }
        cantidad--;
        return nodo;
    }
    //Funcion que permite ver el frente de la lista, o sea, el primer ticket o el que tiene mas prioridad
    public Ticket verFrente() {
        if (estaVacia()) {
            System.out.println("La cola esta vacia.\n");
            return null;
        }
        return cola[frente];
    }
}
