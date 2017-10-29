package no.hvl.dat104.controller.deltager;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.controller.deltager.GiTilbakemeldingController;

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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


public class GiTilbakemeldingControllerTest {
	/*@Mock HttpServletRequest stubRequest;
	@Mock HttpServletResponse stubResponse;
	@Mock RequestDispatcher stubRequestDispatcher;
    GiTilbakemeldingController giTilbakemeldingController;
    
	@Before
	public void setUp() {
        giTilbakemeldingController = new GiTilbakemeldingController();
        
        when(stubRequest.getRequestDispatcher(any(String.class))).thenReturn(stubRequestDispatcher);
	}

    @Test
    public void atDetGjoresEnForwardTilKorrektJsp() throws ServletException, IOException {
        
    	giTilbakemeldingController.doGet(stubRequest, stubResponse);
        
        verify(stubRequest).getRequestDispatcher("WEB-INF/views/deltager/gitilbakemelding.jsp");
        verify(stubRequestDispatcher).forward(stubRequest, stubResponse);
    }*/


}
