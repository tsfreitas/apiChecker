package com.tsfreitas.apiChecker.job;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsfreitas.apiChecker.loader.PROPERTIES;
import com.tsfreitas.apiChecker.loader.PropertiesLoader;

@Component
public class GitTagJob {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private static final RestTemplate REST_TEMPALTE = new RestTemplate();

	private static final String API_ENDPOINT = "https://api.github.com/repos/googleads/googleads-java-lib/releases/latest";

	@Autowired
	private PropertiesLoader loader;

	@Scheduled(cron="0 20 8 * * ?")
	public void run() {
		try {
			job();
		} catch (URISyntaxException | IOException e) {
			// TODO: Deu erro, avisa no front
			e.printStackTrace();
		}

	}

	private void job() throws URISyntaxException, IOException {

		// Recupera tag do github
		String latestTag = getLatestTag();

		// Recupera tag do arquivo
		String validatedTag = loader.getProperties(PROPERTIES.GITHUB_TAG);

		if (!latestTag.equals(validatedTag)) {

			// TODO: Manda email

			// Marca atualização de tag
			loader.updateProperties(PROPERTIES.GITHUB_ALERT, "true");

			// Atualiza arquivo
			loader.updateProperties(PROPERTIES.GITHUB_TAG, latestTag);
		}
	}

	private String getLatestTag() throws URISyntaxException, IOException {
		String response = REST_TEMPALTE.getForObject(new URI(API_ENDPOINT), String.class);

		JsonNode jsonNode = MAPPER.readTree(response);

		return jsonNode.get("tag_name").textValue();

	}

}
