
package objetos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.JButton;
/**
 *
 * @author Victor
 */
public class ArrayOfertas {
    private ArrayList<Integer>ofertas;
    public ArrayOfertas(){
        ofertas = new ArrayList<>();
        insertar();
    }
    public void insertar(){
        ofertas.add(50);
        ofertas.add(1000);
        ofertas.add(1500);
        ofertas.add(20000);
        ofertas.add(25000);
        ofertas.add(45000);
        ofertas.add(50000);
        ofertas.add(70000);
        ofertas.add(100000);
        //van 9
        ofertas.add(150000);
        ofertas.add(245000);
        ofertas.add(1000000);
        ofertas.add(300000);
        ofertas.add(550);
        ofertas.add(500);
        //15
        ofertas.add(450000);
        ofertas.add(150450);
        ofertas.add(12000);
        ofertas.add(80000);
        ofertas.add(14000);
        ofertas.add(30000);
        //21
        ofertas.add(240);
        ofertas.add(6800);
        ofertas.add(3000);       
    }
    public void desordenar(){
        Collections.shuffle(this.ofertas);
    }
    public ArrayList<Integer> getOfertas() {
        return ofertas;
    }
    
}
