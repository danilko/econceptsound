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

package com.econcept.init;

import java.util.Arrays;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.econcept.ws.rest.TrackResource;

@Configuration
public class AppConfig
{
	@Bean(destroyMethod = "shutdown")
	

	public SpringBus cxf()
	{
		return new SpringBus();
	}  // SpringBus cxf
	
	@Bean
	public TrackResource getSoundResource() {
		return new TrackResource();
	}  // TrackResource getTrackResource

	@Bean
	public com.econcept.ws.rest.RestServiceApplication getRestServiceApplication()
	{
		return new com.econcept.ws.rest.RestServiceApplication();
	}  // RestServiceApplication getRestServiceApplication

	@Bean
	public Server initJAXRSServer() {
		JAXRSServerFactoryBean lFactory = RuntimeDelegate.getInstance().createEndpoint(getRestServiceApplication(),
				JAXRSServerFactoryBean.class);
		lFactory.setServiceBeans(Arrays.<Object>asList(getSoundResource()));
		
		lFactory.setAddress(lFactory.getAddress());

		return lFactory.create();
	}  // Server initJAXRSServer
}  // AppConfig
