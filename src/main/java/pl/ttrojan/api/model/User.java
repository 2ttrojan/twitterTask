package pl.ttrojan.api.model;

import java.math.BigInteger;

/**
 * Created by HomeAccount on 25.07.2017.
 */
public class User implements Model{

    private Long id;
    private String name;
    private String surname;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
