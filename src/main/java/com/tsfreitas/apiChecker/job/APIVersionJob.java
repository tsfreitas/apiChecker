package com.tsfreitas.apiChecker.job;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.tsfreitas.apiChecker.loader.PROPERTIES;
import com.tsfreitas.apiChecker.loader.PropertiesLoader;

@Component
public class APIVersionJob {

	private static final String URL = "https://developers.google.com/adwords/api/docs/sunset-dates?hl=pt-br";

	@Autowired
	private PropertiesLoader loader;

	private static final RestTemplate REST_TEMPALTE = new RestTemplate();

	@Scheduled(cron = "0 28 8 * * ?")
	public void run() {
		try {
			job();
		} catch (URISyntaxException | IOException e) {
			// TODO: Deu erro, avisa no front
			e.printStackTrace();
		}
	}

	private void job() throws URISyntaxException, IOException {

		// Recupera Tag do site
		String latest = getLatestVersion();

		// Recupera tag do arquivo
		String current = loader.getProperties(PROPERTIES.API_TAG);

		if (!latest.equals(current)) {

			// TODO: Manda email

			// Marca atualização de tag
			loader.updateProperties(PROPERTIES.API_ALERT, "true");

			// Atualiza arquivo
			loader.updateProperties(PROPERTIES.API_TAG, latest);

		}

	}

	private String getLatestVersion() throws RestClientException, URISyntaxException {
		String html = REST_TEMPALTE.getForObject(new URI(URL), String.class);

		Document doc = Jsoup.parse(html);

		return doc.select("#sunset td a").first().text();

	}

}
