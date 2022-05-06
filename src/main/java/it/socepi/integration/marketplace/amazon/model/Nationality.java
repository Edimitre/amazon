package it.socepi.integration.marketplace.amazon.model;


import javax.persistence.*;

@Entity
@Table(name = "nationality_table")
public class Nationality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name ;


    public Nationality() {
    }

    public Nationality(String name) {
        this.name = name;
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
}
