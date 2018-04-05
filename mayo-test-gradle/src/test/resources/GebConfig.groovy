package resources
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import geb.report.*

// default driver
//System.setProperty('webdriver.chrome.driver', "src/test/resources/chromedriver.exe")
driver = { new FirefoxDriver() }

environments {
	chrome {
		def chromeDriver = 'src/test/resources/chromedriver.exe'
		System.setProperty('webdriver.chrome.driver', chromeDriver)
		driver = { new ChromeDriver() }
		driver.manage().window().maximize()
	}

	firefox {
/*		def ffDriver = 'src/test/resources/geckodriver.exe'
		System.setProperty('webdriver.Firefox.driver', ffDriver)*/
        driver = { new FirefoxDriver() }
        driver.manage().window().maximize()
	}
}

baseUrl = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/"
reporter = new CompositeReporter(new ScreenshotReporter(), new FramesSourceReporter())
waiting {
	timeout = 6
	retryInterval = 0.5
	slow { timeout = 12 }
	reallySlow { timeout = 24 }
}

reportsDir = "target/geb-reports"
reportOnTestFailureOnly = true

