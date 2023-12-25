// Business.java
package ds.graph;

import java.util.ArrayList;
import java.util.Objects;

public class Business {
    private String name;           // Name of the business
    private ArrayList<Person> edges;  // List of people associated with this business

    // Constructor to initialize a Business object with a given name
    public Business(String name) {
        this.name = name;
        this.edges = new ArrayList<>();  // Initialize the list of edges (people)
    }

    // Getter method to retrieve the name of the business
    public String getName() {
        return name;
    }

    // Getter method to retrieve the list of people associated with this business
    public ArrayList<Person> getEdges() {
        return edges;
    }

    // Method to add a connection (edge) between this business and another business through a person
    public void addEdge(Business dest, Person route) {
        for (Person person : edges) {
            // Check if there is already a connection to the destination business
            if (person.getBusiness() == dest) {
                // If the new route is more effective (less infective), replace the existing route
                if (route.getInfectiveness() > person.getInfectiveness()) {
                    edges.remove(person);  // Remove the existing connection
                    break;  // Exit the loop, as we only need to replace one connection
                } else {
                    return;  // No need to add a new edge if the existing one is more effective
                }
            }
        }
        edges.add(route);  // Add the new connection (edge) to the list
    }

    // Method to remove a connection (edge) between this business and another business
    public void removeEdge(Business dest) {
        edges.removeIf(person -> person.getBusiness() == dest);  // Remove the edge if it exists
    }

    // Override the equals method to compare the content of two Business objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Business business = (Business) obj;

        // Compare the content of the objects based on their names
        return Objects.equals(name, business.name);
    }

    // Override the hashCode method to generate a hash code based on the name
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // Override the toString method to provide a string representation of the Business object
    @Override
    public String toString() {
        return "Business: " + name;
    }
}
