package com.endurance.shield.dbserver;

import com.endurance.shield.dbserver.utils.BCrypt;

/**
 * Created by kai on 17/6/17.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(BCrypt.gensalt());
    }
}
