package main.java;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class IosBasic_iPad extends IosBasic{

    @Before
    public void setUp() throws IOException, InterruptedException {
        new File(outputDirectory).mkdir();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPad Pro (9.7-inch)");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/maggie/git/WP-AppiumTest/demoApp/WhizzyPay.app");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        System.out.println("session started");

        Thread.sleep(2000);
        takeScreenshot(driver);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void test1_positiveFlow() throws IOException, InterruptedException {
        super.testA_positiveFlow();
    }

    @Test
    public void test2_exception_Flow() throws IOException, InterruptedException {
        super.testB_exception_Flow();
    }

    @Test
    public void test3_HorizontalScreen() throws IOException, InterruptedException {
        super.testC_HorizontalScreen();
    }
}

