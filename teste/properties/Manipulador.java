package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Manipulador {
	
	private final static String PATH_PROPERTIES = "/properties/config.properties";
	static String host, buffer, url; 
	static int port, tamanho_buffer;
		
	public static Properties getProp() throws IOException {
		
		InputStream input = Manipulador.class.getResourceAsStream(PATH_PROPERTIES);
		
		Properties props = new Properties();
		props.load(input);
		
		return props;
	}
		
	public static void CarregarDadosSensiveis()
	{		
		try 
		{
			Properties prop = getProp();
			
			setPort(Integer.parseInt(prop.getProperty("prop.port")));
			setTamanho_buffer(Integer.parseInt(prop.getProperty("prop.tamanho_buffer")));
			setHost(prop.getProperty("prop.host"));
			setBuffer(prop.getProperty("prop.buffer"));
			setUrl(prop.getProperty("prop.url"));
			
		} catch (IOException e) {
			System.out.println("MontarValores: " + e.toString());
		}
	}

	public static String getHost() {
		return host;
	}

	public static String getBuffer() {
		return buffer;
	}
	
	public static String getUrl() {
		return url;
	}

	public static int getPort() {
		return port;
	}

	public static int getTamanho_buffer() {
		return tamanho_buffer;
	}

	public static void setHost(String host) {
		Manipulador.host = host;
	}

	public static void setBuffer(String buffer) {
		Manipulador.buffer = buffer;
	}
	
	public static void setUrl(String url) {
		Manipulador.url = url;
	}


	public static void setPort(int port) {
		Manipulador.port = port;
	}

	public static void setTamanho_buffer(int tamanho_buffer) {
		Manipulador.tamanho_buffer = tamanho_buffer;
	}
}
