/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab7p1_josealvarez;
import java.util.Scanner;
import java.util.Random;


public class Lab7P1_JoseAlvarez {
    static Scanner leer = new Scanner(System.in);
    static Random random = new Random();
    
    
        public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 3) {
            mostrarMenu();
            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    jugarTresEnRaya();
                    break;
                case 2:
                    ejecutarPuntosSilla();
                    break;
               
                
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println(" ");
        System.out.println("1. Tres en raya");
        System.out.println("2. Puntos de silla");
        System.out.println("3. Salir");
        System.out.println("Selecciona una opción: ");
    }
        
     public static void jugarTresEnRaya() {
        char tablero[][] = generarTablero();
        boolean juegoActivo = true;

        while (juegoActivo) {
            mostrarTablero(tablero);
            solicitarMovimientosUsuario(tablero, leer);

            if (verificarVictoria(tablero, 'X')) {
                mostrarTablero(tablero);
                System.out.println("Felicidades, ha ganado");
                juegoActivo = preguntarNuevaPartida(leer);

            } else if (esEmpate(tablero)) {
                mostrarTablero(tablero);
                System.out.println(" Es un empate");
                juegoActivo = preguntarNuevaPartida(leer);

            } else {
                realizarMovimientoMaquina(tablero);
                if (verificarVictoria(tablero, 'O')) {
                    mostrarTablero(tablero);
                    System.out.println("Ha perdido");
                    juegoActivo = preguntarNuevaPartida(leer);
                }
            }
        }
     }// fin del metodo jugartresenraya
     
     
        
        
        public static char [][] generarTablero(){
            char [][] tablero = new char [3][3];
            for(int i =0; i< 3; i++){
                for(int j =0; j< 3; j++){
                    tablero [i][j]= ' ';
                }
            }
            return tablero;
        }// fin del metodo char
        
        public static void mostrarTablero(char [][] tablero){
            System.out.println(" ");
            for(int i = 0; i < 3; i++){
                System.out.print("[ ");
                for(int j = 0; j < 3; j++){
                    System.out.print(tablero [i][j] + " ] ");
                }
                System.out.println();
                System.out.println(" ");
            }
        }// fin metodo mostrar tablero
        
        public static void solicitarMovimientosUsuario(char [][] tablero, Scanner leer){
            int fila;
            int columna;
            
            System.out.println("Ingrese la fila 0,1,2: ");
            fila = leer.nextInt();
            
            System.out.println("Ingrese la columna 0,1,2: ");
            columna = leer.nextInt();
            
            while(!verificarPosicionValida(tablero, fila, columna)){
                System.out.println("El movimiento no es valido. Ingrese 0, 1,2: ");
                fila = leer.nextInt();
                
                System.out.println("El movimiento no es valido. Ingrese 0, 1,2: ");
                columna = leer.nextInt();

            }
            tablero[fila][columna]= 'X';
        }
        
        public static boolean verificarPosicionValida (char [][] tablero, int fila, int columna){
            return(fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero [fila][columna]== ' ');
        }
        
        public static void realizarMovimientoMaquina(char [][] tablero){
            int fila;
            int columna;
            
            boolean posicionValida = false;
            while(!posicionValida){
                fila = random.nextInt(3);
                columna = random.nextInt(3);
                
                if(verificarPosicionValida(tablero, fila, columna)){
                    tablero[fila][columna] = 'O';
                    posicionValida = true;
                }
            }
        }
        
        public static boolean verificarVictoria(char[][] tablero, char jugador) {
    
            for (int i = 0; i < 3; i++) {
                if ((tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) ||
                (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador)) {
                return true;
                }
            }

    
            if ((tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) ||
            (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador)) {
            return true;
            }

    return false;
    }

    public static boolean esEmpate(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                return false; 
                }
            }
        }

    return true;
    }
    
        public static boolean preguntarNuevaPartida(Scanner scanner) {
            System.out.println("¿Quieres jugar otra partida? (1: Sí, 0: No): ");
        return scanner.nextInt() == 1;
        }
        
        public static void ejecutarPuntosSilla() {
        System.out.println("Ingrese el número de filas para la matriz:");
        int filas = leer.nextInt();

        System.out.println("Ingrese el número de columnas para la matriz:");
        int columnas = leer.nextInt();

        int[][] matriz = generarIntMatrizAleatoria(filas, columnas);

        System.out.println("Matriz generada:");
        mostrarMatriz(matriz);

        encontrarPuntosSilla(matriz);
        
       
        }
        
        public static int[][] generarIntMatrizAleatoria(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(100); 
            }
        }

        return matriz;
    }

    public static void mostrarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
            System.out.print(matriz[i][j] + " ");
            }
        System.out.println();
        }
    }

    public static void encontrarPuntosSilla(int[][] matriz) {
        boolean hayPuntosSilla = false;

        for (int i = 0; i < matriz.length; i++) {
            int minimoFila = matriz[i][0];
            int columnaMinimo = 0;

            for (int j = 1; j < matriz[0].length; j++) {
                if (matriz[i][j] < minimoFila) {
                    minimoFila = matriz[i][j];
                    columnaMinimo = j;
                }
            }

            boolean esPuntoSilla = true;
            for (int k = 0; k < matriz.length; k++) {
                if (matriz[k][columnaMinimo] > minimoFila) {
                    esPuntoSilla = false;
                    break;
                }
            }

            if (esPuntoSilla) {
                System.out.println("Punto de silla encontrado en la posición (" + i + ", " + columnaMinimo + ") con valor " + minimoFila);
                hayPuntosSilla = true;
            }
        }

        if (!hayPuntosSilla) {
            System.out.println("No se encontraron puntos de silla en la matriz.");
        }
    }
    
 
}
