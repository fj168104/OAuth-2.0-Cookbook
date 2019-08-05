package com.packt.linkedin.example.sociallinkd.customer;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.ProfileOperations;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by feng on 2019/8/2
 */
public class MyLinkedInAdapter implements ApiAdapter<LinkedIn> {

	public boolean test(LinkedIn linkedin) {
		try {
			linkedin.profileOperations().getUserProfile();
			return true;
		} catch (HttpClientErrorException e) {
			// TODO: Have api throw more specific exception and trigger off of that.
			return false;
		}
	}

	public void setConnectionValues(LinkedIn linkedin, ConnectionValues values) {
		ProfileOperations profile = linkedin.profileOperations();
//		values.setProviderUserId(profile.getId());
//		values.setDisplayName(profile.getFirstName() + " " + profile.getLastName());
//		values.setProfileUrl(profile.getPublicProfileUrl());
//		values.setImageUrl(profile.getProfilePictureUrl());
	}

	public UserProfile fetchUserProfile(LinkedIn linkedin) {
		LinkedInProfile profile = linkedin.profileOperations().getUserProfile();
		return new UserProfileBuilder()
				.setName(profile.getFirstName() + " " + profile.getLastName())
				.setEmail(profile.getEmailAddress())
				.build();
	}

	public void updateStatus(LinkedIn linkedin, String message) {
		linkedin.networkUpdateOperations().createNetworkUpdate(message);
	}
}
