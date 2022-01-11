import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import registry.MockUpRegistryCollection;

public class MockUpCollectionTest {

    private MockUpRegistryCollection mockupcollection;

    @Before
    public void setUp() {
        this.mockupcollection = MockUpRegistryCollection.getMockUpRegistryCollection();
    }

    @After
    public void tearDown() {
        this.mockupcollection = null;
    }

    @Test
    public void testCollectionSize() {
        Assert.assertEquals(6, this.mockupcollection.getPersonRegistry().getAll().size());
        Assert.assertEquals(4, this.mockupcollection.getFriendsRegistry().getAll().size());
    }

}
