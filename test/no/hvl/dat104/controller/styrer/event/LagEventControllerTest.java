package no.hvl.dat104.controller.styrer.event;

import static org.junit.Assert.assertTrue;

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

@RunWith(MockitoJUnitRunner.class)
public class LagEventControllerTest {
	@Mock
	HttpServletRequest stubRequest;
	@Mock
	HttpServletResponse stubResponse;
	@Mock
	RequestDispatcher stubRequestDispatcher;

	LagEventController lageventcontroller;

	@Before
	public void setUp() {
		lageventcontroller = new LagEventController();

		when(stubRequest.getRequestDispatcher(any(String.class))).thenReturn(stubRequestDispatcher);
	}

	@Test
	public void test() {
		assertTrue(true);
	}

	@Test
	public void atDetGjoresEnForwardTilKorrektJsp() throws ServletException, IOException {

		lageventcontroller.doGet(stubRequest, stubResponse);
//
//		verify(stubRequest).getRequestDispatcher("WEB-INF/views/deltager/lagevent.jsp");
//		verify(stubRequestDispatcher).forward(stubRequest, stubResponse);
	}

}
