package io.github.biezhi.jetty.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class DumpServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        dumpDetails(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        dumpDetails(req,resp);
    }

    private void dumpDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();

        out.printf("Method: %s%n",req.getMethod());
        out.printf("Request-URI: %s%n",req.getRequestURI());
        out.printf("Version: %s%n",req.getProtocol());
        out.printf("Request-URL: %s%n",req.getRequestURL());
    }
}
