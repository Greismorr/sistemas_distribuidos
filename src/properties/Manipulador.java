package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulador {
	
	private final static String PATH_PROPERTIES = "src\\\\properties\\\\config.properties";
	static String host, buffer; 
	static int port, tamanho_buffer;
		
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(PATH_PROPERTIES);
		props.load(file);
		return props;
	}
	
	public static void main(String[] args) throws IOException {
		
		Properties prop = getProp();

		host = prop.getProperty("prop.host");
		port =  Integer.parseInt(prop.getProperty("prop.port"));
		buffer = prop.getProperty("prop.buffer");
		tamanho_buffer = Integer.parseInt(prop.getProperty("prop.tamanho_buffer"));
	}
	
	public static String getHost()
	{
		return Manipulador.host;
	}
	
	public static int getPort()
	{
		return Manipulador.port;
	}
	
	public static String getBuffer()
	{
		return Manipulador.buffer;
	}
	
	public static int getTamanhoBuffer()
	{
		return Manipulador.tamanho_buffer;
	}
}
