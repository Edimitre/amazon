package it.socepi.integration.marketplace.amazon.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantita;

    private String partNumber;

    private String codiceVarianteArticolo;

    private String nomeArticolo;

    private String nomeVarianteArticolo;

    private BigDecimal prezzoUnitario;

    private BigDecimal iva ;


    public Article(Integer quantita, String partNumber, String codiceVarianteArticolo, String nomeArticolo, String nomeVarianteArticolo, BigDecimal prezzoUnitario, BigDecimal iva) {
        this.quantita = quantita;
        this.partNumber = partNumber;
        this.codiceVarianteArticolo = codiceVarianteArticolo;
        this.nomeArticolo = nomeArticolo;
        this.nomeVarianteArticolo = nomeVarianteArticolo;
        this.prezzoUnitario = prezzoUnitario;
        this.iva = iva;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", quantita=" + quantita +
                ", partNumber='" + partNumber + '\'' +
                ", codiceVarianteArticolo='" + codiceVarianteArticolo + '\'' +
                ", nomeArticolo='" + nomeArticolo + '\'' +
                ", nomeVarianteArticolo='" + nomeVarianteArticolo + '\'' +
                ", prezzoUnitario=" + prezzoUnitario +
                ", iva=" + iva +
                '}';
    }

    public Article() {
    }
}
