import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.opencsv.CSVReader;


public class LoginAutomation {

	public static String chromeDriverPath, dataSheetPath, csvCell[], url, loginButton, productionButton, uname, pswd, salesforceLoginButton;
	public static WebDriver driver;
	public static CSVReader reader;
	
	
	public static void main(String[] args) throws IOException {
		
		chromeDriverPath = System.getProperty("user.dir")+"\\chromedriver.exe";
		chromeDriverPath = chromeDriverPath.replace('\\', '/');
		
		dataSheetPath = System.getProperty("user.dir")+"\\data.csv";
		dataSheetPath = dataSheetPath.replace('\\', '/');
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		
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
		
			driver = new ChromeDriver();
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
