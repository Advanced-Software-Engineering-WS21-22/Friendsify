import Entities.Friends;
import Entities.Person;

public class RegistryFactory {

    public static MockUpRegistry getMockUpRegistry(String entity) {
        switch (entity) {
            case "Person":
                return new MockUpRegistry<Integer, Person>();
            case "Friends":
                return new MockUpRegistry<Integer, Friends>();
            default:
                return null;
        }
    }

}
