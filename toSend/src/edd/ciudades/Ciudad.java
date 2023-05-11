
package edd.ciudades;

public class Ciudad implements Comparable<Ciudad> {

    private String nombre;
    private String estado;
    private int x;
    private int y;

    public Ciudad(String ciudad) {
        String[] a = ciudad.split("\\s+");
        this.nombre = a[0];
        this.estado = a[1];
        this.x = Integer.valueOf(a[2]);
        this.y = Integer.valueOf(a[3]);
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int compareTo(Ciudad c) {
        return (estado + nombre).compareTo(c.estado + c.nombre);
    }

    public boolean sameEstado(String estado) {
        return this.estado.equals(estado);
    }

    public boolean equals(Object obj) {
        Ciudad c;

        if (obj == null) return false;
        if (!(obj instanceof Ciudad)) return false;
        c = (Ciudad)obj;
        if (!this.nombre.equals(c.nombre)) return false;
        if (!this.estado.equals(c.estado)) return false;
        if (this.x != c.x) return false;
        if (this.y != c.y) return false;

        return true;
    }

    public String toString() {
        return nombre + " " + estado + " " + x + " " + y;
    }
}
