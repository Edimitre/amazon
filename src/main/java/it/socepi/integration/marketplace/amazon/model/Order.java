package it.socepi.integration.marketplace.amazon.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String denominazione;

    private String via ;

    private String cap;

    private String userName;
    private String citta;

    private String provincia ;

    private String telefono;

    private String codice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    private BigDecimal iva;

    private BigDecimal subTotale;

    private BigDecimal totale;

    private String notes;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Article> articleList;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "market_place_id", referencedColumnName = "id")
    private MarketPlace marketPlace;

    public Order() {
    }

    public Order(String denominazione, String via, String cap, String userName, String citta, String provincia, String telefono, String codice, Date data, BigDecimal iva, BigDecimal subTotale, BigDecimal totale, String notes, State state, List<Article> articleList, MarketPlace marketPlace) {
        this.denominazione = denominazione;
        this.via = via;
        this.cap = cap;
        this.userName = userName;
        this.citta = citta;
        this.provincia = provincia;
        this.telefono = telefono;
        this.codice = codice;
        this.data = data;
        this.iva = iva;
        this.subTotale = subTotale;
        this.totale = totale;
        this.notes = notes;
        this.state = state;
        this.articleList = articleList;
        this.marketPlace = marketPlace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getSubTotale() {
        return subTotale;
    }

    public void setSubTotale(BigDecimal subTotale) {
        this.subTotale = subTotale;
    }

    public BigDecimal getTotale() {
        return totale;
    }

    public void setTotale(BigDecimal totale) {
        this.totale = totale;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public MarketPlace getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", denominazione='" + denominazione + '\'' +
                ", via='" + via + '\'' +
                ", cap='" + cap + '\'' +
                ", userName='" + userName + '\'' +
                ", citta='" + citta + '\'' +
                ", provincia='" + provincia + '\'' +
                ", telefono='" + telefono + '\'' +
                ", codice='" + codice + '\'' +
                ", data=" + data +
                ", iva=" + iva +
                ", subTotale=" + subTotale +
                ", totale=" + totale +
                ", notes='" + notes + '\'' +
                ", state=" + state +
                ", articleList=" + articleList +
                ", marketPlace=" + marketPlace +
                '}';
    }
}
