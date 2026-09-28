//Lista donde se guardan los tickets ya resueltos

public class ListaEnlazadaSimpleTicket {

    // Atributos
    private Ticket primero;

    //METODOS
    //Constructor
    public ListaEnlazadaSimpleTicket() {
        primero = null;
    }

    // Getters
    private Ticket getPrimero() {
        return primero;
    }

    // Setters
    private void setPrimero(Ticket primero) {
        this.primero = primero;
    }

    // Operaciones
    private boolean estaVacia() {
        return primero == null;
    }

    //Se inserta un ticket resuelto al inicio
    public void insertarInicio(Ticket ticket) {
        ticket.setSiguiente(primero);
        setPrimero(ticket);
    }

    //Se busca un ticket resuelto
    public Ticket buscar(int id) {
        Ticket temporal = primero;
        while (temporal != null) {
            if (id == (temporal.getId())) return temporal;
            temporal = temporal.getSiguiente();
        }
        System.out.println("El ticket esta pendiente de resolucion o creacion.\n");
        return null;
    }

    //se inserta un ticket al final
    public void insertarFin(Ticket ticket) {
        //Si esta vacia, se pone el ticket al inicio
        if (estaVacia()) {
            setPrimero(ticket);
            return;
        }
        Ticket temporal = primero;
        while (temporal.getSiguiente() != null) {
            temporal = temporal.getSiguiente();
        }
        temporal.setSiguiente(ticket);
    }

    //muestra la lista de tickets
    public void mostrarLista() {
        //Si esta vacia, se da un mensaje explicativo y se retorna prematuramente
        if (estaVacia()) {
            System.out.println("La lista esta vacia.\n");
            return;
        }
        Ticket temporal = primero;
        while (temporal != null) {
            System.out.println(temporal);
            temporal = temporal.getSiguiente();
        }
        //Se recorre de inicio a fin, imprimiendo ccada ticket
    }

    //Elimina un ticket de los resueltos
    public Ticket eliminar(int id) {
        //Si esta vacia, se da un mensaje explicativo y se retorna un null simbolico
        if (estaVacia()) {
            System.out.println("La lista está vacía.\n");
            return null;
        }
        Ticket anterior = primero;
        Ticket temporal = anterior;
        while (temporal != null) {
            if (id == (temporal.getId())) break;
            anterior = temporal;
            temporal = temporal.getSiguiente();
        }
        if (temporal != null) {
            if (temporal == primero) setPrimero(temporal.getSiguiente());
            else anterior.setSiguiente(temporal.getSiguiente());
            return temporal;
        } else {
            System.out.println("El ticket esta pendiente (resolucion o creacion).\n");
            return null;
        }

        //Si se salio del ciclo porque se encontro el dato, entonces se elimina y se retorna el temporal
        //Si se salio del cilo porque se encontro null, se da un mensaje explicativo y se retorna un null simbolico
    }
}


