package co.com.ies.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Creditos.
 */
@Entity
@Table(name = "creditos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Creditos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "coin_in", precision=10, scale=2, nullable = false)
    private BigDecimal coinIn;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "coin_out", precision=10, scale=2, nullable = false)
    private BigDecimal coinOut;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "handpay", precision=10, scale=2, nullable = false)
    private BigDecimal handpay;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "jackpot", precision=10, scale=2, nullable = false)
    private BigDecimal jackpot;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "bill", precision=10, scale=2, nullable = false)
    private BigDecimal bill;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCoinIn() {
        return coinIn;
    }

    public Creditos coinIn(BigDecimal coinIn) {
        this.coinIn = coinIn;
        return this;
    }

    public void setCoinIn(BigDecimal coinIn) {
        this.coinIn = coinIn;
    }

    public BigDecimal getCoinOut() {
        return coinOut;
    }

    public Creditos coinOut(BigDecimal coinOut) {
        this.coinOut = coinOut;
        return this;
    }

    public void setCoinOut(BigDecimal coinOut) {
        this.coinOut = coinOut;
    }

    public BigDecimal getHandpay() {
        return handpay;
    }

    public Creditos handpay(BigDecimal handpay) {
        this.handpay = handpay;
        return this;
    }

    public void setHandpay(BigDecimal handpay) {
        this.handpay = handpay;
    }

    public BigDecimal getJackpot() {
        return jackpot;
    }

    public Creditos jackpot(BigDecimal jackpot) {
        this.jackpot = jackpot;
        return this;
    }

    public void setJackpot(BigDecimal jackpot) {
        this.jackpot = jackpot;
    }

    public BigDecimal getBill() {
        return bill;
    }

    public Creditos bill(BigDecimal bill) {
        this.bill = bill;
        return this;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Creditos creditos = (Creditos) o;
        if (creditos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), creditos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Creditos{" +
            "id=" + getId() +
            ", coinIn=" + getCoinIn() +
            ", coinOut=" + getCoinOut() +
            ", handpay=" + getHandpay() +
            ", jackpot=" + getJackpot() +
            ", bill=" + getBill() +
            "}";
    }
}
