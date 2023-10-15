package src.abw.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDateiEinlesen {
//	public String remoteConfig = "/home/jach/client-abwesenheit/conf/remoteConfig";
	public String remoteConfig;
	public String nutzernamenListe;
	public String abwListe;
	public String sieve;
	
	public void propertiesEinlesen() throws FileNotFoundException, IOException {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String configPath = rootPath + "config.properties";
//		String configPath = "/home/jach/workspace/client-abwesenheit/config.properties";

		Properties config = new Properties();
		config.load(new FileInputStream(configPath));
		
		remoteConfig = config.getProperty("remoteConfig");
		nutzernamenListe = config.getProperty("nutzernamenListe");
		abwListe = config.getProperty("abwListe");
		sieve = config.getProperty("sieve");
	}
}
