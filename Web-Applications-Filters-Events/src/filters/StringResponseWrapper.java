package filters;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class StringResponseWrapper extends HttpServletResponseWrapper {

    // Strumien do którego będą pisane odpowiedzi
    private StringWriter stringWriter = null;

    public StringResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    // Przedefiniowanie metody getWriter()
    // każdy kto ejj użyje - otrzyma nasz strumień StringWriter
    // i nic o tym nie wiedząc będzie pisał do niego
    // a nie do strumienia
    // związanego z oryginalnym obiektem HttpServletResponse


    public PrintWriter getWriter() throws IOException {
        if (stringWriter == null) stringWriter = new StringWriter();
        return new PrintWriter(stringWriter);
    }

    // Nie jesteśmy przygotowani na obsługę strumieni binarnych
    // - wykluczamy ich zastosowanie (chociaż nie musimy tego robić)

    public ServletOutputStream getOutputStream() throws IOException {
        throw new IllegalStateException(
                "getOutputStream() not allowed for StringResponseWrapper"
        );
    }

    // Nasza własna metoda, pozwlająca uzyskać dostęp do strumioenia
    // i do jego zawartości

    public StringWriter getStringWriter() {
        return stringWriter;
    }

}