package classes;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.Assert;

public class eccomerce_tc_3 extends HybridBase {
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
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
		
		//Show the whole element with all btn's from parent element, scroll until it is not completely shown
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));
		
		//There could be more 'add to chart' buttons and we want to click on exact one 'Jordan 6 Rings'
		int count_add_to_chart = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		for(int i=0; i<count_add_to_chart; i++) {
			String text_item = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			System.out.println(text_item);
			if(text_item.equals("Jordan 6 Rings")) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		
		//Click on button Cart
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(1000);
		
		String last_page_text = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals("Jordan 6 Rings", last_page_text);
	}
}
