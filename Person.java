// Person.java
package ds.graph;

import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private String name;             // Name of the person
    private int age;                 // Age of the person
    private float socialHygiene;     // Social hygiene factor of the person
    private ArrayList<Person> contacts;  // List of contacts associated with this person
    private Business business;        // Business associated with this person
    private Business destination;     // Destination business for this person's movement

    // Constructor to initialize a Person object with a given name, age, and social hygiene
    public Person(String name, int age, float socialHygiene) {
        this.name = name;
        this.age = age;
        this.socialHygiene = socialHygiene;
        this.contacts = new ArrayList<>();  // Initialize the list of contacts
        this.business = null;  // Initialize the business as null
    }

    // Method to calculate and retrieve the ineffectiveness of the person
    public float getInfectiveness() {
        return (age / 100.0f) - (socialHygiene * (age / 100.0f));
    }

    // Getter method to retrieve the name of the person
    public String getName() {
        return name;
    }

    // Getter method to retrieve the age of the person
    public int getAge() {
        return age;
    }

    // Getter method to retrieve the social hygiene factor of the person
    public float getSocialHygiene() {
        return socialHygiene;
    }

    // Getter method to retrieve the list of contacts associated with this person
    public ArrayList<Person> getContacts() {
        return contacts;
    }

    // Setter method to set the business associated with this person
    public void setBusiness(Business business) {
        this.business = business;
    }

    // Getter method to retrieve the business associated with this person
    public Business getBusiness() {
        return business;
    }

    // Setter method to set the destination business for this person's movement
    public void setDestination(Business destination) {
        this.destination = destination;
    }

    // Getter method to retrieve the destination business for this person's movement
    public Business getDestination() {
        return destination;
    }

    // Override the equals method to compare the content of two Person objects based on their names
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(name, person.name);
    }

    // Override the hashCode method to generate a hash code based on the name
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // Override the toString method to provide a string representation of the Person object
    @Override
    public String toString() {
        return "Person: " + name + ", " + age + ". Contacts: " + contacts.size() +
                ". Business: " + (business != null ? business.toString() : "No business");
    }
}
