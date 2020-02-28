package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	public String[] os() {
		String os[] = new String[3];
		os[0] = System.getProperty("os.name");
		os[1] = System.getProperty("os.version");
		os[2] = System.getProperty("os.arch");
		return os;
	}

	public void Ip(String os) {
		Process process;
		String comando = "";
		if (os.equals("Windows 10")) {
			comando = "ipconfig";
		} else {
			if (os.equals("Linux")) {
				comando = "ifconfig";
			}
		}
		try {

			process = Runtime.getRuntime().exec(comando);
			InputStream input = process.getInputStream();
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader bufferReader = new BufferedReader(reader);
			String line = "";
			StringBuffer saida = new StringBuffer();
			if (os == "Windows 10") {
				while (line != null) {
					line = bufferReader.readLine();
					if (line != null) {
						if (line.contains("Adaptador")) {
							if (saida.length() > 0) {
								if (!(saida.substring(saida.lastIndexOf("Adaptador"), saida.length())
										.contains("IPv4"))) {
									saida.delete(saida.lastIndexOf("Adaptador"), saida.length());
								}
							}
							saida.append(line + " \n");
						} else {
							if (line.contains("IPv4")) {
								saida.append(line + " \n");
							}
						}
					}
				}
			}else {
				if (os.contentEquals("Linux")) {
					int temp = 0; 
					while (line != null) {
						line = bufferReader.readLine();
						if (line != null) {
							if (line.contains("flags")) {
								if (saida.length() > 0) {
									saida.delete(temp, saida.length());
								}
								saida.append(line + "\n");
							}else {
								if (line.contains("netmask")) {
									saida.append(line + "\n");
									temp = saida.length();
								}
							}
						}
					}
				}
			}

			System.out.println(saida.toString());

			bufferReader.close();
			reader.close();
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ping(String os) {
		Process process;
		String comando = "";
		try {
			if (os.equals("Windows 10")) {
				comando = "ping google.com  -n 10";
			} else {
				if (os.equals("Linux")) {
					comando = "ping -c 10 google.com";
				}
			}
			process = Runtime.getRuntime().exec(comando);
			InputStream input = process.getInputStream();
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader bufferReader = new BufferedReader(reader);
			String line = bufferReader.readLine();
			double pingMedia = 0;
			while (line != null) {
				line = bufferReader.readLine();
				if (line != null) {
					if (line.contains("tempo=") || line.contains("time=")) {
						pingMedia += Double
								.parseDouble(line.substring(line.lastIndexOf("=") + 1, line.lastIndexOf("ms")));
					}
				}
			}
			pingMedia = pingMedia / 10;
			System.out.println("Ping: " + pingMedia + " ms");

			bufferReader.close();
			reader.close();
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
