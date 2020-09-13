package com.assurity.demo2.tests;

import com.assurity.automation.engine.FrameworkContext;
import com.assurity.automation.engine.context.TestContext;
import com.assurity.automation.engine.dataprovider.TestNgDataProvider;
import com.assurity.automation.utilities.FrameworkBaseTest;
import com.assurity.automation.utilities.rest.GenericRestClient;
import org.springframework.util.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URISyntaxException;


public class InitTest extends FrameworkBaseTest {
    GenericRestClient genericRestClient;
    @Test
    public void initTest() throws URISyntaxException {
        TestContext context = new TestContext();
        String endpoint = context.getDefaultEndpoint();
        genericRestClient = new GenericRestClient(endpoint);
    }

    @Test
    public void firstTestMethod() throws Exception {
        //TODO: Your test method comes here
    }

    @Test(dataProvider = "assurity.testng.dataprovider", dataProviderClass = TestNgDataProvider.class)
    public void firstDataDrivenTestMethod(Object name, Object id) throws Exception {
        //TODO: Your data driven test method comes here
    }

}
