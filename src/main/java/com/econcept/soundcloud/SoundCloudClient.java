/**
 * 
 * The MIT License (MIT)
 * 
 * Copyright (c) 2013 Kai-Ting (Danil) Ko
 * 
 * Permission is hereby granted, free of charge, 
 * to any person obtaining a copy of this software 
 * and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including 
 * without limitation the rights to use, copy, modify, 
 * merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom 
 * the Software is furnished to do so, subject to the 
 * following conditions:
 * 
 * The above copyright notice and this permission notice 
 * shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY 
 * OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS 
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE 
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */

/**
 * The SoundCloud API to get the client
 * 
 * @author Kai - Ting (Danil) Ko
 */
package com.econcept.soundcloud;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.Http;
import com.soundcloud.api.Request;
import com.soundcloud.api.Token;

public class SoundCloudClient 
{
	private ApiWrapper mClient;
	
	private final static CachedToken CACHEDTOKEN = new CachedToken();
	private final static Logger LOGGER = LoggerFactory.getLogger(SoundCloudClient.class);
	
	private String mClientID;
	private String mSoundCloudURL;
	public SoundCloudClient() 
	{
		 mSoundCloudURL=System.getenv("SOUNDCLOUD_URL");
		 mClientID=System.getenv("SOUNDCLOUD_CLIENT_ID");
		 mClient =  new ApiWrapper(System.getenv("SOUNDCLOUD_CLIENT_ID"), System.getenv("SOUNDCLOUD_CLIENT_SECRET"), null, null);
	}  // SoundCloudClient
	
	/**
	 * Get the access token for current ApiWrapper
	 * @throws Exception 
	 */
	private void getAccessToken() throws Exception
	{
		try {
			Token lToken = mClient.login(System.getenv("SOUNDCLOUD_USERNAME"), System.getenv("SOUNDCLOUD_PASSWORD"));
			if(lToken == null)
			{
				throw new Exception();
			}  // if
			
			CACHEDTOKEN.setToken(lToken);
		}  // catch 
		catch (Exception pException) 
		{
			LOGGER.debug("Fail to get token" + pException.toString());
			throw pException;
		}  // try
	}  // void getAccessToken
	
	/**
	 * 
	 * @param pTrackNum The track number per page
	 * @param pStartIndex The index to start counting the page
	 * @return Tracks within JSON Format
	 * @throws Exception The exception to get the token
	 */
	public String getTracks(int pTrackNum, int pStartIndex) throws Exception
	{
		System.out.println("NUM " + pTrackNum + " Index " + pStartIndex);
		Token lToken = CACHEDTOKEN.getToken();
		if(lToken==null)
		{
			getAccessToken();
			lToken = CACHEDTOKEN.getToken();
		}  // if
		
		mClient.setToken(lToken);
		
		Request lRequest = new Request();
		lRequest = lRequest.newResource(mSoundCloudURL + "/tracks?client_id=" + mClientID);
		
		HttpResponse lResponse = mClient.get(lRequest);
		
		if(lResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		{
			JSONArray lJSONArray = new JSONArray(Http.getString(lResponse));
			
			int lTotalTrackLength = lJSONArray.length();
			
			int lStartIndex = pStartIndex;
			int lEndIndex = (lStartIndex + 1) * pTrackNum;
			
			int lCurrentIndex = 0;
			
			StringBuilder lBuilder = new StringBuilder();
			lBuilder.append("[");
			
			
			while(lCurrentIndex < lEndIndex && lCurrentIndex < lTotalTrackLength)
			{

				JSONObject lObject = lJSONArray.getJSONObject(lCurrentIndex);
				
				// No need to collect private
				if(lObject.getString("sharing").equals("private"))
				{
					continue;
				}  // if
				
				String lURL = lObject.getString("stream_url");
				if(!lURL.equals("null"))
				{
					lObject = lObject.put("stream_url", lURL + "?oauth_token=" + lToken.access);
				}  // if
				
				lObject = lObject.put("uri", lObject.get("uri") + "?oauth_token=" + lToken.access);
				
				lURL = "" + lObject.get("artwork_url");
				if(!lURL.equals("null"))
				{
					lObject = lObject.put("artwork_url", lURL + "?oauth_token=" + lToken.access);
				}  // IF
				lBuilder.append(lObject);
				
				lCurrentIndex++;
				
				if(lCurrentIndex < lEndIndex && lCurrentIndex < lTotalTrackLength)
				{
					lBuilder.append(",");
				}  // if
			}  // while
			
			lBuilder.append("]");
			LOGGER.debug(lBuilder.toString());
			return lBuilder.toString();
		}  // if
		
		LOGGER.debug("Fail to get tracks");
		throw new Exception("Fail to get tracks");
	}  // String getTracks
}  // class SoundCloudClient
