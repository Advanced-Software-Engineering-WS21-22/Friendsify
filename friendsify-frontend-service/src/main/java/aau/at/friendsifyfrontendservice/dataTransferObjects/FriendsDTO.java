package aau.at.friendsifyfrontendservice.dataTransferObjects;

public class FriendsDTO {

    private Long id_initiator;

    private Long id_friend;

    public FriendsDTO() {

    }

    public FriendsDTO(Long id_initiator, Long id_friend) {
        this.id_initiator = id_initiator;
        this.id_friend = id_friend;
    }


}
