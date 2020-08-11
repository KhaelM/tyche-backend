/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyche.exception;

/**
 *
 * @author michael
 */
public class NotAdminException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotAdminException() {
        super("Vous devez etre un administrateur pour continuer.");
    }
    
}
