package classes;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class eccomerce_tc_4 extends HybridBase {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		AndroidDriver<AndroidElement> driver = Capabilities("emulator");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Enter name
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		
		//Keyboard must hide before anything else
		driver.hideKeyboard();
		
		//Click on btn lets shop
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//Add two items to cart
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		
		//Add second item
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Air Jordan 1 Mid SE\").instance(0))"));
		
		//There could be more 'add to chart' buttons and we want to click on exact one 'Jordan 6 Rings'
		int count_add_to_chart = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		for(int i=0; i<count_add_to_chart; i++) {
			String text_item = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(text_item.equals("Air Jordan 1 Mid SE")) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		//End of adding second item
		
		//Click on cart
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(1000);
		
		//Take amount
		String amount_1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
		//Now remove $ sign
		amount_1.substring(1);
		double amount_double_1 = Double.parseDouble(amount_1);
		
		String amount_2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
		//Now remove $ sign
		amount_2.substring(1);
		double amount_double_2 = Double.parseDouble(amount_2);
		
		//sum two amounts
		double sum = amount_double_1 + amount_double_2;
		
		String total_amount_string = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLb1")).getText();
		total_amount_string.substring(1);
		double total_amount_double = Double.parseDouble(total_amount_string);
		
		//Check if sum of two amount is equal to amount writen in app
		Assert.assertEquals(sum, total_amount_double);
		
	}
}
