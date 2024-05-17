package pr2;

import java.util.List;
import util.Graph;
/**
 * Clase de prueba para el grafo.
 */
public class Main {
    /**
     * Método principal para probar el grafo.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        // Crear un nuevo grafo
        Graph<Integer> graph = new Graph<>();

        // Agregar vértices al grafo
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        // Agregar aristas al grafo
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        // Imprimir el grafo
        System.out.println("Grafo:");
        System.out.println(graph);

        // Encontrar el camino más corto entre dos vértices
        Integer source = 1;
        Integer destination = 5;
        List<Integer> shortestPath = graph.shortestPath(source, destination);

        // Imprimir el camino más corto
        if (shortestPath != null) {
            System.out.println("Camino más corto desde " + source + " hasta " + destination + ":");
            for (Integer vertex : shortestPath) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        } else {
            System.out.println("No hay camino desde " + source + " hasta " + destination);
        }
    }
}
