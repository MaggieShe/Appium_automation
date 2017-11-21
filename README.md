# Development

## Intellij
### Add libraries:
    - hamcrest-core-1.3.jar
    - java-client-5.0.4.jar
    - junit-4.12.jar
    - selenium-server-standalone-3.6.0.jar

##  Appium-desktop
### Install appium-desktop: https://github.com/appium/appium-desktop/releases/tag/v1.2.6
### Config Desired Capabilities:
    - platformName, text, iOS
    - platformVersion, text,  <ios Version, e.g. 11.0>
    - deviceName, text, <device type, e.g. iPhone 7, iPad Pro 9.1-inch>
    - noReset, boolean, true
    - automationName, text, XCUITest
    - app, filepath, <the filepath of whizzypay-mobile app>


## Get started
- Run appium-desktop by clicking Start Session
- Run src/main/java/IosBasic to run automation testing on iPhone 7
- Run src/main/java/IosBasic_iPad to run automation testing on iPad Pro 9.7-inch
