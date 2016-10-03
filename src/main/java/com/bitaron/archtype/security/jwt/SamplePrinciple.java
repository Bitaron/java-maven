package com.bitaron.archtype.security.jwt;

import java.security.Principal;


public class SamplePrinciple implements Principal {
    private String id;

    public SamplePrinciple(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.id;
    }
}
