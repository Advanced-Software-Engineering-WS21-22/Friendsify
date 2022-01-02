import Entities.Friends;
import Entities.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;


public class MockUpRegistryTest {

    private Registry<Integer, Person> personRegistry;
    private Registry<Integer, Friends> friendsRegistry;
    private Person testPerson;
    private Friends testFriends;

    @Before
    public void setUp() {
        this.personRegistry = RegistryFactory.getMockUpRegistry("Person");
        this.friendsRegistry = RegistryFactory.getMockUpRegistry("Friends");
        this.testPerson = this.createTestPerson();
        this.testFriends = this.createTestFriends();
        this.personRegistry.addMockUp(0, this.testPerson);
        this.friendsRegistry.addMockUp(0, testFriends);
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
        return new Person(1, "Hans", new Date(), 12);
    }

    private Friends createTestFriends() {
        return new Friends(0, 1,2);
    }
}
