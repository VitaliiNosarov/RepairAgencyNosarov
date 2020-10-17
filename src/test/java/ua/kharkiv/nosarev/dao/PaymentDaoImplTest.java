package ua.kharkiv.nosarev.dao;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.dao.api.PaymentDao;
import ua.kharkiv.nosarev.entitie.Payment;

import javax.sql.DataSource;
import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class PaymentDaoImplTest {

    private DataSource dataSource;
    private PaymentDao paymentDao;
    private Payment payment;

    @Before
    public void setUp() throws Exception {
        dataSource = DBCPDataSource.getDataSource();
        paymentDao = new PaymentDaoImpl(dataSource);
        payment = new Payment.Builder().addId(1).addUserId(3)
                .addSum(new BigDecimal(10)).build();
    }

    @Test
    public void insertPaymentShouldReturnFalseWhenNotEnoughMoney(){
        assertFalse(paymentDao.insertPayment(payment));
    }

    @Test
    public void getPaymentShouldReturnCorrectPaymentFromDB() {
        assertEquals(new BigDecimal(24), paymentDao.getPayment(1).getSum());
    }
}