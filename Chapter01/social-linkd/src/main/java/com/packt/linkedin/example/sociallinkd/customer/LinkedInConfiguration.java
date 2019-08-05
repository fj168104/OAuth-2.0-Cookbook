package com.packt.linkedin.example.sociallinkd.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.linkedin.api.LinkedIn;

@Configuration
@EnableSocial
public class LinkedInConfiguration extends SocialAutoConfigurerAdapter {

	@Autowired
	private EnhancedLinkedInProperties properties;

	@Bean
	@ConditionalOnMissingBean(LinkedIn.class)
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public LinkedIn linkedIn(ConnectionRepository repository) {
		Connection<LinkedIn> connection = repository
				.findPrimaryConnection(LinkedIn.class);
		return connection != null ? connection.getApi() : null;
	}

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		return new CustomLinkedInConnectionFactory(this.properties.getAppId(),
				this.properties.getAppSecret(), this.properties.getApiVersion());
	}

	@Bean
	public ConnectController connectController(
			ConnectionFactoryLocator factoryLocator,
			ConnectionRepository repository) {

		ConnectController controller = new ConnectController(
			factoryLocator, repository);
		controller.setApplicationUrl("http://localhost:8080");
		return controller;
	}
}
