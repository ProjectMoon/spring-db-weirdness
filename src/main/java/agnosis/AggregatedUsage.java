package agnosis;

import java.util.UUID;

public class AggregatedUsage {
    public static enum AggregatedRecordState {
        ONGOING,
        FINISHED
    }

    public static enum AggregationType {
        TYPE_1
    }

    private Long id; //database id
    private long rawEventId; //first raw event used to create this usage.
    private String uuid; //resource uuid
    private String sid; //uuid identifier for this aggregated record
    private ResourceType resourceType; //basically what table we join to for specific info
    private AggregatedRecordState state;

    private AggregationType aggregationType;

    //The times between which this aggregation chunk exists.
    //End is null if usage is ongoing.
    private long aggregationStart;
    private Long aggregationEnd;

    public AggregatedUsage() {
        this.sid = UUID.randomUUID().toString();
    }

    public String toString() {
        return "Usage[type=" + getResourceType().toString() + ", start=" + getAggregationStart() + ", "
                + "end=" + getAggregationEnd() + ", start=" + getAggregationStart() + "]";
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType type) {
        this.resourceType = type;
    }

    public String getResourceUuid() {
        return uuid;
    }

    public void setResourceUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getAggregationStart() {
        return aggregationStart;
    }

    public void setAggregationStart(long aggregationRangeStart) {
        this.aggregationStart = aggregationRangeStart;
    }

    public Long getAggregationEnd() {
        return aggregationEnd;
    }

    public void setAggregationEnd(Long aggregationRangeEnd) {
        this.aggregationEnd = aggregationRangeEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AggregatedRecordState getState() {
        return state;
    }

    public void setState(AggregatedRecordState state) {
        this.state = state;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public long getRawEventId() {
        return rawEventId;
    }

    public void setRawEventId(long rawEventId) {
        this.rawEventId = rawEventId;
    }

    public AggregationType getAggregationType() {
        return aggregationType;
    }

    public void setAggregationType(AggregationType aggregationType) {
        this.aggregationType = aggregationType;
    }
}
