package no.hvl.dat104.controller.deltager.test;

import static org.junit.Assert.*;

import no.hvl.dat104.controller.deltager.GiTilbakemeldingController;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.Test;


public class GiTilbakemeldingControllerTest {
    MockHttpServletRequest fakeRequest;
    MockHttpServletResponse fakeResponse;
    GiTilbakemeldingController giTilbakemeldingController;
    
	@Before
	public void setUp() throws Exception {
        fakeRequest = new MockHttpServletRequest();
        fakeResponse = new MockHttpServletResponse();
        giTilbakemeldingController = new GiTilbakemeldingController();
	}

	@Test
	public void tilbakemeldingSkalLiggeISomParameter() {
        fakeRequest.setParameter("tilbakemelding", "0");
        
        giTilbakemeldingController.doPost(fakeRequest,fakeResponse);
        
	}

}
