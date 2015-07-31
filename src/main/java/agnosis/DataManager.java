package agnosis;

import javax.sql.DataSource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DataManager {
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public long insertUsage(AggregatedUsage usage) {
        InsertUsageProc proc = new InsertUsageProc(dataSource);
        long id = proc.exec(usage);
        return id;
    }
}
