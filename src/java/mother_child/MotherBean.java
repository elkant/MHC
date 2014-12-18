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
public class MotherBean implements Serializable{
   String ANCNO;
   String STATUS;
   String FNAME;
   String SNAME;
   String PHONENO;
   String NOKPHONE;
   String LANGUAGE;
   String CONSENT;
   String NOK;
   int VISITID;

    public int getVISITID() {
        return VISITID;
    }

    public void setVISITID(int VISITID) {
        this.VISITID = VISITID;
    }

   
   String VISITDATE;

    public String getVISITDATE() {
        return VISITDATE;
    }

    public void setVISITDATE(String VISITDATE) {
        this.VISITDATE = VISITDATE;
    }

 

    public String getNOKPHONE() {
        return NOKPHONE;
    }

    public void setNOKPHONE(String NOKPHONE) {
        this.NOKPHONE = NOKPHONE;
    }

    public String getLANGUAGE() {
        return LANGUAGE;
    }

    public void setLANGUAGE(String LANGUAGE) {
        this.LANGUAGE = LANGUAGE;
    }

    public String getCONSENT() {
        return CONSENT;
    }

    public void setCONSENT(String CONSENT) {
        this.CONSENT = CONSENT;
    }

    public String getNOK() {
        return NOK;
    }

    public void setNOK(String NOK) {
        this.NOK = NOK;
    }

    public String getANCNO() {
        return ANCNO;
    }

    public void setANCNO(String ANCNO) {
        this.ANCNO = ANCNO;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getFNAME() {
        return FNAME;
    }

    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }

    public String getPHONENO() {
        return PHONENO;
    }

    public void setPHONENO(String PHONENO) {
        this.PHONENO = PHONENO;
    }
   
}
