package ds.graph.tests;
import ds.graph.Business;
import ds.graph.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BusinessTest {
    private Business business;
    private Business destination;
    private Person person1;
    private Person person2;

    @BeforeEach
    void setUp() {
        // Initialize a Business object and some Person objects for testing
        business = new Business("Test Business");
        destination = new Business("Destination Business");
        person1 = new Person("Alice", 30, 0.8f);
        person2 = new Person("Bob", 40, 0.6f);

        // Set business for person objects
        person1.setBusiness(destination);
        person2.setBusiness(destination);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Test Business", business.getName());
        assertTrue(business.getEdges().isEmpty());
    }

    @Test
    void testAddEdge() {
        business.addEdge(destination, person1);
        assertEquals(1, business.getEdges().size());
        assertTrue(business.getEdges().contains(person1));
    }

    @Test
    void testRemoveEdge() {
        business.addEdge(destination, person1);
        business.removeEdge(destination);

        assertTrue(business.getEdges().isEmpty());
    }

    @Test
    void testRemoveNonExistentEdge() {
        business.removeEdge(destination);

        assertTrue(business.getEdges().isEmpty());
    }

    @Test
    void testEqualsMethod() {
        Business sameBusiness = new Business("Test Business");
        Business differentBusiness = new Business("Different Business");

        assertEquals(business, sameBusiness);
        assertNotEquals(business, differentBusiness);
        assertNotEquals(business, null);
        assertNotEquals(business, new Object());
    }

    @Test
    void testHashCodeMethod() {
        Business sameBusiness = new Business("Test Business");

        assertEquals(business.hashCode(), sameBusiness.hashCode());
        assertNotEquals(business.hashCode(), destination.hashCode());
    }

    @Test
    void testToStringMethod() {
        String expected = "Business: Test Business";
        assertEquals(expected, business.toString());
    }
}
