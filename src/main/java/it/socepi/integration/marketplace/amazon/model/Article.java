package it.socepi.integration.marketplace.amazon.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Sku;

    private Integer quantita;

    private String partNumber;

    private String codiceVarianteArticolo;

    private String nomeArticolo;

    private String nomeVarianteArticolo;

    private BigDecimal prezzoUnitario;

    private BigDecimal prezzoTotale;

    private BigDecimal iva ;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Order order;

    public Article() {
    }

    public Article(String sku, Integer quantita, String partNumber, String codiceVarianteArticolo, String nomeArticolo, String nomeVarianteArticolo, BigDecimal prezzoUnitario, BigDecimal prezzoTotale, BigDecimal iva, Order order) {
        Sku = sku;
        this.quantita = quantita;
        this.partNumber = partNumber;
        this.codiceVarianteArticolo = codiceVarianteArticolo;
        this.nomeArticolo = nomeArticolo;
        this.nomeVarianteArticolo = nomeVarianteArticolo;
        this.prezzoUnitario = prezzoUnitario;
        this.prezzoTotale = prezzoTotale;
        this.iva = iva;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return Sku;
    }

    public void setSku(String sku) {
        Sku = sku;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getCodiceVarianteArticolo() {
        return codiceVarianteArticolo;
    }

    public void setCodiceVarianteArticolo(String codiceVarianteArticolo) {
        this.codiceVarianteArticolo = codiceVarianteArticolo;
    }

    public String getNomeArticolo() {
        return nomeArticolo;
    }

    public void setNomeArticolo(String nomeArticolo) {
        this.nomeArticolo = nomeArticolo;
    }

    public String getNomeVarianteArticolo() {
        return nomeVarianteArticolo;
    }

    public void setNomeVarianteArticolo(String nomeVarianteArticolo) {
        this.nomeVarianteArticolo = nomeVarianteArticolo;
    }

    public BigDecimal getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public BigDecimal getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(BigDecimal prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", Sku='" + Sku + '\'' +
                ", quantita=" + quantita +
                ", partNumber='" + partNumber + '\'' +
                ", codiceVarianteArticolo='" + codiceVarianteArticolo + '\'' +
                ", nomeArticolo='" + nomeArticolo + '\'' +
                ", nomeVarianteArticolo='" + nomeVarianteArticolo + '\'' +
                ", prezzoUnitario=" + prezzoUnitario +
                ", prezzoTotale=" + prezzoTotale +
                ", iva=" + iva +
                ", order=" + order +
                '}';
    }
}
