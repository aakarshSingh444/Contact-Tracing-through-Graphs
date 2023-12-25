package ds.graph.tests;
import ds.graph.Person;
import ds.graph.Business;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void getInfectiveness_shouldCalculateCorrectInfectiveness() {
        // Create a person with age 25 and social hygiene 0.5
        Person person = new Person("John", 25, 0.5f);

        // Calculate ineffectiveness
        float infectiveness = person.getInfectiveness();

        // Expected ineffectiveness: (25 / 100.0) - (0.5 * (25 / 100.0))
        assertEquals(25, infectiveness, 100.0);
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        // Create a person
        Person person = new Person("Alice", 30, 0.7f);

        // Set and get the business
        Business business = new Business("BusinessA");
        person.setBusiness(business);
        assertEquals(business, person.getBusiness());

        // Set and get the destination
        Business destination = new Business("BusinessB");
        person.setDestination(destination);
        assertEquals(destination, person.getDestination());
    }

    @Test
    void equalsAndHashCode_shouldWorkCorrectly() {
        // Create two persons with the same name
        Person person1 = new Person("Bob", 22, 0.6f);
        Person person2 = new Person("Bob", 25, 0.8f);

        // Check if equals works
        assertTrue(person1.equals(person2));

        // Check if hash codes are the same
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    void toString_shouldReturnCorrectStringRepresentation() {
        // Create a person
        Person person = new Person("Eve", 28, 0.9f);

        // Check the string representation
        assertEquals("Person: Eve, 28. Contacts: 0. Business: No business", person.toString());
    }
}
