/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class ArrayControl {
    private ArrayList<Integer>control;

    public ArrayControl() {
        control=new ArrayList<>();
        agregar();
        
    }
    public void agregar(){
        for(int i=0;i<24;i++){
            this.control.add(0);
        }
    }
    public ArrayList<Integer> getControl() {
        return control;
    }

    public void setControl(ArrayList<Integer> control) {
        this.control = control;
    }
    
    
}
