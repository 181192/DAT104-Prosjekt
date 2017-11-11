package no.hvl.dat104.controller.deltager;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Date;

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
public class GiTilbakemeldingHjelpeklasseTest {
	@Mock HttpServletRequest stubRequest;
	@Mock HttpServletResponse stubResponse;
	@Mock HttpSession stubSesjon;
	
	@Before
	public void setUp() throws Exception {
        
        when(stubRequest.getSession(any(boolean.class))).thenReturn(stubSesjon);
	}

	@Test
	public void maaVente() throws ServletException, IOException {
		stubSesjon.setAttribute("sistStemme", new Date());
		
		verify(stubSesjon).getAttribute("sistStemme");
		//assertTrue(GiTilbakemeldingHjelpeklasse.maaVente(stubRequest, 10));
		
	}

}
