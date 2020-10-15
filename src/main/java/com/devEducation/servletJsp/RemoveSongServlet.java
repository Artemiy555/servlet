package com.devEducation.servletJsp;

import com.devEducation.service.MySqlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RemoveSongServlet", urlPatterns = "/removeSong")
public class RemoveSongServlet extends HttpServlet {
    private static final long serialVersionUID = 1567877564;

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//    }
//
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("/////////////////////////////////////////////////////////////////////////////");
            doDelete(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            String id = request.getParameter("id");
            new MySqlService().deleteSong(Integer.parseInt(id));
            response.sendRedirect(request.getContextPath() + "/getAllSongServlet");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

}


