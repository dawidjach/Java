package src.abw.updater;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDateiEinlesen {
//	public String remoteConfig = "/home/jach/sieve-datei-updater/conf/remoteConfig";
	public String remoteConfig;
	public String dateienVerzeichnis;
	
	public void propertiesEinlesen() throws FileNotFoundException, IOException {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String configPath = rootPath + "config.properties";
//		String configPath = "/home/jach/workspace/sieve-datei-updater/config.properties";

		Properties config = new Properties();
		config.load(new FileInputStream(configPath));
		remoteConfig = config.getProperty("remoteConfig");
		dateienVerzeichnis = config.getProperty("dateienVerzeichnis");
	}
}
