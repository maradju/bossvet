/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author Miroslav
 */
public class FormValidator {
    private static FormValidator instance;
    String validationMessage = "";
    
    private FormValidator(){
    }

    public static FormValidator getInstance() {
        if(instance == null)
            instance = new FormValidator();
        return instance;
    }
    
    public boolean validateEmpty(JTextField... textF){
        boolean valid = true;
        int brojacLosih = 0;
        for (JTextField oneField : textF) {
            if("".equals(oneField.getText())){
                oneField.setBorder(BorderFactory.createLineBorder(Color.red,3,true));
                brojacLosih++;
                valid = false;
            }
        }
        
        if(!valid)
         validationMessage =   brojacLosih==1 ? "Dato polje ne sme biti prazno": "Data polja ne smeju biti prazna";
        
        return valid;
    }

    public String getValidationMessage() {
        return validationMessage;
    }
    
    public boolean validateEmail(JTextField email){
        if(!email.getText().matches("^\\w+@\\w+\\..{2,3}(.{2,3})?$")){
            validationMessage = "Email nije dobar";
            email.setBorder(BorderFactory.createLineBorder(Color.red,3,true));
            return false;
        }
        
        return true;
    }
}
