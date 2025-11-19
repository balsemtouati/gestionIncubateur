/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package gestionincubateur;

/**
 *
 * @author touat
 */
public class ErreurEvent extends Exception{

    /**
     * Creates a new instance of <code>ErreurEvent</code> without detail
     * message.
     */
    public ErreurEvent() {
    }

    public ErreurEvent(String msg) {
        super(msg);
    }
}
