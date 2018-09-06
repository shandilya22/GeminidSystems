import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.opencsv.CSVReader;


public class LoginAutomation {

	public static String driverPath, dataSheetPath, csvCell[], url, loginButton, productionButton, uname, pswd, salesforceLoginButton;
	public static WebDriver driver;
	public static CSVReader reader;
	public static Scanner sc;
	
	
	public static void main(String[] args) throws IOException {
		
		dataSheetPath = System.getProperty("user.dir")+"\\data.csv";
		dataSheetPath = dataSheetPath.replace('\\', '/');
		
		try
		{
			reader = new CSVReader(new FileReader(dataSheetPath));

			reader.readNext();
			csvCell = reader.readNext();

			url = csvCell[0];
			loginButton = csvCell[1];
			productionButton = csvCell[2];
			uname = csvCell[3];
			pswd = csvCell[4];
			salesforceLoginButton = csvCell[5];
			driverPath = csvCell[6];
			
			System.out.println("Choose the browser on which you want this script to run:-\n\n1.Google Chrome\n2.Firefox\n3.Internet Explorer\n");
			sc = new Scanner(System.in);
			switch(sc.nextInt())
			{
				case 1:
					System.setProperty("webdriver.chrome.driver", driverPath);
					driver = new ChromeDriver();
					break;
				case 2:
					System.setProperty("webdriver.gecko.driver", driverPath);
					driver = new FirefoxDriver();
					break;
				case 3:
					System.setProperty("webdriver.ie.driver", driverPath);
					driver = new InternetExplorerDriver();
					break;
				default :
					System.out.println("Invalid selection...Try again");
			}
			
			driver.get(url);
			driver.manage().window().maximize();	
			driver.findElement(By.id(loginButton)).click();
			driver.findElement(By.xpath(productionButton)).click();
			driver.findElement(By.id("username")).sendKeys(uname);
			driver.findElement(By.id("password")).sendKeys(pswd);
			driver.findElement(By.id(salesforceLoginButton)).click();
			driver.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
