package com.tsfreitas.apiChecker.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PropertiesLoader {

	private static final String PATH = "/data/apichecker.properties";

	public String getProperties(PROPERTIES prop) throws IOException {

		// Valida se arquivo existe
		validateFile();

		// Lê propriedade
		return readPropeties().getProperty(prop.getPropertyName());
	}

	public void updateProperties(PROPERTIES prop, String value) throws IOException {
		// Lê as propriedades
		Properties props = readPropeties();
		props.setProperty(prop.getPropertyName(), value);

		// Grava as infos no arquivo
		props.store(new FileOutputStream(new File(PATH)), "Local database");
	}

	private void validateFile() throws IOException {
		Path path = Paths.get(PATH);

		if (Files.exists(path)) {
			return;
		}

		// Cria arquivo
		Files.createFile(path);

		// Adiciona propriedades padrão
		for (PROPERTIES prop : PROPERTIES.values()) {
			updateProperties(prop, prop.getDefaultValue());
		}
	}

	private Properties readPropeties() throws IOException {
		FileInputStream stream = new FileInputStream(new File(PATH));

		Properties p = new Properties();
		p.load(stream);

		return p;
	}

}
