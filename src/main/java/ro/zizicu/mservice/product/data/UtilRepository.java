package ro.zizicu.mservice.product.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UtilRepository  {

    private final JdbcTemplate jdbcTemplate;

    public Long getTransactionId() {
        log.debug("get transaction id");
        Long transactionId = jdbcTemplate.query("select nextval(sq_transaction_id)", new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getLong(1);
            }
        });
        log.debug("transaction id = {}", transactionId);
        return transactionId;
    }



}
