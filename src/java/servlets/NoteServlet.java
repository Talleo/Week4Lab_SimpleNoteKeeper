package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

/**
 *
 * @author talleo
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // path to note.txt
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");

        // to read note.txt
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));

        // assign first line of note.txt to title and the next line to content
        String title = br.readLine();
        String content = br.readLine();
        br.close();

        // create new Note object with title and content read from Note.txt
        Note note = new Note(title, content);

        // setting the "note" attribute to be the Note object in the request
        request.setAttribute("note", note);

        // if edit param is null, then load viewnote.jsp, else load editnote.jsp
        if (request.getParameter("edit") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp")
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp")
                    .forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // path to note.txt
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");

        // get title and content from parameters in request
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // to write to note.txt
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));

        // printing the title and content into note.txt
        pw.println(title);
        pw.print(content);
        pw.close();

        // create new Note object with title and content from "title" and "content" params
        Note note = new Note(title, content);

        // setting the "note" attribute to be the new Note object in the request
        request.setAttribute("note", note);

        // forwarding
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp")
                .forward(request, response);
    }
}
