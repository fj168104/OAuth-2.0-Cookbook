package com.packt.linkedin.example.sociallinkd.customer;


import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.connect.LinkedInAdapter;

public class CustomLinkedInConnectionFactory extends
		OAuth2ConnectionFactory<LinkedIn> {

	public CustomLinkedInConnectionFactory(
			String appId, String appSecret, String apiVersion) {

		super("linkedin",
			new CustomLinkedInServiceProvider(appId, appSecret),
			new MyLinkedInAdapter());
	}

}
