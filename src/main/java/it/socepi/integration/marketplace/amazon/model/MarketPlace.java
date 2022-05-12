package it.socepi.integration.marketplace.amazon.model;


import javax.persistence.*;

@Entity
@Table(name = "market_place_table")
public class MarketPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;




    public MarketPlace() {
    }

    public MarketPlace(String name, Nationality nationality) {
        this.name = name;
        this.nationality = nationality;

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

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

}
