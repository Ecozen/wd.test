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

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column at_test_data.DATA_VALUE
     *
     * @return the value of at_test_data.DATA_VALUE
     *
     * @mbggenerated
     */
    public String getDataValue() {
        return dataValue;
    }


    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }



}