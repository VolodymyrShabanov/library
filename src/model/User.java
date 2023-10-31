package model;

import interfaces.UserInterface;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class User implements UserInterface {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
