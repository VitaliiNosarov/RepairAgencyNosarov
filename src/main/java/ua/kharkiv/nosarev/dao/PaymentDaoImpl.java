package ua.kharkiv.nosarev.dao;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.PaymentDao;
import ua.kharkiv.nosarev.entitie.Payment;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.exception.DatabaseException;
import ua.kharkiv.nosarev.util.DaoUtil;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDaoImpl implements PaymentDao {

    private static final Logger LOGGER = Logger.getLogger(PaymentDaoImpl.class);
    private DataSource dataSource;

    public PaymentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean insertPayment(Payment payment) throws DatabaseException {
        Connection connection = null;
        PreparedStatement paymentStatement = null;
        PreparedStatement orderStatement = null;
        PreparedStatement accountStatement = null;
        PreparedStatement accountBalanceStatement = null;
        ResultSet rs = null;
        BigDecimal userBalance = new BigDecimal(0);
        boolean result = false;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            accountBalanceStatement = connection.prepareStatement(SQLConstant.GET_ACCOUNT_BALANCE);
            accountBalanceStatement.setLong(1, payment.getUserId());
            rs = accountBalanceStatement.executeQuery();
            if (rs.next()) {
                userBalance = rs.getBigDecimal("balance");
            }
            if (checkBalance(payment, userBalance)) {
                paymentStatement = connection.prepareStatement(SQLConstant.SAVE_PAYMENT);
                paymentStatement.setBigDecimal(1, payment.getSum());
                paymentStatement.setLong(2, payment.getUserId());
                paymentStatement.setLong(3, payment.getId());
                paymentStatement.executeUpdate();

                orderStatement = connection.prepareStatement(SQLConstant.SET_ORDER_STATUS);
                orderStatement.setString(1, OrderStatus.PAID.toString());
                orderStatement.setLong(2, payment.getId());
                orderStatement.executeUpdate();

                accountStatement = connection.prepareStatement(SQLConstant.SET_ACCOUNT_BALANCE);
                accountStatement.setBigDecimal(1, userBalance.subtract(payment.getSum()));
                accountStatement.setLong(2, payment.getUserId());
                accountStatement.executeUpdate();
                connection.commit();
                result = true;
            }
        } catch (SQLException ex) {
            DaoUtil.rollBack(connection);
            LOGGER.error("Can't save Payment to database", ex);
            throw new DatabaseException();
        } finally {
            DaoUtil.close(rs, paymentStatement, orderStatement, accountBalanceStatement, accountStatement, connection);
        }
        return result;
    }

    @Override
    public Payment getPayment(long orderId) {
        Payment payment = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLConstant.GET_PAYMENT)) {
            statement.setLong(1, orderId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    payment = new Payment.Builder()
                            .addId(orderId)
                            .addUserId(rs.getLong("account_id"))
                            .addPaymentTime(rs.getTimestamp("creating_time"))
                            .addSum(rs.getBigDecimal("payment_value")).build();
                }
            }
        } catch (SQLException ex) {
            LOGGER.warn("Can't get Payment from database", ex);
            throw new DatabaseException();
        }
        return payment;
    }

    private boolean checkBalance(Payment payment, BigDecimal userBalance) {
        if (payment.getSum() != null && userBalance != null) {
            return payment.getSum().compareTo(userBalance) < 0 || payment.getSum().compareTo(userBalance) == 0;
        }
        return false;
    }
}
