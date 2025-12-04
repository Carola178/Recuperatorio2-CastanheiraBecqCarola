package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Inventario<T extends CSVConvertible> implements Almacenable<T> {

    private ArrayList<T> lista;

    public Inventario() {
        this.lista = new ArrayList<>();
    }


    @Override
    public void agregar(T elemento) {
        lista.add(elemento);
    }

    @Override
    public void eliminarSegun(Predicate<T> criterio) {
        Iterator<T> it = lista.iterator();
        while (it.hasNext()) {
            if (criterio.test(it.next())) {
                it.remove();
            }
        }
    }

    @Override
    public List<T> obtenerTodos() {
        return new ArrayList<>(lista);
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        for (T elem : lista) {
            if (criterio.test(elem)) {
                return elem;
            }
        }
        return null;
    }

    @Override
    public void ordenar() {
        Collections.sort((List) lista);
    }

    @Override
    public void ordenar(Comparator<T> comparador) {
        lista.sort(comparador);
    }

    @Override
    public List<T> filtrar(Predicate<T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (T elem : lista) {
            if (criterio.test(elem)) {
                resultado.add(elem);
            }
        }
        return resultado;
    }

    @Override
    public List<T> transformar(Function<T, T> operador) {
        List<T> resultado = new ArrayList<>();
        for (T elem : lista) {
            resultado.add(operador.apply(elem));
        }
        return resultado;
    }

    @Override
    public int contar(Predicate<T> criterio) {
        int contador = 0;
        for (T elem : lista) {
            if (criterio.test(elem)) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public void guardarEnBinario(String ruta) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            out.writeObject(lista);
        }
    }

    @Override
    public void cargarDesdeBinario(String ruta) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            lista = (ArrayList<T>) in.readObject();
        }
    }

    @Override
    public void guardarEnCSV(String ruta) throws Exception {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta))) {

            escritor.write(DispositivoDomotico.toHeader());
            escritor.newLine();

            for (T elem : lista) {
                escritor.write(elem.toCSV());
                escritor.newLine();
            }
        }
    }

    @Override
    public void cargarDesdeCSV(String ruta, Function<String, T> fromCSV) throws Exception {
        lista.clear();

        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {

            // ignorar encabezado
            lector.readLine();

            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    lista.add(fromCSV.apply(linea));
                }
            }
        }
    }

    @Override
    public void guardarEnJSON(String ruta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}


   




