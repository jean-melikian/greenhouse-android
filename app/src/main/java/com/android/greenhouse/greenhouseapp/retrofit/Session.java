package com.android.greenhouse.greenhouseapp.retrofit;

import com.android.greenhouse.greenhouseapp.BuildConfig;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public class Session {

	private static String BASE_URL = BuildConfig.GREENDUINO_REMOTE_ENDPOINT;

	private static Retrofit retrofit;
	private static Gson gson;

	public Session() {
		retrofit = getDefault();
		gson = getGson();
	}

	public static Retrofit getDefault() {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}

		return retrofit;
	}

	private static GsonBuilder getDefaultGsonBuilder() {
		GsonBuilder defaultGsonBuilder = new GsonBuilder();
		defaultGsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return f.getAnnotation(Exclude.class) != null &&
						f.getAnnotation(Exclude.class).serialize();
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		});

		defaultGsonBuilder.addDeserializationExclusionStrategy(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return f.getAnnotation(Exclude.class) != null &&
						f.getAnnotation(Exclude.class).deserialize();
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		});
		defaultGsonBuilder.setDateFormat("yyyy-MM-dd");
		return defaultGsonBuilder;
	}

	private static Gson getGson() {
		if (gson == null) {
			GsonBuilder builder = getDefaultGsonBuilder();
			gson = builder.create();
		}
		return gson;
	}
}
