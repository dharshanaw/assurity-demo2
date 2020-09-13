package com.assurity.demo2.tests;

import com.assurity.automation.engine.FrameworkContext;
import com.assurity.automation.engine.context.TestContext;
import com.assurity.automation.engine.dataprovider.TestNgDataProvider;
import com.assurity.automation.utilities.FrameworkBaseTest;
import com.assurity.automation.utilities.rest.GenericRestClient;

import com.assurity.demo2.service.model.Employee;
import org.springframework.util.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.util.Map;

public class EmployeeServiceTest extends FrameworkBaseTest {
    GenericRestClient genericRestClient;

    @BeforeTest
    public void initTest() throws URISyntaxException {
        TestContext context = new TestContext();
        String endpoint = context.getDefaultEndpoint();
        FrameworkContext frameworkContext = new FrameworkContext();
        genericRestClient = new GenericRestClient(endpoint);
    }

    @Test
    public void addEmployeeTestcase() {
        Employee employee = new Employee();
        employee.setId(1234);
        employee.setEmail("dkasunw@gmail.com");
        employee.setFirstName("user");
        employee.setLastName("demo");
        genericRestClient.doPost("/employees/adduser/", employee);
        Assert.notNull(getResponse(), "List is empty, Employee not added");
    }

    @Test(dataProvider = "assurity.testng.dataprovider", dataProviderClass = TestNgDataProvider.class)
    @CustomAttribute(name = "dataProvider", values = {"assurity.xsls.dataprovider"})
    public void addEmployeeDataProviderTestcase(Object data) {
        Map<String, Object> data2 = (Map<String, Object>) data;

        Employee employee = new Employee();
        employee.setId((int) Double.parseDouble(data2.get("id").toString()));
        employee.setEmail(data2.get("email").toString());
        employee.setFirstName(data2.get("name").toString());
        employee.setLastName("demo");
        Object response = genericRestClient.doPost("/employees/adduser/", employee);
        Assert.notNull(response, "Empty Response with no return status");
    }

//    @Test(dataProvider = "assurity.testng.dataprovider", dataProviderClass = TestNgDataProvider.class)
//    @CustomAttribute(name = "dataProvider", values = {"assurity.mysql.dataprovider"})
//    public void addEmployeeDataProviderTestcase2(Object data) {
//        Map<String, Object> data2 = (Map<String, Object>) data;
//
//        Employee employee = new Employee();
//        employee.setId((int) Double.parseDouble(data2.get("id").toString()));
//        employee.setEmail(data2.get("email").toString());
//        employee.setFirstName(data2.get("name").toString());
//        employee.setLastName("demo");
//        Object response = genericRestClient.doPost("/employees/adduser/", employee);
//        Assert.notNull(response, "Empty Response with no return status");
//    }

    @Test
    public void getEmployeeTestcase() {
        Object response = genericRestClient.doGetRequest("/employees/getuser/");
        Assert.notNull(response, "Empty Response with no return status");
    }

    private Object getResponse() {
        Object response = genericRestClient.doGetRequest("/employees/getuser/");
        return response;
    }
}
