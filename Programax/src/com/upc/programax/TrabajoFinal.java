package com.upc.programax;

import java.util.Scanner;

public class TrabajoFinal {
    static String[] articulos ={"lapiceros", "papel", "archivadores", "resaltadores", "grapas", "folder"};
    static String[] empleados ={"Bryan Condor","Mijhael Cueto", "Diego Morales", "Jorge Tello"};
    static int[] stockMinimo ={3,2,2,3,2,3};

    public static int[] modificarStock (String articulo, int cantidad, int[] stock){
        int[] nuevoStock = new int[articulos.length];
        for (int i=0;i< articulos.length;i++){
            nuevoStock[i]=stock[i];
            if (articulos[i].equals(articulo)) {
                nuevoStock[i]-=cantidad;
            }
        }
        return nuevoStock;
    }
    public static int[][] registrarArtPorUsuario (String nombre, String articulo, int cantidad){
        int[][] consumos = new int[empleados.length][articulos.length];
        for (int i=0;i< empleados.length;i++){
            for (int j=0;j< articulos.length;j++){
                if (empleados[i].equals(nombre) && articulos[j].equals(articulo)){
                    consumos[i][j]+=cantidad;
                }
            }
        }
        return consumos;
    }
    public static int[] verificarStockMinimo (int[] stock,String articulo){
        int[] alerta=new int[6];
        int[] nuevoStock = new int[6];
        for (int i=0;i< articulos.length;i++){
            nuevoStock[i]=stock[i];
            if (nuevoStock[i] <= stockMinimo[i]){
                System.out.println(articulo +" bajo en stock. Realizar nuevo pedido");
                alerta[i]++;
            }
        }
        return alerta;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] stock ={20,10,10,10,5,10}; //stock inicial
        int[] alertaStockMinimo;
        int[] comprasTotal = new int[6];
        int[][] consumosPorUsuario = new int[empleados.length][articulos.length];
        String nuevaSolicitud;
        do {
            String nombreU, tipoArt;
            int cantArtxUsar, cantArtxComprar;
            System.out.println("Ingresar nombre de usuario solicitante");
            nombreU = sc.nextLine();
            System.out.println("Ingresar articulo a solicitar");
            tipoArt = sc.nextLine();
            System.out.println("Ingresar cantidad del carticulo por solicitar");
            cantArtxUsar = sc.nextInt();
            sc.nextLine();
            int[] nuevoStock = modificarStock(tipoArt,cantArtxUsar,stock);
            int[][] nuevoConsumo = registrarArtPorUsuario(nombreU,tipoArt,cantArtxUsar);
            for (int i=0;i< empleados.length;i++){
                for (int j=0;j< articulos.length;j++){
                stock[j]=nuevoStock[j];
                consumosPorUsuario[i][j]+=nuevoConsumo[i][j];
                }
            }
            alertaStockMinimo =verificarStockMinimo(stock, tipoArt);
            for (int i=0;i<articulos.length;i++){
                if (alertaStockMinimo[i]==1){
                    System.out.println("Ingrese cantidad de "+tipoArt+" por comprar");
                    cantArtxComprar= sc.nextInt();
                    sc.nextLine();
                    stock[i] += cantArtxComprar;
                    comprasTotal[i] += cantArtxComprar;
                }
            }
            System.out.println("Realizar nueva solicitud?");
            nuevaSolicitud = sc.nextLine();

        }while (nuevaSolicitud.equals("si"));
        String reporte;
        System.out.println("Elegir reporte: consumo o compras");
        reporte= sc.nextLine();
        if (reporte.equals("consumo")){
            System.out.println("Reporte de consumo de articulos de oficina");
            for (int i=0;i< empleados.length;i++){
                for (int j=0;j< articulos.length;j++){
                    System.out.println(empleados[i]+":\n"+articulos[j] +": " +consumosPorUsuario[i][j] +"\n");
                }
            }
        }else if (reporte.equals("compras")){
            System.out.println("Reporte de compras");
            for (int i=0;i< articulos.length;i++){
                System.out.println(articulos[i] +": "+comprasTotal[i] +"\n");
            }
        }
    }
}
