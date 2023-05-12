package edd.ciudades;

import java.util.Iterator;
import edd.estructuras.lineales.List;
import java.io.IOException;
import edd.readerwriter.ReaderWriter;
import edd.estructuras.arboles.ArbolBinario;

public class Buscador {
    private static String fileName;
    private ArbolBinario<Ciudad> ciudades;
    private ArbolBinario<String> estados;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    public Buscador() {
        this.ciudades = new ArbolBinario<Ciudad>();
        this.estados = new ArbolBinario<String>();
        final int n = Integer.MAX_VALUE;
        this.minY = n;
        this.minX = n;
        final int n2 = Integer.MIN_VALUE;
        this.maxY = n2;
        this.maxX = n2;
        this.load(Buscador.fileName);
    }

    private void load(final String c) {
        try {
            final List<String> l = ReaderWriter.readLines(c);
            for (final String s : l) {
                this.add(new Ciudad(s), false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Ciudad c) {
        this.add(c, true);
    }

    private void add(final Ciudad c, final boolean addToFile) {
        if (!this.ciudades.contains(c)) {
            this.ciudades.add(c);
            final String estado = c.getEstado();
            if (!this.estados.contains(estado)) {
                this.estados.add(estado);
            }
            final int x = c.getX();
            if (this.minX > x) {
                this.minX = x;
            }
            if (this.maxX < x) {
                this.maxX = x;
            }
            final int y = c.getY();
            if (this.minY > y) {
                this.minY = y;
            }
            if (this.maxY < y) {
                this.maxY = y;
            }
            if (addToFile) {
                try {
                    ReaderWriter.writeLines(Buscador.fileName, this.ciudades);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void borrar(final int opcion) {
        int i = 0;
        for (final Ciudad c : this.ciudades) {
            if (i == opcion) {
                this.ciudades.remove(c);
                break;
            }
            ++i;
        }
        try {
            ReaderWriter.writeLines(Buscador.fileName, this.ciudades);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArbolBinario<Ciudad> searchEstados(final int opcion) {
        int i = 0;
        boolean flag = false;
        final ArbolBinario<Ciudad> a = new ArbolBinario<Ciudad>();
        for (final String e : this.estados) {
            if (i == opcion) {
                for (final Ciudad c : this.ciudades) {
                    if (c.sameEstado(e)) {
                        a.add(c);
                        flag = true;
                    } else {
                        if (flag) {
                            break;
                        }
                        continue;
                    }
                }
            }
            ++i;
        }
        return a;
    }

    public ArbolBinario<Ciudad> searchCoordenadas(final int x1, final int x2, final int y1, final int y2) {
        final ArbolBinario<Ciudad> a = new ArbolBinario<Ciudad>();
        for (final Ciudad c : this.ciudades) {
            final int x3 = c.getX();
            final int y3 = c.getY();
            if (x1 <= x3 && x3 <= x2 && y1 <= y3 && y3 <= y2) {
                a.add(c);
            }
        }
        return a;
    }

    public ArbolBinario<Ciudad> getCiudades() {
        return this.ciudades;
    }

    public ArbolBinario<String> getEstados() {
        return this.estados;
    }

    public int minX() {
        return this.minX;
    }

    public int maxX() {
        return this.maxX;
    }

    public int minY() {
        return this.minY;
    }

    public int maxY() {
        return this.maxY;
    }

    static {
        Buscador.fileName = "toSend/ciudades.txt";

    }
}
