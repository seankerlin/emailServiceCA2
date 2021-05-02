/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Callback_Support;

import Objects.Email;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author admin
 */
public class CallBackMethods extends UnicastRemoteObject implements RMIClientInterface  {

    public CallBackMethods() throws RemoteException {
    }
    
    
    
    
    
    @Override
    public void newEmailNotification(Email newEmail) throws RemoteException {
        System.out.println("You got mail");
    }
}
