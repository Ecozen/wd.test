package topdeep.autotest.entity.data;

import java.util.Date;

public class AtTestData extends AtTestDataKey {



    private String dataValue;



    public AtTestData(String testDataGroupId, String dataKey, String dataValue) {
        super(testDataGroupId, dataKey);
        this.dataValue = dataValue;
    }


    public AtTestData() {
        super();
    }


    public String getDataValue() {
        return dataValue;
    }


    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }



}