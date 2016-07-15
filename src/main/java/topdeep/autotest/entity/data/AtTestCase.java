package topdeep.autotest.entity.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtTestCase  {

    private String id;


    private String superAdminId;


    private String testProjectId;


    private String testName;

    private List<AtUserCaseAction> actionlist;


    public AtTestCase(String id, String superAdminId, String testProjectId, String testName) {
        this.id = id;
        this.superAdminId = superAdminId;
        this.testProjectId = testProjectId;
        this.testName = testName;
        this.actionlist= new ArrayList<AtUserCaseAction>();
    }

    public void addActions(AtUserCaseAction action){
    	actionlist.add(action);
    	
    }
    
    public List<AtUserCaseAction> getActionlist(){
    	return actionlist;
    }
    
    public AtTestCase() {
        super();
        this.actionlist= new ArrayList<AtUserCaseAction>();
    }

   
    public String getId() {
        return id;
    }

  
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

  
    public String getSuperAdminId() {
        return superAdminId;
    }

    public void setSuperAdminId(String superAdminId) {
        this.superAdminId = superAdminId == null ? null : superAdminId.trim();
    }

    public String getTestProjectId() {
        return testProjectId;
    }

    public void setTestProjectId(String testProjectId) {
        this.testProjectId = testProjectId == null ? null : testProjectId.trim();
    }

    public String getTestName() {
        return testName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column at_test_case.TEST_NAME
     *
     * @param testName the value for at_test_case.TEST_NAME
     *
     * @mbggenerated
     */
    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }
   
}
