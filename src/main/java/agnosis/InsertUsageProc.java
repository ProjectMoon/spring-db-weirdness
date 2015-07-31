package agnosis;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class InsertUsageProc extends StoredProcedure {
    public static final String SPROC = "INSERT_AGGREGATED_USAGE";

    //input params
    public static final String RAW_EVENT_ID = "p_raw_event_id";
    public static final String RESOURCE_UUID = "p_resource_uuid";
    public static final String SID = "p_sid";
    public static final String RESOURCE_TYPE = "p_resource_type";
    public static final String STATE = "p_state";
    public static final String AGGREGATION_START = "p_aggregation_start";
    public static final String AGGREGATION_END = "p_aggregation_end";
    public static final String AGGREGATION_TYPE = "p_aggregation_type";

    //output params
    public static final String RETURNED_ID = "p_returned_id";

    public InsertUsageProc(DataSource ds) {
        super(ds, SPROC);
        declareParameter(new SqlParameter(RAW_EVENT_ID, Types.BIGINT));
        declareParameter(new SqlParameter(RESOURCE_UUID, Types.VARCHAR));
        declareParameter(new SqlParameter(SID, Types.VARCHAR));
        declareParameter(new SqlParameter(RESOURCE_TYPE, Types.VARCHAR));
        declareParameter(new SqlParameter(STATE, Types.VARCHAR));
        declareParameter(new SqlParameter(AGGREGATION_START, Types.BIGINT));
        declareParameter(new SqlParameter(AGGREGATION_END, Types.BIGINT));
        declareParameter(new SqlParameter(AGGREGATION_TYPE, Types.VARCHAR));
        declareParameter(new SqlOutParameter(RETURNED_ID, Types.BIGINT));
        compile();
    }

    /**
     * Insert an aggregated usage record. Sets the ID property of the AggregatedUsage
     * object after a successful insert. Also returns that ID.
     * @param usage
     * @return The ID of the inserted record.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public long exec(AggregatedUsage usage) {
        assert usage.getId() == null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(RAW_EVENT_ID, usage.getRawEventId());
        params.put(RESOURCE_UUID, usage.getResourceUuid());
        params.put(SID, usage.getSid());
        params.put(RESOURCE_TYPE, usage.getResourceType().toString());
        params.put(STATE, usage.getState().toString());
        params.put(AGGREGATION_START, usage.getAggregationStart());
        if (usage.getAggregationEnd() != null) params.put(AGGREGATION_END, usage.getAggregationEnd());
        else params.put(AGGREGATION_END, null);
        params.put(AGGREGATION_TYPE, usage.getAggregationType().toString());

        Map<String, Object> results = super.execute(params);
        long id = (long)results.get(RETURNED_ID);

        usage.setId(id);
        return id;
    }
}
