package co.com.ies.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import co.com.ies.domain.enumeration.PlayType;

/**
 * A DTO for the PlayRequest entity.
 */
public class PlayRequestDTO implements Serializable {

    private Long id;

    @NotNull
    private PlayType playtype;

    @NotNull
    private String token;

    @NotNull
    private String system;

    @NotNull
    private ZonedDateTime timestamp;

    private String ticketid;

    private String transactionid;

    private BigDecimal amount;

    private BigDecimal balance;

    private BigDecimal bonusbalance;

    @NotNull
    private String user;

    @NotNull
    private String operator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayType getPlaytype() {
        return playtype;
    }

    public void setPlaytype(PlayType playtype) {
        this.playtype = playtype;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBonusbalance() {
        return bonusbalance;
    }

    public void setBonusbalance(BigDecimal bonusbalance) {
        this.bonusbalance = bonusbalance;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlayRequestDTO playRequestDTO = (PlayRequestDTO) o;
        if(playRequestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), playRequestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlayRequestDTO{" +
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
