package org.magnum.dataup;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class VideoSvcHelper {

	/**
	 * An id-generator for assigning unique id for each uploaded video in VideoSvc
	 */
	private static final AtomicLong currentId = new AtomicLong(0L);
	
	/**
	 * Atomically increment the last video id and return new id.
	 * @return
	 */
	public static long getNextId() {
		return currentId.incrementAndGet();
	}
	
	/**
	 * Generate a data URL from videoID.
	 * The URL is a complete URL of the form 
	 * http://localhost:8080/video/<videoid>/data or
	 * http://<servername>/video/<videoid>/data
	 * @param videoId
	 * @return
	 */
	public static String getDataUrl(long videoId) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		String base = "http://"+request.getServerName() + ((request.getServerPort() != 80) ? ":"+request.getServerPort() : "");

		String url = base +  VideoSvcApi.VIDEO_SVC_PATH + "/" + videoId + "/" + VideoSvcApi.DATA_PARAMETER;
		return url;
	}

}
