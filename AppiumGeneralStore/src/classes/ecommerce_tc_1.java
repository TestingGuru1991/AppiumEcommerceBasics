package classes;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ecommerce_tc_1 extends HybridBase {

	public static void main(String[] args) throws MalformedURLException {

		AndroidDriver<AndroidElement> driver = Capabilities("emulator");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		
		//Keyboard must hide before anything else
		driver.hideKeyboard();
		
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		
		//Select drop down for country
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		
		//Scroll until Argentina is not found
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		//Click on Argentina when it is visible
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		
		//Click on btn lets shop
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
	}


}
