package it.ipfsdownloadmonitor.commons;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import it.ipfsdownloadmonitor.model.Contributor;

/**
 * @author reschini
 *
 */
public class GeoLiteManagerTestCase {

	/**
	 * Test method for {@link it.ipfsmonitoring.commons.GeoLiteManager#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		GeoLiteManager geoLiteManager = GeoLiteManager.getInstance();
		assertNotNull(geoLiteManager);
	}

	/**
	 * Test method for {@link it.ipfsmonitoring.commons.GeoLiteManager#resolve(java.lang.String)}.
	 */
	@Test
	public void testResolve() {
		try {
			Contributor contributor = new Contributor();
			contributor.setIp("95.216.30.73");
			boolean result = GeoLiteManager.getInstance().resolve(contributor);
			assertTrue(result);
		} catch (IOException | GeoIp2Exception e) {
			fail(e.getMessage());
		}
	}

}
