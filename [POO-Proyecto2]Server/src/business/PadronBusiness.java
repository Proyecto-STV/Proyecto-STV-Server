package business;

import data.PadronData;
import domain.Persona;
import java.util.List;
import util.IConstants;

/**
 *
 * @author Nelson
 */
public class PadronBusiness implements IConstants{
    private PadronData padronData;

    public PadronBusiness() {
        this.padronData = new PadronData(FILE_NAME);
    }
    
    public List<Persona> getAllPersons(){
        return padronData.getAllPersons();
    }
}
