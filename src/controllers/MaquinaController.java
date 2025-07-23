package controllers;
import models.Maquina;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class MaquinaController {
    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral){
        Stack<Maquina> pila = new Stack<>();
        for (Maquina m : maquinas){
            if(m.getSubred() > umbral){
                pila.push(m);
            }

        }
        return pila;
    }


    public TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila){
        return  new TreeSet<Maquina>((m1, m2) -> {
            if (m1.getSubred() != m2.getSubred()){
                return Integer.compare(m2.getSubred(), m1.getSubred());
            }else{
                return m1.getNombre().compareTo(m2.getNombre());
            }
            
        }){{
            addAll(pila);
        }}; 
    }

    public TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas){
        TreeMap<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina m : maquinas){
            int riesgo = m.getRiesgo();
            mapa.putIfAbsent(riesgo, new LinkedList<>());
            mapa.get(riesgo).add(m);
        }
        return mapa;
    }



    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa){
        int maxRiesgo = -1;
        int maxTam = -1;

        for(Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()){
            int tam = entry.getValue().size();
            int riesgo = entry.getKey();
            
            if(tam > maxTam || tam == maxTam && riesgo > maxRiesgo){
                maxTam = tam;
                maxRiesgo = riesgo;
            }

        }
        Stack<Maquina> resultado = new Stack<>();
        if(mapa.containsKey(maxRiesgo)){
            for (Maquina m : mapa.get(maxRiesgo)){
                resultado.push(m);
            }
        }
        return resultado;
    }
        
}

