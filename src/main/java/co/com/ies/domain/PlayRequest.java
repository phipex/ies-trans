package co.com.ies.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

import co.com.ies.domain.enumeration.PlayType;

/**
 * A PlayRequest.
 */
@Entity
@Table(name = "play_request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PlayRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "playtype", nullable = false)
    private PlayType playtype;

    @NotNull
    @Column(name = "token", nullable = false)
    private String token;

    @NotNull
    @Column(name = "jhi_system", nullable = false)
    private String system;

    @NotNull
    @Column(name = "jhi_timestamp", nullable = false)
    private ZonedDateTime timestamp;

    @Column(name = "ticketid")
    private String ticketid;

    @Column(name = "transactionid")
    private String transactionid;

    @Column(name = "amount", precision=10, scale=2)
    private BigDecimal amount;

    @Column(name = "balance", precision=10, scale=2)
    private BigDecimal balance;

    @Column(name = "bonusbalance", precision=10, scale=2)
    private BigDecimal bonusbalance;

    @NotNull
    @Column(name = "jhi_user", nullable = false)
    private String user;

    @NotNull
    @Column(name = "operator", nullable = false)
    private String operator;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayType getPlaytype() {
        return playtype;
    }

    public PlayRequest playtype(PlayType playtype) {
        this.playtype = playtype;
        return this;
    }

    public void setPlaytype(PlayType playtype) {
        this.playtype = playtype;
    }

    public String getToken() {
        return token;
    }

    public PlayRequest token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSystem() {
        return system;
    }

    public PlayRequest system(String system) {
        this.system = system;
        return this;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public PlayRequest timestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTicketid() {
        return ticketid;
    }

    public PlayRequest ticketid(String ticketid) {
        this.ticketid = ticketid;
        return this;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public PlayRequest transactionid(String transactionid) {
        this.transactionid = transactionid;
        return this;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PlayRequest amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public PlayRequest balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBonusbalance() {
        return bonusbalance;
    }

    public PlayRequest bonusbalance(BigDecimal bonusbalance) {
        this.bonusbalance = bonusbalance;
        return this;
    }

    public void setBonusbalance(BigDecimal bonusbalance) {
        this.bonusbalance = bonusbalance;
    }

    public String getUser() {
        return user;
    }

    public PlayRequest user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOperator() {
        return operator;
    }

    public PlayRequest operator(String operator) {
        this.operator = operator;
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
        PlayRequest playRequest = (PlayRequest) o;
        if (playRequest.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), playRequest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlayRequest{" +
            "id=" + getId() +
            ", playtype='" + getPlaytype() + "'" +
            ", token='" + getToken() + "'" +
            ", system='" + getSystem() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", ticketid='" + getTicketid() + "'" +
            ", transactionid='" + getTransactionid() + "'" +
            ", amount=" + getAmount() +
            ", balance=" + getBalance() +
            ", bonusbalance=" + getBonusbalance() +
            ", user='" + getUser() + "'" +
            ", operator='" + getOperator() + "'" +
            "}";
    }
}
