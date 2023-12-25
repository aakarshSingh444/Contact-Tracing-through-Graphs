// SocialGraph.java
package ds.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class SocialGraph {
    private ArrayList<Person> vertices;  // List of people in the social graph

    // Constructor to initialize a SocialGraph object with an empty list of vertices
    public SocialGraph() {
        this.vertices = new ArrayList<>();
    }

    // Getter method to retrieve the list of people in the social graph
    public ArrayList<Person> getVertices() {
        return vertices;
    }

    // Method to add a person to the social graph
    public void addVertex(Person p) throws PersonAlreadyExists {
        if (vertices.contains(p)) {
            throw new PersonAlreadyExists("Person already present in the graph.");
        }
        vertices.add(p);
    }

    // Method to remove a person from the social graph along with associated edges
    public void removeVertex(Person p) throws PersonDoesNotExist {
        if (!vertices.contains(p)) {
            throw new PersonDoesNotExist("Person not found in the graph.");
        }
        vertices.remove(p);

        // Remove edges to this person
        for (Person vertex : vertices) {
            vertex.getContacts().remove(p);
        }
    }

    // Method to add an edge (connection) between two people in the social graph
    public void addEdge(Person a, Person b) throws PersonDoesNotExist {
        if (!vertices.contains(a) || !vertices.contains(b)) {
            throw new PersonDoesNotExist("Person not found in the graph.");
        }
        a.getContacts().add(b);
        b.getContacts().add(a);
    }

    // Method to remove an edge (connection) between two people in the social graph
    public void removeEdge(Person a, Person b) throws EdgeDoesNotExist, PersonDoesNotExist {
        if (!vertices.contains(a) || !vertices.contains(b)) {
            throw new PersonDoesNotExist("Person not found in the graph.");
        }
        if (!a.getContacts().contains(b) || !b.getContacts().contains(a)) {
            throw new EdgeDoesNotExist("Edge does not exist between the given people.");
        }
        a.getContacts().remove(b);
        b.getContacts().remove(a);
    }

    // Method to perform a breadth-first search in the social graph
    public ArrayList<Person> searchBFS(Person start, Person target) throws PersonDoesNotExist {
        validatePersonsExist(start, target);
        Queue<Person> queue = new LinkedList<>();
        Set<Person> visited = new HashSet<>();
        ArrayList<Person> result = new ArrayList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            result.add(current);

            if (current.equals(target)) {
                break;
            }

            for (Person neighbor : current.getContacts()) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return result;
    }

    // Method to perform a weighted breadth-first search in the social graph based on infectiveness
    public ArrayList<Person> searchWeightedBFS(Person start, Person target) throws PersonDoesNotExist {
        validatePersonsExist(start, target);

        PriorityQueue<Person> queue = new PriorityQueue<>((a, b) ->
                Float.compare(b.getInfectiveness(), a.getInfectiveness())); // Higher infectiveness is preferred
        Set<Person> visited = new HashSet<>();
        ArrayList<Person> result = new ArrayList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            result.add(current);

            if (current.equals(target)) {
                break;
            }

            for (Person neighbor : current.getContacts()) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return result;
    }

    // Method to perform a depth-first search in the social graph
    public ArrayList<Person> searchDFS(Person start, Person target) throws PersonDoesNotExist {
        validatePersonsExist(start, target);

        Set<Person> visited = new HashSet<>();
        ArrayList<Person> result = new ArrayList<>();
        Stack<Person> stack = new Stack<>();

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            Person current = stack.pop();
            result.add(current);

            if (current.equals(target)) {
                break;
            }

            for (Person neighbor : current.getContacts()) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return result;
    }

    // Method to perform a weighted depth-first search in the social graph based on infectiveness
    public ArrayList<Person> searchWeightedDFS(Person start, Person target) throws PersonDoesNotExist {
        validatePersonsExist(start, target);

        Set<Person> visited = new HashSet<>();
        ArrayList<Person> result = new ArrayList<>();
        Stack<Person> stack = new Stack<>();

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            Person current = stack.pop();
            result.add(current);

            if (current.equals(target)) {
                break;
            }

            // Sort neighbors by infectiveness in descending order
            List<Person> neighbors = new ArrayList<>(current.getContacts());
            neighbors.sort((a, b) -> Float.compare(b.getInfectiveness(), a.getInfectiveness()));

            for (Person neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return result;
    }

    // Method to count the number of contacts of a person in the social graph
    public int countContacts(Person start) throws PersonDoesNotExist {
        validatePersonsExist(start, start);

        Set<Person> visited = new HashSet<>();
        Stack<Person> stack = new Stack<>();
        int count = 0;

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            Person current = stack.pop();

            for (Person neighbor : current.getContacts()) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    count++;
                }
            }
        }

        return count;
    }

    // Method to validate the existence of two persons in the social graph
    private void validatePersonsExist(Person start, Person target) throws PersonDoesNotExist {
        if (!vertices.contains(start) || !vertices.contains(target)) {
            throw new PersonDoesNotExist("Person not found in the graph.");
        }
    }

    // Override the toString method to provide a string representation of the SocialGraph object
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("SocialGraph:\n");

        // Display each person and their contacts in the graph
        for (Person person : vertices) {
            result.append(person.getName()).append(" contacts: ");
            for (Person contact : person.getContacts()) {
                result.append(contact.getName()).append(", ");
            }
            result.append("\n");
        }

        return result.toString();
    }
}
