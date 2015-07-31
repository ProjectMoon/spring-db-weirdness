package agnosis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import agnosis.AggregatedUsage.AggregatedRecordState;
import agnosis.AggregatedUsage.AggregationType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/UnitTest-Context.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AppTest {
    @Inject
    public DataManager dataManager;
    
    public static long getTimestamp(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        return formatter.parse(date).getTime() / 1000L;
    }
    
    @Test
    public void testInsertUsage() throws ParseException {
        long timestamp = getTimestamp("2015-07-05 00:00:00");
        AggregatedUsage usage = new AggregatedUsage();
        usage.setRawEventId(1);
        usage.setResourceUuid("i-abc123");
        usage.setResourceType(ResourceType.TYPE_1);
        usage.setState(AggregatedRecordState.ONGOING);
        usage.setAggregationType(AggregationType.TYPE_1);
        usage.setAggregationStart(timestamp);
        usage.setAggregationEnd(null);

        long id = dataManager.insertUsage(usage);
        Assert.assertNotNull(usage.getId());
        //it's always ID 1 because spring resets for us.
        Assert.assertEquals(1L, id);
        Assert.assertEquals(1L, usage.getId().longValue());
    }
}
