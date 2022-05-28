package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Manipulador {
	
	private final static String PATH_PROPERTIES = "src\\properties\\config.properties";
	static String host, buffer, url; 
	static int port, tamanho_buffer;
		
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(PATH_PROPERTIES);
		props.load(file);
		
		return props;
	}
		
	public static void CarregarDadosSensiveis()
	{
		Properties prop;
		
		try {
			prop = getProp();
			
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
