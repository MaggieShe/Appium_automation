# How to install Appium inspector? 
  https://github.com/appium/appium-desktop#the-inspector  


# How to generate a ios simulator application?
  exp build:ios -t simulator
  exp build:status
  
# How to find the element? 
  -- Appium inspectior
  -- Java:
      driver.findElement(MobileBy.iOSNsPredicateString("name CONTAINS 'Payment'"));
      driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"\uF060\"])[2]"));
      driver.findElement(By.id("LOGIN"));
  
  
