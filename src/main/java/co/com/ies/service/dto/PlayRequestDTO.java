package co.com.ies.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

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
    
    private BigDecimal bonusRestrictedBalance;

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

	public BigDecimal getBonusRestrictedBalance() {
		return bonusRestrictedBalance;
	}

	public void setBonusRestrictedBalance(BigDecimal bonusRestrictedBalance) {
		this.bonusRestrictedBalance = bonusRestrictedBalance;
	}

	@Override
	public String toString() {
		return "PlayRequestDTO [id=" + id + ", playtype=" + playtype + ", token=" + token + ", system=" + system
				+ ", timestamp=" + timestamp + ", ticketid=" + ticketid + ", transactionid=" + transactionid
				+ ", amount=" + amount + ", balance=" + balance + ", bonusbalance=" + bonusbalance
				+ ", bonusRestrictedBalance=" + bonusRestrictedBalance + ", user=" + user + ", operator=" + operator
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((bonusRestrictedBalance == null) ? 0 : bonusRestrictedBalance.hashCode());
		result = prime * result + ((bonusbalance == null) ? 0 : bonusbalance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((playtype == null) ? 0 : playtype.hashCode());
		result = prime * result + ((system == null) ? 0 : system.hashCode());
		result = prime * result + ((ticketid == null) ? 0 : ticketid.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((transactionid == null) ? 0 : transactionid.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayRequestDTO other = (PlayRequestDTO) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (bonusRestrictedBalance == null) {
			if (other.bonusRestrictedBalance != null)
				return false;
		} else if (!bonusRestrictedBalance.equals(other.bonusRestrictedBalance))
			return false;
		if (bonusbalance == null) {
			if (other.bonusbalance != null)
				return false;
		} else if (!bonusbalance.equals(other.bonusbalance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (playtype != other.playtype)
			return false;
		if (system == null) {
			if (other.system != null)
				return false;
		} else if (!system.equals(other.system))
			return false;
		if (ticketid == null) {
			if (other.ticketid != null)
				return false;
		} else if (!ticketid.equals(other.ticketid))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (transactionid == null) {
			if (other.transactionid != null)
				return false;
		} else if (!transactionid.equals(other.transactionid))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

    
    
}
