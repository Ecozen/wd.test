package topdeep.autotest.entity.data;

public class AtTestDataKey {
    private String testDataGroupId;

    private String dataKey;

    public AtTestDataKey(String testDataGroupId, String dataKey) {
        this.testDataGroupId = testDataGroupId;
        this.dataKey = dataKey;
    }

    public AtTestDataKey() {
        super();
    }
    
    public String getTestDataGroupId() {
        return testDataGroupId;
    }


    public void setTestDataGroupId(String testDataGroupId) {
        this.testDataGroupId = testDataGroupId == null ? null : testDataGroupId.trim();
    }


    public String getDataKey() {
        return dataKey;
    }


    public void setDataKey(String dataKey) {
        this.dataKey = dataKey == null ? null : dataKey.trim();
    }
}