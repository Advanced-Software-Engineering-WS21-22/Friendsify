package aau.at.friendsifyjokeservice.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum JokeTypes {
    RANDOM,
    JOD;

    public static JokeTypes find(String type) {
        return Arrays.stream(JokeTypes.values())
                .filter(jt -> StringUtils.equalsIgnoreCase(jt.name(), type))
                .findFirst()
                .orElse(JokeTypes.RANDOM);
    }
}
