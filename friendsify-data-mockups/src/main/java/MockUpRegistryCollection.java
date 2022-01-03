import Entities.Friends;
import Entities.Person;

public class MockUpRegistryCollection {

    private static MockUpRegistryCollection mockUpRegistryCollection = null;

    private Registry personRegistry;
    private Registry friendsRegistry;

    private MockUpRegistryCollection() {
        this.personRegistry = new MockUpRegistryImpl<Integer, Person>();
        this.friendsRegistry = new MockUpRegistryImpl<Integer, Friends>();

        this.addPersonSamples();
        this.addFriendsSamples();

        //TODO: Add dummy data to the registries
    }

    public MockUpRegistryCollection getMockUpRegistryCollection() {
        if(mockUpRegistryCollection == null){
            mockUpRegistryCollection = new MockUpRegistryCollection();
        }

        return mockUpRegistryCollection;
    }

    public Registry getPersonRegistry() {
        return personRegistry;
    }

    public Registry getFriendsRegistry() {
        return friendsRegistry;
    }

    private void addPersonSamples() {

    }

    private void addFriendsSamples() {

    }
}
