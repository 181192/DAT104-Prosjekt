package no.hvl.dat104.controller.styrer.liveevent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LiveEventServlet
 */
public class LiveEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet LiveEventServlet kjører.");

		//Lager noen testdata og sender til jsp i requesten. 
		List<Integer> dummyData = lagDummyListe(50, 20);
		List<Integer> dummyDataFT = lagFrekvensTabell(dummyData, 60);
		request.setAttribute("dummyData", dummyDataFT);
		request.getRequestDispatcher("WEB-INF/views/styrer/liveevent/liveevent.jsp").
						forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	
	
	/**
	 * Mockup for testdata.
	 * @param antall
	 * @param maxTid
	 * @return
	 */
    private List<Integer> lagDummyListe(int antall, int maxTid){
        List<Integer> tbm = new ArrayList<>();
        Random r = new Random();
        for(int i= 0; i < antall; i++){
            tbm.add(r.nextInt(maxTid));
        }
        
        return tbm;
    }
    
    /**
     * Mockup for testdata
     * @param alleTilbakemeldinger
     * @param maxVerdi
     * @return
     */
    private List<Integer> lagFrekvensTabell(List<Integer> alleTilbakemeldinger, Integer maxVerdi){
        Integer[] ftb = new Integer[maxVerdi];
        
        for(int i = 0; i < maxVerdi; i++){
            ftb[i] = 0;
        }
        
        for(int i = 0; i < alleTilbakemeldinger.size(); i++){
            int denne = alleTilbakemeldinger.get(i);
            ftb[denne] += 1;
        }
        
        return Arrays.asList(ftb);
    }

}
