// BusinessGraphTest.java
package ds.graph.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ds.graph.Business;
import ds.graph.BusinessGraph;
import ds.graph.Person;

import static org.junit.jupiter.api.Assertions.*;

class BusinessGraphTest {
    private BusinessGraph graph;
    private Business business1, business2, business3;
    private Person person1, person2, person3;

    @BeforeEach
    void setUp() {
        // Initialize a BusinessGraph and some Business and Person instances for testing
        graph = new BusinessGraph();
        business1 = new Business("Business1");
        business2 = new Business("Business2");
        business3 = new Business("Business3");
        person1 = new Person("Person1", 25, 0.8f);
        person2 = new Person("Person2", 30, 0.6f);
        person3 = new Person("Person3", 35, 0.7f);

        // Establish some connections between businesses and persons
        business1.addEdge(business2, person1);
        business1.addEdge(business3, person2);
        business2.addEdge(business3, person3);

        // Add businesses to the graph
        graph.addVertex(business1);
        graph.addVertex(business2);
        graph.addVertex(business3);
        
        System.out.println(graph.toString());
    }

    @Test
    void addVertex() {
        // Test the addVertex method
        assertEquals(3, graph.getVertices().size());
        assertTrue(graph.getVertices().contains(business1));
        assertTrue(graph.getVertices().contains(business2));
        assertTrue(graph.getVertices().contains(business3));
    }

    @Test
    void removeVertex() {
        // Test the removeVertex method
        graph.removeVertex(business1);
        assertEquals(2, graph.getVertices().size());
        assertFalse(graph.getVertices().contains(business1));
        assertTrue(graph.getVertices().contains(business2));
        assertTrue(graph.getVertices().contains(business3));
    }

    @Test
    void totalPersonsInfected() {
        // Test the totalPersonsInfected method
        assertEquals(3, graph.totalPersonsInfected(business1));
    }

    @Test
    void minStepsToDestFromStart() {
        // Test the minStepsToDestFromStart method
        assertEquals(-1, graph.minStepsToDestFromStart(business1, business2));
        assertEquals(-1, graph.minStepsToDestFromStart(business1, business3));
    }

    @Test
    void isStronglyConnected() {
        // Test the isStronglyConnected method
        assertFalse(graph.isStronglyConnected(business1));
    }
}
