import Entities.Friends;
import Entities.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import registry.MockUpRegistry;
import registry.MockUpMockUpRegistryImpl;

import java.time.LocalDate;


public class MockUpMockUpRegistryTest {

    private MockUpRegistry<Person> personRegistry;
    private MockUpRegistry<Friends> friendsRegistry;
    private Person testPerson;
    private Friends testFriends;

    @Before
    public void setUp() {
        this.personRegistry = new MockUpMockUpRegistryImpl<>();
        this.friendsRegistry = new MockUpMockUpRegistryImpl<>();
        this.testPerson = this.createTestPerson();
        this.testFriends = this.createTestFriends();
        this.personRegistry.addMockUp(this.testPerson);
        this.friendsRegistry.addMockUp(this.testFriends);
    }

    @After
    public void tearDown() {
        this.personRegistry = null;
        this.testPerson = null;
    }

    @Test
    public void testInitialization() {
        Assert.assertEquals(this.testPerson, this.personRegistry.getByKey(0));
        Assert.assertEquals(this.testFriends, this.friendsRegistry.getByKey(0));
    }

    private Person createTestPerson() {
        return new Person(Long.valueOf(0), "Max", "Mustermann", "max@mustermann.de", LocalDate.of(1999,4,1), "Q64", "Berlin", "testhash");
    }

    private Friends createTestFriends() {
        return new Friends(Long.valueOf(0), Long.valueOf(0), Long.valueOf(1), LocalDate.of(1999,4,1), false);
    }
}
