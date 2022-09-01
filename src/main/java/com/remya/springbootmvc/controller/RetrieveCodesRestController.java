package com.remya.springbootmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remya.springbootmvc.model.Data;

@Controller
public class RetrieveCodesRestController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String test()
	{
		return "home";
	}


	@RequestMapping(value = "/getMedicines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Data>> sayHi() throws JsonProcessingException, IOException {
		HttpHeaders headers = new HttpHeaders();
		

		final String uri = "https://test/REST/rxcui/17767/related.json?tty=SBD";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		List<Data> dataList = new ArrayList<Data>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(result);
		JsonNode relatedGroup = rootNode.path("relatedGroup");
		JsonNode conceptGroup = relatedGroup.path("conceptGroup");
		Iterator<JsonNode> elements = conceptGroup.elements();
		while (elements.hasNext()) {
			JsonNode phone = elements.next();
			JsonNode conceptProperties = phone.path("conceptProperties");
			Iterator<JsonNode> elements2 = conceptProperties.elements();
			while (elements2.hasNext()) {
				JsonNode node=elements2.next();
				String name=node.get("name").asText();
				
				String id=node.get("rxcui").asText();
				if (name.startsWith("amlodipine 10 MG",0)) {
					System.out.println(name);
					dataList.add(new Data(id,
							name));
					
				}
			}
		}

		System.out.println(dataList);
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Content-Type", "application/json");
		return ResponseEntity.ok().headers(headers).body(dataList);
	}

}
