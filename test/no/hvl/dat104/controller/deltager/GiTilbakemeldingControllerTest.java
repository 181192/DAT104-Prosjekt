package no.hvl.dat104.controller.deltager;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.JspMappings;
import no.hvl.dat104.controller.UrlMappings;
import no.hvl.dat104.controller.deltager.GiTilbakemeldingController;
import no.hvl.dat104.model.Event;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GiTilbakemeldingControllerTest {
	@Mock HttpServletRequest stubRequest;
	@Mock HttpServletResponse stubResponse;
	@Mock HttpSession stubSesjon;
	@Mock RequestDispatcher stubRequestDispatcher;
    GiTilbakemeldingController giTilbakemeldingController;
    
	@Before
	public void setUp() {
        giTilbakemeldingController = new GiTilbakemeldingController();
        
        when(stubRequest.getSession(any(boolean.class))).thenReturn(stubSesjon);
        when(stubRequest.getRequestDispatcher(any(String.class))).thenReturn(stubRequestDispatcher);
	}

	/*
    @Test
    public void atDetGjoresEnForwardTilKorrektJsp() throws ServletException, IOException {
    	stubSesjon.setAttribute("event", new Event());
        
    	giTilbakemeldingController.doGet(stubRequest, stubResponse);
    	
        verify(stubRequest).getRequestDispatcher(JspMappings.GITILBAKEMELDING_JSP);
        verify(stubRequestDispatcher).forward(stubRequest, stubResponse);

    }
	*/
    
    @Test
    public void blirRedirectaTilDeltaEventVedManglendeData() throws ServletException, IOException {
    	giTilbakemeldingController.doGet(stubRequest, stubResponse);
    	
    	verify(stubResponse).sendRedirect(UrlMappings.DELTAEVENT_URL);
    }
}
