package edd;

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

    public static String getLine(String msg) {
        Scanner scn = new Scanner(System.in);

        Colors.println(msg, Colors.HIGH_INTENSITY);

        return scn.next();
    }

    public static void main(String[] args) throws IOException {
        Colors.println(Colors.HIGH_INTENSITY + Colors.BLUE, "Este es un programa sobre un buscador de ciudades");

        try {
            ReaderWriter.readLines("toSend/ciudades.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        int opcion = 0, coordenadaX = 0, coordenadaY = 0;
        String nombreCiudad = "", estadoCiudad = "";
        Boolean b = true;
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
                    String s  = nombreCiudad + " " +  estadoCiudad + " " + Integer.toString(coordenadaX) + " " + Integer.toString(coordenadaY);
                    Ciudad c = new Ciudad(s);
                   System.out.println(c.toString());
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("\n" + "Regrese pronto" + "\n");
                    break;
                default:
                    System.out.println("Ingresa una opcion valida.");
                    break;
            }
        } while (opcion != 5);

    }
}