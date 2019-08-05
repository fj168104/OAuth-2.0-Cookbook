package com.packt.linkedin.example.sociallinkd.customer;

import com.packt.linkedin.example.sociallinkd.customer.impl.MyLinkedInTemplate;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class CustomLinkedInServiceProvider extends
		AbstractOAuth2ServiceProvider<LinkedIn> {

	public CustomLinkedInServiceProvider(String appId, String appSecret) {
		super(getOAuth2Template(appId, appSecret));
	}

	private static OAuth2Template getOAuth2Template(String clientId, String clientSecret) {
		OAuth2Template oAuth2Template = new OAuth2Template(clientId, clientSecret,
				"https://www.linkedin.com/oauth/v2/authorization",
				"https://www.linkedin.com/oauth/v2/accessToken");
		oAuth2Template.setUseParametersForClientAuthentication(true);
		return oAuth2Template;
	}


	@Override
	public LinkedIn getApi(String accessToken) {
		return new MyLinkedInTemplate(accessToken);
	}
}
