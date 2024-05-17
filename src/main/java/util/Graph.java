package util;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.*;


public class Graph<V> {

    // Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice `v` al grafo.
     *
     * @param v vértice a añadir.
     * @return `true` si no estaba anteriormente y `false` en caso contrario.
     */
    public boolean addVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<>());
            return true;
        }
        return false;
    }

    /**
     * Añade un arco entre los vértices `v1` y `v2` al grafo. En caso de
     * que no exista alguno de los vértices, lo añade también.
     *
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return `true` si no existía el arco y `false` en caso contrario.
     */
    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        if (!adjacencyList.get(v1).contains(v2)) {
            adjacencyList.get(v1).add(v2);
            return true;
        }
        return false;
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a `v`.
     *
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     */
    public Set<V> obtainAdjacents(V v) {
        return adjacencyList.getOrDefault(v, new HashSet<>());
    }

    /**
     * Comprueba si el grafo contiene el vértice dado.
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return `true` si `v` es un vértice del grafo.
     */
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    /**
     * Método `toString()` reescrito para la clase `Graph.java`.
     * @return una cadena de caracteres con la lista de adyacencia.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V vertex : adjacencyList.keySet()) {
            sb.append(vertex).append(": ");
            sb.append(adjacencyList.get(vertex)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene, en caso de que exista, el camino más corto entre
     * `v1` y `v2`. En caso contrario, devuelve `null`.
     * 
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices del camino más corto
     * entre `v1` y `v2`
     **/
    public List<V> shortestPath(V v1, V v2) {
        // Verificar si los vértices dados existen en el grafo
        if (!containsVertex(v1) || !containsVertex(v2)) {
            return null; // Devolver null si uno de los vértices no está en el grafo
        }

        // Inicializar estructuras de datos
        Queue<V> queue = new LinkedList<>(); // Cola para realizar la búsqueda en anchura
        Map<V, V> parentMap = new HashMap<>(); // Mapa para rastrear el camino más corto
        Set<V> visited = new HashSet<>(); // Conjunto para mantener un registro de los nodos visitados

        // Comenzar la búsqueda desde el vértice de inicio
        queue.offer(v1);
        visited.add(v1);

        // Realizar la búsqueda en anchura
        while (!queue.isEmpty()) {
            V current = queue.poll();

            // Verificar si hemos alcanzado el vértice de destino
            if (current.equals(v2)) {
                break; // Si es así, detener la búsqueda
            }

            // Explorar los vértices adyacentes al vértice actual
            for (V neighbor : obtainAdjacents(current)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor); // Agregar el vecino a la cola
                    visited.add(neighbor); // Marcar el vecino como visitado
                    parentMap.put(neighbor, current); // Registrar el padre del vecino
                }
            }
        }

        // Reconstruir el camino más corto a partir del mapa de padres
        List<V> shortestPath = new ArrayList<>();
        V backtrack = v2;
        while (backtrack != null) {
            shortestPath.add(backtrack);
            backtrack = parentMap.get(backtrack); // Retroceder al padre del nodo actual
        }

        // Invertir la lista para obtener el camino desde el inicio hasta el destino
        Collections.reverse(shortestPath);
        return shortestPath; // Devolver el camino más corto encontrado
    }
}

