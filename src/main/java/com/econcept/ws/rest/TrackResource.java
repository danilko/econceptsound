package com.econcept.ws.rest;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.econcept.soundcloud.SoundCloudClient;

@Path("/track")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackResource
{
	private final static Logger LOGGER = LoggerFactory.getLogger(TrackResource.class);
	
	@Resource
	private SoundCloudClient mClient;
	
	/**
	 * 
	 * <p>Get the all files that owned by the user according to the user information</p>
	 * 
	 * @return Response HTTP Status with String in FileEntity JSON format
	 *         
	 */
	@GET
	@Path("/")
	public Response getTrackList(@QueryParam("trackNum") int pTrackNum, @QueryParam("startIndex") int pStartIndex)
	{
		ResponseBuilder lBuilder = null;
		
		try
		{
			System.out.println(mClient.getTracks(pTrackNum, pStartIndex));
			lBuilder = Response.status(Status.OK).entity(mClient.getTracks(pTrackNum, pStartIndex));
		} // try
		catch (Exception pException)
		{
			LOGGER.debug(pException.toString());
			lBuilder = Response.status(Status.INTERNAL_SERVER_ERROR).entity(pException);
		} // catch
		
		return lBuilder.build();
	} // String getFileList
}
