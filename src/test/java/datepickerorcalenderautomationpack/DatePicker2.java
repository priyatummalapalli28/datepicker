package datepickerorcalenderautomationpack;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePicker2
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
		driver.get("http://demo.automationtesting.in/Datepicker.html");
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("datepicker2")));
		WebElement e=driver.findElement(By.id("datepicker2"));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("datepicker2")));
		driver.executeScript("arguments[0].scrollIntoView();",e);
		e.click();
		//Year
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Change the year']")));
		while(2>1)
		{
			WebElement e1=driver.findElement(By.xpath("//*[@title='Change the year']"));
			Select s1=new Select(e1);
			List<WebElement> l1=s1.getOptions();
			int lyear=Integer.parseInt(l1.get(1).getText());
			int hyear=Integer.parseInt(l1.get(l1.size()-2).getText());
			if(eyear<lyear)
			{
				l1.get(0).click();
			}
			else if(eyear>hyear)
			{
				l1.get(l1.size()-1).click();
			}
			else
			{
				s1.selectByVisibleText(""+eyear+"");
				break;
			}
		}
		//Month
		WebElement e2=driver.findElement(By.xpath("//*[@title='Change the month']"));
		Select s2=new Select(e2);
		s2.selectByVisibleText(emonth);
		//Day
		driver.findElement(By.xpath("//*[text()='"+eday+"']")).click();
		Thread.sleep(3000);
		//Close site
		driver.close();
	}
}
