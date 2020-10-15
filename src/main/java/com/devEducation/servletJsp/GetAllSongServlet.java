package com.devEducation.servletJsp;

import com.devEducation.model.Song;
import com.devEducation.service.MySqlService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetAllSongServlet", urlPatterns = "/getAllSongServlet")
public class GetAllSongServlet extends HttpServlet {
    private static final long serialVersionUID = 1567877564;
    private static final Logger logger = Logger.getLogger(MySqlService.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        MySqlService mySqlService = new MySqlService();
        PrintWriter out = response.getWriter();
        try {
            String errorString = null;
            List<Song> songs = null;
            try {
                songs = mySqlService.selectAllSong();
            }catch (Exception e){
                e.printStackTrace();
                errorString = e.getMessage();
            }
            request.setAttribute("errorString", errorString);
            request.setAttribute("songs", songs);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allSong.jsp");
            dispatcher.forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            out.print(HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}

