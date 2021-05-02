/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiemailserviceclientca2;

import Callback_Support.CallBackMethods;
import java.net.URL;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Business.RMIEmailInterface;
import Business.RMIUserInterface;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;

/**
 *
 * @author admin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    Label label;
    @FXML
    TextField userName;
    @FXML
    TextField password;
    @FXML
    Button loginBtn;
    @FXML
    Button registerbtn;
    @FXML
    TextField emailSubject;
    @FXML
    TextField emailContents;
    @FXML
    TextArea emailDisplay;
    @FXML
    TextField emailRecepiant;

    RMIUserInterface userService;
    RMIEmailInterface emailService;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            int portNum = 16000;

            String registryPath = "rmi://localhost:" + portNum;
            String EmailLabel = "/EmailService";
            String UserLabel = "/UserService";

            emailService = (RMIEmailInterface) Naming.lookup(registryPath + EmailLabel);
            userService = (RMIUserInterface) Naming.lookup(registryPath + UserLabel);
            

        } catch (NotBoundException ex) {
            System.out.println("NotBoundException");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            System.out.println("MalformedURLException");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            System.out.println("RemoteException");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loginAction(ActionEvent event) {

        try {
            System.out.println("Login");
            CallBackMethods callBack = new CallBackMethods();
            
            ArrayList<String> userInfo = new ArrayList();
            String username = userName.getText();
            String pass = password.getText();
            System.out.println(username + pass);
           boolean output = userService.login(username, pass, callBack);
            System.out.println(output);

        } catch (RemoteException ex) {
            System.out.println("RemoteException");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void register(ActionEvent event) {

        try {
            System.out.println("Register");
            String username = userName.getText();
            String pass = password.getText();
            userService.register(username, pass);
        } catch (RemoteException ex) {
            System.out.println("RemoteException");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void sendEmail(ActionEvent event) {
        String reciepent = emailRecepiant.getText();
        String subject = emailSubject.getText();
        String content = emailContents.getText();
    }

}
