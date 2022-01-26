package aau.at.friendsifybirthdayservice.obj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

@Data
public class Person {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private LocalDate birthday;

    private String email;

    public String getFullName() {
        return StringUtils.join(this.getFirstName(), this.getLastName());
    }
}