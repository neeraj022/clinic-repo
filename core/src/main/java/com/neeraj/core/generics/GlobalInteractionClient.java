package com.neeraj.core.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeraj.core.spring.ApplicationContextProvider;
import com.neeraj.core.spring.AdvancedQueryBuilder;
import com.neeraj.core.ui.QueryBuilder;

public class GlobalInteractionClient<Req> {

	private String dtoName;
	private Logger logger = Logger.getLogger(GlobalInteractionClient.class);

	// Read method
	public MyResponse read(String url, QueryBuilder queryBuilder, Class response) {
		MyResponse myResponse = new MyResponse();
		HttpMethod httpMethod = HttpMethod.GET;
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<MyResponse> httpEntity = new HttpEntity<MyResponse>(httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MyResponse> responseEntity = null;
		responseEntity = restTemplate.exchange(this.getFullUrl(url) + appendGETConditions(queryBuilder), httpMethod, httpEntity, MyResponse.class);
		logger.debug("Final URL called is: " + this.getFullUrl(url) + appendGETConditions(queryBuilder));
		logger.debug("Response entity received from service side: " + responseEntity.toString());
		try {
			myResponse = this.setProperData(responseEntity, url, response);
		} catch (ClassNotFoundException e) {
			logger.debug(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return myResponse;
	}

	// add httpMethod.GET parameters in the URL
	public String appendGETConditions(QueryBuilder queryBuilder) {
		String stringForAppendingGetConditions = "";
		if (queryBuilder != null && queryBuilder.getQueryBuilderMap().size() != 0) {
			stringForAppendingGetConditions = "?";
			for (Map.Entry<String, String> entry : queryBuilder.getQueryBuilderMap().entrySet()) {
				stringForAppendingGetConditions = stringForAppendingGetConditions + entry.getKey() + "=" + entry.getValue() + "&";
			}

			stringForAppendingGetConditions = stringForAppendingGetConditions.substring(0, stringForAppendingGetConditions.length() - 1);
		}

		return stringForAppendingGetConditions;
	}

	// Advanced Read method
	public MyResponse advancedRead(String url, AdvancedQueryBuilder queryBuilder, Class response) {
		logger.debug("Query parameters for the URL " + this.getFullUrlForAdvancedSearch(url) + " are: " + queryBuilder.toString());
		MyResponse myResponse = new MyResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity httpEntity = new HttpEntity(queryBuilder, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MyResponse> responseEntity = null;
		responseEntity = restTemplate.exchange(this.getFullUrlForAdvancedSearch(url), HttpMethod.POST, httpEntity, MyResponse.class);
		logger.debug("Response entity received from service side: " + responseEntity.toString());
		try {
			myResponse = this.setProperData(responseEntity, url, response);
		} catch (ClassNotFoundException e) {
			logger.debug(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return myResponse;
	}

	// Write Methods

	// Update
	public MyResponse write(String url, HttpMethod httpMethod, Req t, Class response) {
		MyResponse myResponse = new MyResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Req> httpEntity = new HttpEntity<Req>(t, headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory() {
			@Override
			protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
				if (HttpMethod.DELETE == httpMethod) {
					return new HttpEntityEnclosingDeleteRequest(uri);
				}

				return super.createHttpUriRequest(httpMethod, uri);
			}
		});
		ResponseEntity<MyResponse> responseEntity = null;
		responseEntity = restTemplate.exchange(this.getFullUrl(url), httpMethod, httpEntity, MyResponse.class);
		logger.debug("Response entity received from service side: " + responseEntity.toString());
		try {
			myResponse = this.setProperData(responseEntity, url, response);
		} catch (ClassNotFoundException e) {
			logger.debug(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return myResponse;
	}

	public String getDtoName() {
		return dtoName;
	}

	public void setDtoName(String dtoName) {
		this.dtoName = dtoName;
	}

	private String getFullUrl(String url) {

		return ApplicationContextProvider.getMessage("serverAddress", null, null) + url;

	}

	private String getFullUrlForAdvancedSearch(String url) {

		return ApplicationContextProvider.getMessage("serverAddress", null, null) + url + "mysearch/";

	}

	private MyResponse setProperData(ResponseEntity<MyResponse> responseEntity, String url, Class response) throws ClassNotFoundException {

		MyResponse finalResponse = new MyResponse();
		if (responseEntity == null) {
			finalResponse.setSuccess(false);
			finalResponse.setErrorMessage("No response received from " + getFullUrl(url) + " class: " + this.getClass().getCanonicalName());
		} else if (responseEntity.getStatusCode() == null || responseEntity.getStatusCode().compareTo(HttpStatus.OK) != 0) {

			finalResponse.setSuccess(false);
			finalResponse.setErrorMessage("Error recevied for URL" + getFullUrl(url) + "with error code as: " + responseEntity.getStatusCode()
					+ " class: " + this.getClass().getCanonicalName());
		} else {

			finalResponse.setSuccess(true);
			// if dtoClassName is supplied, it means casting is required from
			// LinkedHashMap to T
			Class clazz = response;
			/*
			 * if (getClass().getGenericSuperclass() instanceof ParameterizedType) { clazz = (Class<Res>) ((ParameterizedType)
			 * getClass().getGenericSuperclass()).getActualTypeArguments()[0]; } else { clazz = (Class<Res>) getClass().getGenericSuperclass(); }
			 */
			/*
			 * if (t != null) { clazz = t.getClass(); } else { clazz = Class.forName(dtoName); }
			 */

			// if class was loaded successfully, only then conversion can happen
			if (clazz != null) {
				/*
				 * List<Req> list = this.processList(responseEntity, clazz); finalResponse.setData((List<Object>) list);
				 */
				finalResponse.setData(this.processList(responseEntity, clazz));
			}

		}
		return finalResponse;
	}

	private List<Object> processList(ResponseEntity<MyResponse> responseEntity, Class clazz) {
		ObjectMapper mapper = new ObjectMapper();
		List<Object> finalList = new ArrayList<Object>();
		if (responseEntity.getBody().getData() != null) {

			for (Object object : responseEntity.getBody().getData()) {
				Req t = (Req) mapper.convertValue(object, clazz);

				finalList.add((Object) t);
			}
			logger.debug("Final response after processing: " + finalList.toString());
			return finalList;
		}
		else 
			return null;
	}

	// Added static class to support body in case of DELETE method
	// RestTemplate supports body only for POST & PUT methods
	public static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {
		public HttpEntityEnclosingDeleteRequest(final URI uri) {
			super();
			setURI(uri);
		}

		@Override
		public String getMethod() {
			return "DELETE";
		}
	}
	/*
	 * if (dtoName != null) { Class clazz; clazz=Class.forName(dtoName); List<clazz> list; return (List<Object>) convertedList; } else return null; }
	 */

}
