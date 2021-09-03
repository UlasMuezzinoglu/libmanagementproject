package ulas.libmanagementproject.helpers;

import lombok.experimental.Helper;

import javax.servlet.http.HttpSession;


public interface IBookSessionHelper {

    void clear(String attribute);
    boolean nullCheck(Object object);
}
