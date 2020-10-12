package ua.kharkiv.nosarev.entitie;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment extends AbstractEntity{

    private long userId;
    private BigDecimal sum;
    private Timestamp paymentTime;

    private Payment() {
    }

    public long getUserId() {
        return userId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }


    public static class Builder {

        private Payment payment;

        public Builder() {
            payment = new Payment();
        }

        public Builder addId(long id) {
            payment.setId(id);
            return this;
        }

        public Builder addUserId(long userId) {
            payment.userId = userId;
            return this;
        }

        public Builder addSum(BigDecimal sum) {
            payment.sum = sum;
            return this;
        }

        public Builder addPaymentTime(Timestamp paymentTime) {
            payment.paymentTime = paymentTime;
            return this;
        }

        public Payment build() {
            return payment;
        }
    }
}
