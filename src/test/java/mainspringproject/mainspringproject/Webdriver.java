package mainspringproject.mainspringproject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Webdriver {
	
	//git
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
    WebDriverManager.chromedriver().setup();
    WebDriver driver=new ChromeDriver();
    driver.get("https://pratesting.cognizant.com/");
    driver.manage().window().maximize();
    
    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    
    WebElement ele1=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/header[1]/nav[1]/div[1]/div[1]/div[3]/div[1]/span[1]"));
    
    //hovering on left menu
    Actions act1=new Actions(driver);
    act1.moveToElement(ele1).perform();
    
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    
   // driver.findElement(By.xpath("//a[@class='nav_icon icon-Projects']")).click();
   // driver.findElement(By.xpath("/html[1]/body[1]/div[8]/div[3]/ul[1]/li[5]/span[1]")).click();
   // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    
    //clicking CFO_Onsite Project
    driver.findElement(By.xpath("//ul[@class='left_menu_items']//a[contains(text(),'CFO_Onsite')]")).click();
    
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    
    WebElement ele2=driver.findElement(By.xpath("//a[normalize-space()='Plan']"));
    
    //hovering on Plans
    Actions act2=new Actions(driver);
    act2.moveToElement(ele2).perform();
    
    //Clicking on Sprint
    driver.findElement(By.id("LOCK_Sprints")).click();
    
    WebElement ele3=driver.findElement(By.xpath("//div[normalize-space()='SPR1186']"));
    
    WebDriverWait wait2=new WebDriverWait(driver,30);
    wait2.until(ExpectedConditions.visibilityOf(ele3));
    
    //Clicking on Sprint SPR1186
    ele3.click();
    
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.switchTo().frame("contentframe");
    
    //To check Selected Sprint Appears
    try
    {
     String sprintName=driver.findElement(By.xpath("//input[@id='_Text_Check_CM_Name']")).getAttribute("value");
     //System.out.println(sprintName);
     String s="SPR1123";
     if(sprintName.equals(s))	
    	 System.out.println("Selected SPR1123 sprint appears,Test case Passed");
     else
    	 System.out.println("Selected SPR1123 sprint not appeared,Test case Failed");
    }catch(Exception e)
    {
    	 System.out.println("Selected SPR1123 sprint not appeared,Test case Failed");
    }
    
    
    //To check if saved button is disabled if no modification is done
    
    boolean isEnbled=driver.findElement(By.id("SaveBtn")).isEnabled();
    
    
    if(isEnbled)
    System.out.println("Button is enabled,Test case failed");
    else
    System.out.println("Button is disabled,Test case passed");
    driver.switchTo().defaultContent();
    
    
    //Check modify information is saved
    
    File f=new File("TestData//config.properties");
    FileInputStream fis=new FileInputStream(f);
    
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.switchTo().frame("contentframe");
    Properties prop = new Properties();
    prop.load(fis);
    driver.findElement(By.xpath("//input[@id='DN_NoofTeamsinvolved']")).clear();
    driver.findElement(By.xpath("//input[@id='DN_NoofTeamsinvolved']")).sendKeys(prop.getProperty("noOfTeamsInvolved"));
    
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.findElement(By.id("SaveBtn")).click();
    driver.switchTo().defaultContent();
    
    driver.switchTo().frame("contentframe");
    
    String modifydata=driver.findElement(By.id("DN_NoofTeamsinvolved")).getAttribute("value");
    int value=Integer.parseInt(modifydata);
    System.out.println("Modified value for No.of teams involved is "+value);
    if(value==2)
    	System.out.println("Modify Information is saved");
    else
    	System.out.println("Modify information is not saved");
   
    
    
    //Check Return Functionality
    
    driver.findElement(By.xpath("//input[@id='CancelBtn']")).click();
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    try
    {
    	driver.findElement(By.xpath("//div[normalize-space()='SPR1186']"));
    	System.out.println("Return functionality is working, Test case passed");
    }
    catch(Exception e)
    {
    	System.out.println("Return functionality is not working, Test case failed");
    }
    
    
    
    //Check navigate to sprint functionality
    
    driver.findElement(By.xpath("//div[normalize-space()='SPR1186']")).click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
    driver.switchTo().frame("contentframe");
    
    driver.findElement(By.xpath("//a[@id='Next_Item']")).click();
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    driver.switchTo().defaultContent();
    try
    {
    	driver.findElement(By.xpath("//span[@id='bCrumbSpanText']"));
    	System.out.println("Navigate to next sprint functionality working, Test case Passed");
    }
    catch(Exception e)
    {
    	System.out.println("Navigate to next sprint functionality not working, Test case Failed");
    }
    
    
    driver.close();
    
	}

}
