import java.time.LocalDate;
//Clase ticket con sus atributos y metodos
public class Ticket {
    private static int cantidad = 0;

    //Atributos del ticket
    private int id;
    private String descripcion; //
    private String nombreCompleto; // Del usuario que lo creo
    private int prioridad;
    private LocalDate fechaCreacion; // Fecha de la creacion del ticket
    private LocalDate fechaResolucion; // Empieza en null
    private Ticket siguiente;

    //METODOS
    //Constructor - construye un ticket con los parametros dados, los que no se colocan solos
    public Ticket( String descripcion, String nombreCompleto, int prioridad) { //El id, y las fechas no se reciben, por lo que no se utilizan como parametros
        this.id = cantidad;
        cantidad++;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.prioridad = prioridad;
        this.fechaCreacion = LocalDate.now();
        this.fechaResolucion = null;
    }

    //Getter - actua como un mesero si se necesita saber el dato de algun atributo en especifico
    public int getId(){return id;}
    public String getDescripcion() {return descripcion;}
    public String getNombreCompleto() {return nombreCompleto;}
    public int getPrioridad() {return prioridad; }
    public LocalDate getFechaCreacion() {return fechaCreacion;}
    public LocalDate getFechaResolucion() {return fechaResolucion;}
    public Ticket getSiguiente() {return siguiente;}

    //Setter - Modifica un atributo si asi se desea, sin nombre y descripcion porque no se deben modificar
    public void setFechaResolucion(LocalDate fechaResolucion) {this.fechaResolucion = fechaResolucion; }
    public void setSiguiente(Ticket siguiente) {this.siguiente = siguiente; }


    //toString() - convierte los datos en un aunica linea para posteriormente presentarla al usuario.
    @Override
    public String toString() {
        String prioridadTexto = ""; //Convierte la prioridad en numero, a texto para que el usuario sepa cual escoger y no lo haga a ciegas.
        if (getPrioridad() == 1) {
            prioridadTexto = "Baja";
        } else if (getPrioridad() == 2) {
            prioridadTexto = "Media";
        } else if (getPrioridad() == 3) {
            prioridadTexto = "Alta";
        }
        return "\nID: " + id + "\nDescripcion: " + descripcion + "\nNombre completo: " + nombreCompleto + "\nPrioridad: " + prioridadTexto + " (" + prioridad + ")" + "\nFecha de creacion: " + fechaCreacion + "\nFecha de resolucion: " + fechaResolucion + "\n";
    }
}
