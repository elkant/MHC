/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mother_child;

import java.io.Serializable;

/**
 *
 * @author Maureen
 */
public class MsgBean implements Serializable {
    String ID;
    String MESSAGE;
    String NOKMESSAGE;
    String STATUS;
    String CATEGORY;
    String UJUMBE;
    String UJUMBENOK;
    String KALENJIN;
    String KALENJINNOK;

    public String getKALENJIN() {
        return KALENJIN;
    }

    public void setKALENJIN(String KALENJIN) {
        this.KALENJIN = KALENJIN;
    }

    public String getKALENJINNOK() {
        return KALENJINNOK;
    }

    public void setKALENJINNOK(String KALENJINNOK) {
        this.KALENJINNOK = KALENJINNOK;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getUJUMBE() {
        return UJUMBE;
    }

    public void setUJUMBE(String UJUMBE) {
        this.UJUMBE = UJUMBE;
    }

    public String getUJUMBENOK() {
        return UJUMBENOK;
    }

    public void setUJUMBENOK(String UJUMBENOK) {
        this.UJUMBENOK = UJUMBENOK;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getNOKMESSAGE() {
        return NOKMESSAGE;
    }

    public void setNOKMESSAGE(String NOKMESSAGE) {
        this.NOKMESSAGE = NOKMESSAGE;
    }
    
    
}
