// BusinessGraph.java
package ds.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class BusinessGraph {
    private ArrayList<Business> vertices;

    public BusinessGraph() {
        this.vertices = new ArrayList<>();
    }

    public void addVertex(Business bus) {
        vertices.add(bus);
    }

    public void removeVertex(Business bus) {
        vertices.remove(bus);
        // Remove references to the removed business from other businesses' edges
        for (Business vertex : vertices) {
            vertex.removeEdge(bus);
        }
    }
    
    public ArrayList<Business> getVertices() {
        return vertices;
    }

    public int totalPersonsInfected(Business start) {
        Set<Person> infectedPersons = new HashSet<>();
        Queue<Business> queue = new LinkedList<>();
        Set<Business> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Business current = queue.poll();
            for (Person person : current.getEdges()) {
                if (!infectedPersons.contains(person)) {
                    infectedPersons.add(person);
                    if (person.getDestination() != null && !visited.contains(person.getDestination())) {
                        queue.add(person.getDestination());
                        visited.add(person.getDestination());
                    }
                }
            }
        }

        return infectedPersons.size()+1;
    }


    public int minStepsToDestFromStart(Business start, Business dest) {
        Set<Business> visited = new HashSet<>();
        int steps = minStepsToDestFromStartHelper(start, dest, visited);
        return steps == Integer.MAX_VALUE ? -1 : steps;
    }

    private int minStepsToDestFromStartHelper(Business current, Business dest, Set<Business> visited) {
        System.out.println("Visiting: " + current.getName());

        if (current.equals(dest)) {
            System.out.println("Destination reached: " + current.getName());
            return 0;
        }

        visited.add(current);

        int minSteps = Integer.MAX_VALUE;
        for (Person person : current.getEdges()) {
            Business nextBusiness = person.getDestination();
            if (nextBusiness != null && !visited.contains(nextBusiness)) {
                int steps = 1 + minStepsToDestFromStartHelper(nextBusiness, dest, visited);
                System.out.println("Steps from " + current.getName() + " to " + dest.getName() + ": " + steps);
                minSteps = Math.min(minSteps, steps);
            }
        }

        visited.remove(current);
        return minSteps;
    }




    public boolean isStronglyConnected(Business start) {
        Set<Business> visitedForward = new HashSet<>();
        dfs(start, visitedForward);

        System.out.println("Visited Forward: " + visitedForward);

        if (visitedForward.size() != vertices.size()) {
            System.out.println("Not all vertices are reachable from the start");
            return false;
        }

        // Reset visited set for reverse traversal
        Set<Business> visitedReverse = new HashSet<>();
        dfsReverse(start, visitedReverse);

        System.out.println("Visited Reverse: " + visitedReverse);

        return visitedReverse.size() == vertices.size();
    }

    private void dfs(Business current, Set<Business> visited) {
        visited.add(current);

        for (Person person : current.getEdges()) {
            Business nextBusiness = person.getDestination();
            if (nextBusiness != null && !visited.contains(nextBusiness)) {
                dfs(nextBusiness, visited);
            }
        }
    }

    private void dfsReverse(Business current, Set<Business> visited) {
        visited.add(current);

        for (Person person : current.getEdges()) {
            Business prevBusiness = person.getDestination();
            if (prevBusiness != null && !visited.contains(prevBusiness)) {
                dfsReverse(prevBusiness, visited);
            }
        }
    }





}
