package main.java;

import com.google.common.io.Files;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class IosBasic {

    public static IOSDriver driver;
    public String validEmail = "maggie.she@wetech.io";
    public String validPassword = "SMAdmin1";
    public String invalidEmail1 = "invalid@wetech.io";
    public String invalidEmail2 = "invalid";
    public String randomPassword = "123alkjogEn";
    public String outputDirectory = "./outputs/";
    public String amount = "0.01";
    public String noteMessage = "Radom+Message=123890";
    public DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    public static WebElement payment, emailTextField, passwordTextField, backArrow, drawerIcon, aboutMenu, transactionMenu, logOut,
    loginButton, okButton, clearButton, crossButton, amountTextField, addNoteButton, createButton, noteTextField,
    cancelButton, doneButton, changeButton, refreshButton;

    @Before
    public void setUp() throws IOException, InterruptedException {
        new File(outputDirectory).mkdir();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/maggie/git/whizzypay-mobile-test/demoApp/WhizzyPay.app");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        System.out.println("session started");
        Thread.sleep(2000);
        takeScreenshot(driver);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("ending session");
        driver.quit();
    }

    /********************************************************************************
     * @ Testing on Vertical screen on iPhone 7
     * @ Test Positive Flow
     ********************************************************************************/

    @Test
    public void testA_positiveFlow() throws IOException, InterruptedException {
        logValidEmail();
        selectMerchant();
        createTransaction();
        nav_TranList_TranDetail();
        nav_TranDetail_TranList();
        openDrawer();
        open_About();
        about_To_TranList();
        logOut();
    }

    public void logValidEmail() throws IOException, InterruptedException {

        /*
         Given I am a new app user
         And I am a merchant admin/merchant user of only one merchant
         When I enter the correct email and password combination on the [login] page
         Then I expect to login to the system
         And the transaction history page is displayed
         */

        System.out.println("tapping login element");
        emailTextField = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
        emailTextField.sendKeys(validEmail);
        passwordTextField = driver.findElement(By.id("Password"));
        passwordTextField.sendKeys(validPassword);
        Thread.sleep(2000);
        takeScreenshot(driver);

        loginButton = driver.findElement(By.id("LOGIN"));
        loginButton.click();
        System.out.println("Successfully Login");
        Thread.sleep(2000);
        takeScreenshot(driver);

    }

    public void selectMerchant() throws IOException, InterruptedException {

        System.out.println("Select Merchant");
//        TouchAction artwok = new TouchAction(driver);
//        artwok.tap(69, 117).perform();
        WebElement artwok = driver.findElement(By.id("ARTWOK \uF00C"));
        artwok.click();
        Thread.sleep(2000);
        takeScreenshot(driver);
    }

    public void createTransaction() throws IOException, InterruptedException {

    /* [POSITIVE FLOW] Transactions History -> New Transaction */
        System.out.println("Start to create a transaction");
        crossButton = driver.findElement(By.id("create"));
        crossButton.click();
        takeScreenshot(driver);

        /* Transaction_Create -> Add a Note */
        System.out.println("Start to add a note");
        addNoteButton =driver.findElement(By.id("Add Note"));
        addNoteButton.click();
        takeScreenshot(driver);

        System.out.println("Cancel a note");
        cancelButton = driver.findElement(By.id("CANCEL"));
        cancelButton.click();
        takeScreenshot(driver);

        addNoteButton.click();
        noteTextField = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//XCUIElementTypeOther[@name=\"Edit Note DONE CANCEL\"])[3]/XCUIElementTypeOther[1]")));
        noteTextField.sendKeys(noteMessage);
        takeScreenshot(driver);

        doneButton = driver.findElement(By.id("DONE"));
        doneButton.click();
        System.out.println("Successfully add a note");
        takeScreenshot(driver);

        /* Edit Existing Note */
        System.out.println("Change note");
        changeButton = driver.findElement(By.id("Change"));
        changeButton.click();
        takeScreenshot(driver);
        doneButton.click();

        /* Create New Transaction */
        System.out.println("Enter an amount");
        amountTextField = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeOther[@name=\"$\"]/XCUIElementTypeOther")));
        amountTextField.sendKeys(amount);
        takeScreenshot(driver);
        System.out.println("Click create button");
        createButton = driver.findElement(By.id("Create"));
        createButton.click();
        takeScreenshot(driver);
        Thread.sleep(2000);

        backArrow = driver.findElement(By.name("drawerIcon"));
        backArrow.click();
    }

    public void nav_TranList_TranDetail() throws IOException, InterruptedException {

        /*
         Given I am on the [Transaction History] page
         When I click a payment
         Then I expect to navigate to the [Transaction Details] page
         */

        System.out.println("tapping a payment");
        payment = driver.findElement(MobileBy.iOSNsPredicateString("name CONTAINS 'T'"));
        payment.click();
        Thread.sleep(2000);
        takeScreenshot(driver);
    }

    /*
     Given I am on the [Transaction Details] page
     When I click the back arrow button on the top-left corner of the page
     Then I expect to navigate to the [Transaction History] page
     */

    public void nav_TranDetail_TranList() throws IOException, InterruptedException {
        System.out.println("tapping a backArrow on Transaction Detail page");
        backArrow = driver.findElement(By.name("drawerIcon"));
        backArrow.click();
        Thread.sleep(2000);
        takeScreenshot(driver);
    }

    public void openDrawer() throws IOException, InterruptedException {

        /*
         Give I have logged on to the App
         When I click the hamburger menu on the top left corner
         Then I expect to see the [Menu] List
         */

        System.out.println("Open the drawer nemu");
        drawerIcon = driver.findElement(By.name("drawerIcon"));
        drawerIcon.click();
        takeScreenshot(driver);
    }

    public void open_About() throws IOException, InterruptedException {

        /*
         Given I am on the [Menu List] page
         When I click the About button on the [Menu List]
         Then I expect to navigate to the [About] page
         */

        System.out.println("Navigate to About page");
        aboutMenu = driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"\uF311 About\"])[2]"));
        aboutMenu.click();
        Thread.sleep(2000);
        takeScreenshot(driver);
    }

    public void about_To_TranList() throws IOException, InterruptedException {

        /*
         Give I am on the [About] page
         When I click the hamburger menu on the top left corner
         And I click the Transactions button on the [Menu List]
         Then I expect to navigate to the [Transaction History] page
         */

        System.out.println("Navigate to Transaction History");
        openDrawer();
        Thread.sleep(2000);
        takeScreenshot(driver);
        transactionMenu = driver.findElement(By.name("\uF389 Transactions"));
        transactionMenu.click();
        Thread.sleep(2000);
        takeScreenshot(driver);
    }

    public void logOut() throws IOException, InterruptedException {

        /*
         Give I am on the [Transaction History] page
         When I click the hamburger menu on the top left corner
         And I click the Log out button on the [Menu List]
         Then I expect to see a log out confirm popup modal
         */

        openDrawer();
        System.out.println("Log out");
        logOut = driver.findElement(By.name("\uF359 Log Out"));
        logOut.click();
        Thread.sleep(2000);
        takeScreenshot(driver);

        /*
         Give I am on the logOut confirm popup modal
         When I click the <button> on the popup modal
         Then I expect to see the <result>
         |button|result|
         |cancel|dismiss the popup modal|
         |ok|log out from the app|
         */

        System.out.println("cancel log out ");
        WebElement cancel = driver.findElement(By.id("Cancel"));
        cancel.click();
        Thread.sleep(2000);
        takeScreenshot(driver);
        logOut.click();
        System.out.println("confirm log out ");
        WebElement done = driver.findElement(By.id("OK"));
        done.click();
        Thread.sleep(2000);
        takeScreenshot(driver);
    }




    /********************************************************************************
     * @ Testing on Vertical screen on iPhone 7
     * @ Test Exception Flow
     ********************************************************************************/

    @Test
    public void testB_exception_Flow() throws IOException, InterruptedException {
        login_invalidEmail();
    }

    public void login_invalidEmail() throws IOException, InterruptedException {

        /*
         Given I am a merchant admin/merchant user of only one merchant
         When I enter the incorrect email and password on the [login] page
         Then I expect to see a popup modal with the following message
         And the transaction history page is displayed
         And I click the ok button to dismiss the popup
         */

        System.out.println("Login with invalid email1");
        emailTextField = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
        emailTextField.sendKeys(invalidEmail1);
        passwordTextField = driver.findElement(By.id("Password"));
        passwordTextField.sendKeys(randomPassword);
        Thread.sleep(2000);
        takeScreenshot(driver);

        loginButton = driver.findElement(By.id("LOGIN"));
        loginButton.click();
        System.out.println("Login Failed");
        Thread.sleep(2000);
        takeScreenshot(driver);

        okButton = driver.findElement(By.name("OK"));
        okButton.click();

        clearButton = driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"\uF023 \uF057\"]/XCUIElementTypeOther[2]"));
        clearButton.click();

        TouchAction emailTestField = new TouchAction(driver);
        emailTestField.tap(219, 238).perform();

        clearButton.click();
        Thread.sleep(2000);
        takeScreenshot(driver);
    }

    /********************************************************************************
     * @ Testing on horizontal screen on iPhone 7
     ********************************************************************************/
    @Test
    public void testC_HorizontalScreen() throws IOException, InterruptedException {
        /* Rotate the screen to horizontal*/
        System.out.println("Rotate screen to horizontal");
        driver.rotate(ScreenOrientation.LANDSCAPE);
        logValidEmail();
        selectMerchant();
        createTransaction();
        nav_TranList_TranDetail();
        nav_TranDetail_TranList();
        openDrawer();
        open_About();
        about_To_TranList();
        logOut();
    }

    public void takeScreenshot(IOSDriver driver) throws IOException {
        System.out.println("take screenshot");
        String fileName = "WhizzyPay-" + MobileCapabilityType.PLATFORM_NAME + "-" + MobileCapabilityType.DEVICE_NAME +
                "-screenshot-" + System.currentTimeMillis() + ".png";
        File destination = new File(outputDirectory, fileName);
        File screenShot = driver.getScreenshotAs(OutputType.FILE);
        Files.move(screenShot, destination);
    }
}
