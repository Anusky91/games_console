package games;

import java.util.ArrayList;

public class TorreDeHanoi {

    /**
     * Regrlas del juego:
     * 🧠 Reglas
     * 1. Solo puedes mover un disco a la vez.
     * 2. Solo puedes mover el disco que está arriba de su torre.
     * 3. No puedes poner un disco más grande encima de uno más pequeño.
     */

    private String logo = """
            ·······································································
            :{___ {______                                            {__          :
            :     {__                                                {__          :
            :     {__       {__    {_ {___{_ {___   {__              {__   {__    :
            :     {__     {__  {__  {__    {__    {_   {__       {__ {__ {_   {__ :
            :     {__    {__    {__ {__    {__   {_____ {__     {_   {__{_____ {__:
            :     {__     {__  {__  {__    {__   {_             {_   {__{_        :
            :     {__       {__    {___   {___     {____         {__ {__  {____   :
            :                                                                     :
            :              {__     {__                                            :
            :              {__     {__                              {_            :
            :              {__     {__   {__    {__ {__     {__                   :
            :              {______ {__ {__  {__  {__  {__ {__  {__ {__            :
            :              {__     {__{__   {__  {__  {__{__    {__{__            :
            :              {__     {__{__   {__  {__  {__ {__  {__ {__            :
            :              {__     {__  {__ {___{___  {__   {__    {__            :
            ·······································································
            """;

    private int disksNumber;
    private int movimientos;
    private Torre origen;
    private Torre destino;
    private Torre auxiliar;
    public TorreDeHanoi(int disksNumber) {
        this.movimientos = 0;
        this.disksNumber = disksNumber;
        this.origen = new Torre("Origen", disksNumber);
        this.destino = new Torre("Destino", 0);
        this.auxiliar = new Torre("Auxiliar", 0);
    }

    public void start() {
        System.out.println("\nEstado inicia:");
        System.out.println("Torre origen:" + origen.discos);
        System.out.println("Torre destino:" + destino.discos);
        System.out.println("Torre auxiliar:" + auxiliar.discos);
        moverTorre(disksNumber, origen, destino, auxiliar);
        System.out.println("\nEstado actual:");
        System.out.println("Torre origen:" + origen.discos);
        System.out.println("Torre destino:" + destino.discos);
        System.out.println("Torre auxiliar:" + auxiliar.discos);
        System.out.println("\nMovimientos totales: " + movimientos);
    }


    public void moverTorre(int n, Torre origen, Torre destino, Torre auxiliar) {

        if (n == 1) {
            movimientos++;
            System.out.println("Mover disco " + n + " de " + origen.nombre + " a " + destino.nombre);
            origen.discos.remove("Disco " + n);
            destino.discos.add("Disco " + n);

        } else {
            moverTorre(n-1, origen, auxiliar, destino);
            movimientos++;
            System.out.println("Mover disco " + n + " de " + origen.nombre + " a " + destino.nombre);
            origen.discos.remove("Disco " + n);
            destino.discos.add("Disco " + n);
            moverTorre(n-1, auxiliar, destino, origen);
        }

    }

    public class Torre {
        String nombre;
        ArrayList<String> discos = new ArrayList<>();
        public Torre (String nombre, int discos) {
            this.nombre = nombre;
            this.discos = crearDiscos(discos);
        }
        private ArrayList<String> crearDiscos(int discos) {
            ArrayList<String> returnDiscos = new ArrayList<>();
            for (int i = discos; i > 0 ; i--) {
                returnDiscos.add("Disco " + i);
            }
            return returnDiscos;
        }
    }

}
