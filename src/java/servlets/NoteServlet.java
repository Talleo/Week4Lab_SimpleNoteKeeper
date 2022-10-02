package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

/**
 *
 * @author godim
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
        
    }
}
