package edu.br.ifsp.applications.persistence.postgres;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.transaction.RequestSituation;
import edu.br.ifsp.domain.usecases.transactions.LineRequestDAO;
import edu.br.ifsp.web.exception.GenericResourceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresLineRequestDAOImpl implements LineRequestDAO {

    private JdbcTemplate jdbcTemplate;
    private PostgresRequestDAOImpl postgresRequestDAO;
    private PostgresToolItemDAOImpl postgresToolItemDAO;

    public PostgresLineRequestDAOImpl(JdbcTemplate jdbcTemplate,
                                      PostgresRequestDAOImpl postgresRequestDAO,
                                      PostgresToolItemDAOImpl postgresToolItemDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.postgresRequestDAO = postgresRequestDAO;
        this.postgresToolItemDAO = postgresToolItemDAO;

    }

    @Value("${queries.sql.line-request-dao.insert.line-request}")
    private String insertLineRequestQuery;

    @Value("${queries.sql.line-request-dao.select.line-request-by-request-id}")
    private String findLineRequestByRequestIdQuery;

    @Value("${queries.sql.line-request-dao.select.line-request-by-id}")
    private String findLineRequestByIdQuery;

    @Value("${queries.sql.line-request-dao.select.line-request-all}")
    private String findAllLineRequestsQuery;

    @Value("${queries.sql.line-request-dao.update.all}")
    private String updateLineRequestQuery;

    @Value("${queries.sql.line-request-dao.update.situation}")
    private String updateLineRequestSituationQuery;

    @Value("${queries.sql.line-request-dao.update.expected-dates}")
    private String updateLineRequestExpectedDatesQuery;

    @Value("${queries.sql.line-request-dao.update.real-withdrawal-date}")
    private String updateLineRequestRealWithdrawalDateQuery;

    @Value("${queries.sql.line-request-dao.update.real-return-date}")
    private String updateLineRequestRealReturnDateQuery;

    @Value("${queries.sql.line-request-dao.delete.line-request}")
    private String deleteLineRequestQuery;

    public Boolean isToolAvailable(UUID toolItemId, Timestamp expectedWithdrawalDate, Timestamp expectedReturnDate) {
        List<LineRequest> lineRequests = findALL();

        for (LineRequest lr : lineRequests) {
            if (lr.getToolItem().getId().equals(toolItemId)) {
                if (lr.getExpectedWithdrawalDate().before(expectedWithdrawalDate) && lr.getExpectedReturnDate().after(expectedWithdrawalDate)) {
                    return false;
                }
                if (lr.getExpectedWithdrawalDate().before(expectedReturnDate) && lr.getExpectedReturnDate().after(expectedReturnDate)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public LineRequest create(LineRequest type) {
        if (!isToolAvailable(type.getToolItem().getId(), type.getExpectedWithdrawalDate(), type.getExpectedReturnDate())) {
            throw new GenericResourceException("Tool is not available for this period.", "Resource Unavailable");
        }

        UUID lineRequestId = UUID.randomUUID();
        jdbcTemplate.update(insertLineRequestQuery, lineRequestId, type.getExpectedReturnDate(), type.getRealReturnDate(), type.getExpectedWithdrawalDate(), type.getRealWithdrawalDate(), RequestSituation.WAITING.name(), type.getToolItem().getId(), type.getRequest().getId());
        return findOne(lineRequestId).get();
    }

    @Override
    public Optional<LineRequest> findByRequest(Request request) {
        LineRequest lineRequest = jdbcTemplate.queryForObject(findLineRequestByRequestIdQuery, this::mapperLineRequestFromRs, request);
        return Optional.of(lineRequest);
    }

    @Override
    public Optional<LineRequest> findByUUID(UUID id) {
        LineRequest lineRequest = jdbcTemplate.queryForObject(findLineRequestByIdQuery, this::mapperLineRequestFromRs, id);
        return Optional.of(lineRequest);
    }

    @Override
    public Optional<LineRequest> findOne(UUID key) {
        try {
            return findByUUID(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<LineRequest> findALL() {
        List<LineRequest> lineRequests = jdbcTemplate.query(findAllLineRequestsQuery, this::mapperLineRequestFromRs);
        return lineRequests;
    }

    @Override
    public LineRequest update(LineRequest type) {
        if (!isToolAvailable(type.getToolItem().getId(), type.getExpectedWithdrawalDate(), type.getExpectedReturnDate())) {
            throw new GenericResourceException("Tool is not available for this period.", "Resource Unavailable");
        }

        jdbcTemplate.update(updateLineRequestQuery, type.getSituation().name(), type.getExpectedWithdrawalDate(),
                type.getExpectedReturnDate(), type.getRealWithdrawalDate(), type.getRealReturnDate(), type.getId());
        return findByUUID(type.getId()).get();
    }

    @Override
    public LineRequest updateSituation(RequestSituation situation, UUID requestId) {
        jdbcTemplate.update(updateLineRequestSituationQuery, situation.name(), requestId);
        return findByUUID(requestId).get();
    }

    @Override
    public LineRequest updateExpectedDates(Timestamp expectedReturnDate, Timestamp expectedWithdrawalDate, UUID requestId) {
        jdbcTemplate.update(updateLineRequestExpectedDatesQuery, expectedReturnDate, expectedWithdrawalDate, requestId);
        return findByUUID(requestId).get();
    }

    @Override
    public LineRequest updateRealReturnDate(Timestamp realReturnDate, UUID requestId) {
        jdbcTemplate.update(updateLineRequestRealReturnDateQuery, realReturnDate, requestId);
        return findByUUID(requestId).get();
    }

    @Override
    public LineRequest updateRealWithdrawalDate(Timestamp realWithdrawalDate, UUID requestId) {
        jdbcTemplate.update(updateLineRequestRealWithdrawalDateQuery, realWithdrawalDate, requestId);
        return findByUUID(requestId).get();
    }

    @Override
    public LineRequest deleteByKey(UUID key) {
        jdbcTemplate.update(deleteLineRequestQuery, key);
        return new LineRequest(key);
    }

    @Override
    public LineRequest delete(LineRequest type) {
        return deleteByKey(type.getId());
    }

    public LineRequest mapperLineRequestFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = rs.getObject("id", UUID.class);
        Timestamp expectedReturnDate = rs.getTimestamp("expected_return_date");
        Timestamp realReturnDate = rs.getTimestamp("real_return_date");
        Timestamp expectedWithdrawalDate = rs.getTimestamp("expected_withdrawal_date");
        Timestamp realWithdrawalDate = rs.getTimestamp("real_withdrawal_date");
        RequestSituation requestSituation = RequestSituation.valueOf(rs.getString("request_situation"));
        UUID toolItemId = UUID.fromString(rs.getString("tool_item_id"));
        UUID requestId = UUID.fromString(rs.getString("request_id"));

        Request request = postgresRequestDAO.findByUUID(requestId).get();
        ToolItem toolItem = postgresToolItemDAO.findOne(toolItemId).get();

        return new LineRequest(id, request, toolItem, expectedReturnDate, realReturnDate, expectedWithdrawalDate, realWithdrawalDate, requestSituation);
    }

}
