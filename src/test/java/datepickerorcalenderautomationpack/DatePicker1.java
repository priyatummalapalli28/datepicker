package datepickerorcalenderautomationpack;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker1
{
	public static void main(String[] args) throws Exception
	{
		//Get expected date from keyboard in terms of day,month and year
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter day");
		int eday=Integer.parseInt(sc.nextLine());
		System.out.println("Enter month in Initcap with complete name");
		String emonth=sc.nextLine();
		System.out.println("Enter year in 4 digits");
		int eyear=sc.nextInt();
		sc.close();
		//Open browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		//Maximize
		driver.manage().window().maximize();
		//launch site
		driver.get("https://jqueryui.com/");
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Datepicker")));
		driver.findElement(By.linkText("Datepicker")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='Datepicker'])[1]")));
		driver.switchTo().frame(0);
		WebElement e=driver.findElement(By.id("datepicker"));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("datepicker")));
		driver.executeScript("arguments[0].scrollIntoView();",e);
		e.click();
		//Year
		while(2>1)
		{
			String temp1=driver.findElement(By.xpath("//*[@class='ui-datepicker-year']")).getText();
			int ayear=Integer.parseInt(temp1);
			if(eyear<ayear)
			{
				driver.findElement(By.xpath("//*[text()='Prev']")).click();
			}
			else if(eyear>ayear)
			{
				driver.findElement(By.xpath("//*[text()='Next']")).click();
			}
			else
			{
				//Month(Come to January)
				while(2>1)
				{
					String amonth=driver.findElement(By.xpath("//*[@class='ui-datepicker-month']")).getText();
					if(!amonth.equals("January"))
					{
						driver.findElement(By.xpath("//*[text()='Prev']")).click();
					}
					else
					{
						break;
					}
				}
				//Come to expected month from January
				while(2>1)
				{
					String amonth=driver.findElement(By.xpath("//*[@class='ui-datepicker-month']")).getText();
					if(!amonth.equalsIgnoreCase(emonth))
					{
						driver.findElement(By.xpath("//*[text()='Next']")).click();
					}
					else
					{
						break;
					}
				}
				break;
			}	
		}
		//Day
		driver.findElement(By.xpath("//*[text()='"+eday+"']")).click();
		Thread.sleep(3000);
		//Close site
		driver.close();
	}
}
