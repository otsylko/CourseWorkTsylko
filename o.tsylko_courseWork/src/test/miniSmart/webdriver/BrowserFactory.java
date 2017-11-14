package miniSmart.webdriver;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.NamingException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.opera.core.systems.OperaDriver;

/**
 * The class-initializer-based browser string parameter.
 */
public abstract class BrowserFactory {
	public static PropertiesResourceManager propResMang = null;

	/**
	 * Setting up Driver
	 * @param type Browser type
	 * @return RemoteWebDriver
	 */
	public static RemoteWebDriver setUp(final Browser.Browsers type) {
		
		DesiredCapabilities capabilitiesProxy = null;
		RemoteWebDriver driver = null;
		File myFile = null;
		switch (type) {
		case CHROME:
			URL myTestURL = ClassLoader.getSystemResource("chromedriver.exe");
			try {
				myFile = new File(myTestURL.toURI());
			} catch (URISyntaxException e1) {
				Logger.getInstance().debug(e1.getMessage());
			}
			System.setProperty("webdriver.chrome.driver", myFile.getAbsolutePath());
			//Added by Tsylko
			HashMap<String, Object> preferences = new HashMap<String, Object>();
			preferences.put("profile.default_content_settings.popups", 0);
			//preferences.put("download.default_directory", Browser.downloadPath);
			preferences.put("download.directory_upgrade", true);
			preferences.put("download.prompt_for_download", "false");
			preferences.put("safebrowsing.enabled", "true");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", preferences);
			options.addArguments("--lang=" + Browser.language);
			options.addArguments("--incognito");
			if (Browser.language.equals("ru")){
				propResMang = new PropertiesResourceManager("localization/loc_ru.properties");
			}
			if (Browser.language.equals("en")){
				propResMang = new PropertiesResourceManager("localization/loc_en.properties");
			}
			DesiredCapabilities cp1 = DesiredCapabilities.chrome();
			cp1.setCapability("chrome.switches", Arrays.asList("--disable-popup-blocking"));
			cp1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cp1.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(cp1);
			break;
			
		case FIREFOX:
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			URL myTestURLF = ClassLoader.getSystemResource("geckodriver.exe");
			try {
				myFile = new File(myTestURLF.toURI());
			} catch (URISyntaxException e1) {
				Logger.getInstance().debug(e1.getMessage());
			}
			//Added by Tsylko
			System.setProperty("webdriver.gecko.driver", myFile.getAbsolutePath());
			FirefoxProfile profile = new FirefoxProfile();
			//profile.setPreference("browser.download.dir", Browser.downloadPath);
			//profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/octet-stream;");
			profile.setPreference("browser.download.manager.showWhenStarting", false );
			profile.setPreference("browser.privatebrowsing.autostart", true);
			profile.setPreference("intl.accept_languages",Browser.language);
			if (Browser.language.equals("ru")){
				propResMang = new PropertiesResourceManager("localization/loc_ru.properties");
			}
			if (Browser.language.equals("en")){
				propResMang = new PropertiesResourceManager("localization/loc_en.properties");
			}
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new FirefoxDriver(capabilities);
			break;
			
		case IEXPLORE:
			//local security request flag INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS
			//(but this flag may cause appearing "skipped" tests)
			if(new PropertiesResourceManager(Browser.PROPERTIES_FILE).getProperty("localrun").equalsIgnoreCase("true")){
				DesiredCapabilities cp = DesiredCapabilities.internetExplorer();
				cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				URL myTestURL2 = ClassLoader.getSystemResource("IEDriverServer.exe");
				try {
					myFile = new File(myTestURL2.toURI());
				} catch (URISyntaxException e1) {
					Logger.getInstance().debug(e1.getMessage());
				}
				System.setProperty("webdriver.ie.driver", myFile.getAbsolutePath());
				driver = new InternetExplorerDriver(cp);
			// better to avoid
			}else{
				// now remote connection will be refused, so use selenium server instead
				driver = new InternetExplorerDriver();
			}
			break;
			
		case OPERA:
			//work on v.11-12 (Presto engine)
			
			driver = new OperaDriver();
			break;
		case SAFARI:
			//work on v.5.1+
			
			driver = new SafariDriver();
			break;
		default:
			break;
		}
		return driver;
	}

	/**
	 * Setting up Driver
	 * @param type Browser type
	 * @return RemoteWebDriver
	 * @throws NamingException NamingException
	 */
	public static RemoteWebDriver setUp(final String type) throws NamingException {
		for (Browser.Browsers t : Browser.Browsers.values()) {
			if (t.toString().equalsIgnoreCase(type)) {
				return setUp(t);
			}
		}
		throw new NamingException(Logger.getLoc("loc.browser.name.wrong")+":\nchrome\nfirefox\niexplore\nopera\nsafari");
	}
}
