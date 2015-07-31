-- This script is meant to be run from Spring with the separator //
-- JDBC script runner doesn't allow use of DELIMITER statement...
use testdb//

-- Insert new usage
drop procedure if exists INSERT_AGGREGATED_USAGE//
CREATE PROCEDURE INSERT_AGGREGATED_USAGE (
       IN p_raw_event_id bigint,
       IN p_resource_uuid varchar(50),
       IN p_sid varchar(36),
       IN p_resource_type varchar(50),
       IN p_state varchar(10),
       IN p_aggregation_start bigint,
       IN p_aggregation_end bigint,
       IN p_aggregation_type varchar(20),
       OUT p_returned_id bigint
) BEGIN
  insert into testdb.aggregated_usage
  (raw_event_id, resource_uuid, sid, resource_type, state, aggregation_start, aggregation_end, aggregation_type)
  values (p_raw_event_id, p_resource_uuid, p_sid, p_resource_type, p_state,
  	 FROM_UNIXTIME(p_aggregation_start), FROM_UNIXTIME(p_aggregation_end), aggregation_type);

  -- TODO insert into the specific info tables and link
  select LAST_INSERT_ID() into p_returned_id;
END//
