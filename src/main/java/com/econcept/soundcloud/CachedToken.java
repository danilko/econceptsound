package com.econcept.soundcloud;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.econcept.ws.rest.TrackResource;
import com.soundcloud.api.Token;

public class CachedToken {

	private Token mToken;
	
	private final static Object BLOCK_OBJECT = new Object();
	private final static Logger LOGGER = LoggerFactory.getLogger(TrackResource.class);
	
	public CachedToken() 
	{
		mToken=null;
	}  // CachedToken
	
	public void setToken(Token pToken)
	{
		synchronized(BLOCK_OBJECT)
		{
			mToken = new Token(pToken.access, pToken.refresh, pToken.scope);
		}  // synchronized(BLOCK_OBJECT)
	}  // void setToken
	
	public Token getToken()
	{
		synchronized(BLOCK_OBJECT)
		{
			Calendar lCalendar = Calendar.getInstance();
			lCalendar.add(Calendar.MINUTE, -2);
			
			if(mToken == null)
			{
				return null;
			}  // if
			
			if(!lCalendar.after(mToken.getExpiresIn()))
			{
				return new Token(mToken.access, mToken.refresh, mToken.scope);
			}  // if
			else
			{
				return null;
			}  // else
		}  // synchronized(BLOCK_OBJECT)
	}  // void setToken
}
