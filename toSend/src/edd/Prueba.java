package edd;

import edd.ciudades.Buscador;
// import edd.ciudades.Buscador;
import edd.ciudades.Ciudad;

import edd.estructuras.arboles.ArbolBinario;
import edd.readerwriter.ReaderWriter;
import edd.colors.Colors;

import java.io.IOException;
import java.util.Scanner;

public class Prueba {

    public static int getInt(String mensaje, String error, int min, int max) {
        int val;
        Scanner scn = new Scanner(System.in);

        while (true) {
            Colors.println(mensaje, Colors.HIGH_INTENSITY);
            if (scn.hasNextInt()) {
                val = scn.nextInt();
                // (-infinito, min) || (max, infinito)
                if (val < min || max < val) {
                    Colors.println(error, Colors.RED + Colors.HIGH_INTENSITY);
                } else {
                    return val;
                }
            } else {
                scn.next();
                Colors.println(error, Colors.RED + Colors.HIGH_INTENSITY);
            }
        }
    }

    public static String itemize(final Iterable it) {
        int i = 1;
        final StringBuilder sb = new StringBuilder();
        sb.append("Selecciona una opcion.\n");
        for (final Object obj : it) {
            sb.append(i++);
            sb.append(".");
            sb.append(obj.toString());
            sb.append(".\n");
        }
        sb.append("0. Salir\n");
        return sb.toString();
    }

    public static String getLine(String msg) {
        Scanner scn = new Scanner(System.in);

        Colors.println(msg, Colors.HIGH_INTENSITY);

        return scn.next();
    }

    public static void println(final String msg, final Iterable it) {
        int i = 1;
        final StringBuilder sb = new StringBuilder();
        Colors.println(msg, "\u001b[1m");
        for (final Object obj : it) {
            sb.append(i++);
            sb.append(". ");
            sb.append(obj.toString());
            sb.append(".\n");
        }
        Colors.println(sb.toString(), "\u001b[0;36m\u001b[1m");
    }

    public static void main(String[] args) throws IOException {
        Colors.println(Colors.HIGH_INTENSITY + Colors.BLUE, "Este es un programa sobre un buscador de ciudades");

        Scanner sc = new Scanner(System.in);
        int opcion = 0, coordenadaX = 0, coordenadaY = 0;
        String nombreCiudad = "", estadoCiudad = "";
        Boolean b = true;
        Buscador buscador = new Buscador();
        do {
            System.out.println("Selecciona una opcion");
            System.out.println("1. Agregar una ciudad al directorio");
            System.out.println("2. Eliminar una ciudad del directorio");
            System.out.println("3. Determinar todas las ciudaddes dentro de un estado");
            System.out.println("4. Determinar toda las ciudades denntro de un rango de coordenadas");
            System.out.println("5. Imprimir todas las ciudades");
            System.out.println("0. Salir");

            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                Colors.println(Colors.RED + Colors.HIGH_INTENSITY, "No puedes introducir letras intentalo de nuevo");
                sc.nextLine();
            }

            switch (opcion) {
                case 0:
                    System.out.println("\n" + "Regrese pronto" + "\n");
                    break;
                case 1:
                    System.out.println("¿Cual es el nombre de la ciudad?");
                    nombreCiudad = sc.next();

                    System.out.println("¿Cual es el estado de la ciudad?");
                    estadoCiudad = sc.next();
                    do {
                        System.out.println("¿Cual es su coordenada x?");
                        try {
                            coordenadaX = sc.nextInt();
                            b = false;
                        } catch (Exception e) {
                            Colors.println(Colors.RED + Colors.HIGH_INTENSITY,
                                    "No puedes introducir letras intentalo de nuevo");
                            sc.nextLine();
                        }
                    } while (b);
                    b = true;
                    do {
                        System.out.println("¿Cual es su coordenada Y");
                        try {
                            coordenadaY = sc.nextInt();
                            b = false;
                        } catch (Exception e) {
                            Colors.println(Colors.RED + Colors.HIGH_INTENSITY,
                                    "No puedes introducir letras intentalo de nuevo");
                            sc.nextLine();
                        }
                    } while (b);
                    String s = nombreCiudad + " " + estadoCiudad + " " + Integer.toString(coordenadaX) + " "
                            + Integer.toString(coordenadaY);
                    Ciudad c = new Ciudad(s);
                    buscador.add(c);
                    continue;

                case 2:
                    ArbolBinario<?> tree = buscador.getCiudades();
                    opcion = getInt(itemize(tree), "Por favor ingresa una opcion valida.", 0, tree.size());
                    if (opcion != 0) {
                        buscador.borrar(opcion - 1);
                        continue;
                    }
                    opcion = -1;
                    continue;

                case 3:
                    tree = buscador.getEstados();
                    opcion = getInt(itemize(tree), "Porfavor ingresa una opcion valida", 0, tree.size());
                    if (opcion != 0) {
                        tree = buscador.searchEstados(opcion - 1);
                        println("Las ciudades encontradas son: ", tree);
                        continue;
                    }
                    opcion = -1;
                    continue;
                case 4:
                    final int minX = buscador.minX();
                    final int minY = buscador.minY();
                    final int maxX = buscador.maxX();
                    final int maxY = buscador.maxY();
                    System.out.println("Ingresa la coordenada x1");
                    int x1 = sc.nextInt();
                    System.out.println("Ingresa la coordenada x2");
                    int x2 = sc.nextInt();
                    System.out.println("Ingresa la coordenada y1");
                    int y1 = sc.nextInt();

                    System.out.println("Ingresa la coordenada y2");
                    int y2 = sc.nextInt();

                    tree = buscador.searchCoordenadas(x1, x2, y1, y2);
                    println("Las ciudades encontradas son: ", tree);
                    continue;
                case 5:
                    println("Las ciudades dentro del directorio son: ", buscador.getCiudades());
                    continue;
                default:
                    System.out.println("Ingresa una opcion valida.");
                    break;
            }
        } while (opcion != 0);

    }
}