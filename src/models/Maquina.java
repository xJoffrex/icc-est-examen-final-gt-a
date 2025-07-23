package models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Maquina {
   private String nombre;
   private String ip;
   private List<Integer> codigos;
   private int subred;
   private int riesgo;

   public Maquina(String variable1, String variable2, List<Integer> variable3) {
      this.nombre = variable1;
      this.ip = variable2;
      this.codigos = variable3;
      this.riesgo = this.calcularRiesgo();
      this.subred = this.calcularSubred();
   }

   private int calcularSubred() {
      String[] variable1 = this.ip.split("\\.");
      return Integer.parseInt(variable1[3]);
   }

   private int calcularRiesgo() {
      int variable1 = 0;
      Iterator variable2 = this.codigos.iterator();

      while(variable2.hasNext()) {
         int variable3 = (Integer)variable2.next();
         if (variable3 % 3 == 0) {
            variable1 += variable3;
         }
      }

      HashSet hashs = new HashSet();
      char[] caracter = this.nombre.toCharArray();
      int val = caracter.length;

      for(int valores = 0; valores < val; ++valores) {
         char caracg = caracter[valores];
         if (caracg != ' ') {
            hashs.add(caracg);
         }
      }

      return variable1 * hashs.size();
   }

   public String getNombre() {
      return this.nombre;
   }

   public String getIp() {
      return this.ip;
   }

   public List<Integer> getCodigos() {
      return this.codigos;
   }

   public int getSubred() {
      return this.subred;
   }

   public int getRiesgo() {
      return this.riesgo;
   }

   public String toString() {
      return this.nombre + " - Subred: " + this.subred + " - Riesgo: " + this.riesgo;
   }

   public boolean equals(Object variable1) {
      if (this == variable1) {
         return true;
      } else if (!(variable1 instanceof Maquina)) {
         return false;
      } else {
         Maquina variable2 = (Maquina)variable1;
         return this.subred == variable2.subred && Objects.equals(this.nombre, variable2.nombre);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.nombre, this.subred});
   }
}
