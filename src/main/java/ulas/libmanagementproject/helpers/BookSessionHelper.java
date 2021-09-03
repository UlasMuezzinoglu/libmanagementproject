package ulas.libmanagementproject.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class BookSessionHelper implements IBookSessionHelper{

    private HttpSession httpSession;

    @Autowired
    public BookSessionHelper(HttpSession httpSession) {
        this.httpSession = httpSession;
    }


    @Override
    public void clear(String attribute) {
        httpSession.removeAttribute(attribute);
    }

    @Override
    public boolean nullCheck(Object object) {
        if (object == null){
            return false;
        }
        return true;
    }
}
