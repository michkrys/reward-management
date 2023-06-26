package com.interview.reward.management.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionMessage {

    public static String notFoundMessage(String name) {
        return String.format("%s not found.", name);
    }
}
