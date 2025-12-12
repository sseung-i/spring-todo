package com.todo.api.utils.hash;

import org.springframework.stereotype.Component;

@Component
public class Hash {
    public String getHashByToken(String token) {

        return String.valueOf(token.hashCode());
    }

}
