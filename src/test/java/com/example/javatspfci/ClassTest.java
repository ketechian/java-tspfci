package com.example.javatspfci;

import com.example.javatspfci.code.util.JWTUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ClassTest {

    @Test
    void Test(){
        Map<String, String> tokenMap = new HashMap<>();
        System.out.println("13738794215".substring(7,11));
        tokenMap.put("ID","53bdac5cfc374597b1260785c2fd8197");
        tokenMap.put("role","superAdmin");
        String token = JWTUtil.createToken(tokenMap);
        System.out.println(token);

    }

}
