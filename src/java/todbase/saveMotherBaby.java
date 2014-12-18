package todbase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sender.dbConn;
import sendSMS.dbConnect;

/**
 *
 * @author SIXTYFOURBIT
 */
public class saveMotherBaby extends HttpServlet {

//session
    HttpSession session;
//Maternal profile fields to be updated
    String institution, ANC_No, FName, SName, LName, Age, Gravida, parity, lmp, edd, height, maritalStatus, education, PhoneNo, occupation, nok, nok_relationship, NOKPhoneNo;
    String prevpreg_old_rows, prevpreg_new_rows;
    String prev_table_id, ps_no_of_old_rows, ps_no_of_rows;
    String delivery_update_or_save;
    String vit_new_no_of_rows, vit_old_no_of_rows;
    dbConnect conn = new dbConnect();

String msg="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            session = request.getSession();

msg="<font color=\"green\">mother details edited succesfully!</font>";

            //=============CONNECTION TO DATABASE=====@EMMANUELS DBCONN======
            dbConn econn = new dbConn();


             String computername = InetAddress.getLocalHost().getHostName();
            

            //receive the values through requestparameter

            //=============================RECEIVE MATERNAL PROFILE VALUES=============   
             
             String motherID =""; 
             String facilityname = request.getParameter("facility");
             
             
             if(request.getParameter("motherID")!=null&&!request.getParameter("motherID").equals("")){
                 motherID=request.getParameter("motherID");
             }
             else{
                 
                 motherID=uniqueid().trim();
             
             }
             
             
            ANC_No = request.getParameter("ancNo");

            if (ANC_No != null) {
                ANC_No = ANC_No.replace("'", "");
            }
            institution = request.getParameter("institution");

            if (institution != null) {
                institution = institution.replace("'", "");
            }
            FName = request.getParameter("FName");

            if (FName != null) {
                FName = FName.replace("'", "");
            }
            SName = request.getParameter("SName");
            if (SName == null) {
                SName = "";
            }

            SName = SName.replace("'", "");


            LName = request.getParameter("LName");
            LName = LName.replace("'", "");

            Age = request.getParameter("Age");

            Gravida = request.getParameter("Gravida");

            parity = request.getParameter("parity");

            height = request.getParameter("height");

            maritalStatus = request.getParameter("maritalStatus");

            education = request.getParameter("education");

            PhoneNo = request.getParameter("PhoneNo");

            occupation = request.getParameter("occupation");

            nok = request.getParameter("nok");

            nok_relationship = request.getParameter("relationship");

            NOKPhoneNo = request.getParameter("NOKPhoneNo");

            lmp = request.getParameter("lmp");

            edd = request.getParameter("edd");

            //================end of Receiving maternal profile details=================          






            //=======receive previous pregnacy details using arraylists loop.. 

            prevpreg_new_rows = request.getParameter("no_of_rows");
            prevpreg_old_rows = request.getParameter("no_of_old_rows");


            for (int a = 1; a <= Integer.parseInt(prevpreg_old_rows); a++) {

                String year, place, deltype, segid;
                year = request.getParameter("year" + a);
                place = request.getParameter("place_of_delivery" + a);
                place = place.replace("'", "");
                deltype = request.getParameter("Type_of_delivery" + a);
                segid = request.getParameter("surgicalid" + a);

                if (place.trim() != null && deltype.trim() != null && year.trim() != null) {
                    econn.st_2.executeUpdate("Update sergical_history set Year='" + year + "',delivery_place='" + place + "',delivery_type='" + deltype + "' where sergical_id='" + segid + "'");
               
                
                    
          
                
                
                }

            }



            //save the new values
            for (int a = 1; a <= Integer.parseInt(prevpreg_new_rows); a++) {
                String year, place, deltype, order;
                order = request.getParameter("new_preg_0rder" + a);
                year = request.getParameter("new_year" + a);
                place = request.getParameter("new_place_of_delivery" + a);
                if (place != null) {
                    place = place.replace("'", "");
                }
                deltype = request.getParameter("new_Type_of_delivery" + a);


                if ((place.trim() != null && !place.trim().equals("")) && (!deltype.trim().equals("") && deltype.trim() != null) && (!year.trim().equals("") && year.trim() != null)) {
                    econn.st_2.executeUpdate("insert into sergical_history set motherid='"+motherID+"',  anc_no='" + ANC_No + "', Year='" + year + "',delivery_place='" + place + "',delivery_type='" + deltype + "' ,pregnancy_order='" + order + "'");
                }

            }








// =================================================update present pregnancy details==================================================================================================
            String p = "";


            int pregnancyrows = 0;
            if (request.getParameter("pregnancy_old_rows") != null && !request.getParameter("pregnancy_old_rows").equals("")) {
                p = request.getParameter("pregnancy_old_rows");
                pregnancyrows = Integer.parseInt(p);
            }
            
          //  System.out.println("pregnancyrows" + pregnancyrows);
            for (int i = 1; i <= pregnancyrows; i++) {
                
                
                String visitNo = "";
            String date = "";
            String weight = "";
            String nextVisit = "";
            String PresentPregnancy = "";
            int visitNum = 0;
                
                
                if (request.getParameter("visitNo" + i) != null && !request.getParameter("visitNo" + i).equals("")) {
                    visitNo = request.getParameter("visitNo" + i);
//                  visitNum = Integer.parseInt(visitNo);
//               System.out.println("visitNo"+visitNo);    
                }
                if (request.getParameter("date" + i) != null && !request.getParameter("date" + i).equals("")) {
                    date = request.getParameter("date" + i);
//                  System.out.println("date"+date);
                }
                if (request.getParameter("weight" + i) != null && !request.getParameter("weight" + i).equals("")) {
                    weight = request.getParameter("weight" + i);
//                  System.out.println("weight"+weight);
                }
                if (request.getParameter("NextVisit" + i) != null && !request.getParameter("NextVisit" + i).equals("")) {
                    nextVisit = request.getParameter("NextVisit" + i);
                }
                if (request.getParameter("PresentPregnancy" + i) != null && !request.getParameter("PresentPregnancy" + i).equals("")) {
                    PresentPregnancy = request.getParameter("PresentPregnancy" + i);
                }


//              System.out.println("visitNo"+visitNum);
//              System.out.println("date"+date);
//              System.out.println("weight"+weight);
//              System.out.println("nextVisit"+nextVisit);
//              System.out.println("PresentPregnancy"+PresentPregnancy);
                String query = "";
                if (!visitNo.equals("") && visitNo != null && !date.equals("") && date != null && weight != null && !weight.equals("") && !nextVisit.equals("") && nextVisit != null && !PresentPregnancy.equals("") && PresentPregnancy != null) {
                    query = "update present_pregnancy set visit_no='" + visitNo + "',date='" + date + "',Weight='" + weight + "',Next_visit='" + nextVisit + "' where PresentPregnancy='" + PresentPregnancy + "'";

                    conn.state.executeUpdate(query);

                }

            }
            
            
            
            
            
            String nextVisit="";
String updatePresentPreg="";
String ancVisit="";
ArrayList dates = new ArrayList();
String querys = "select * from anc_visits where ancNo='"+ANC_No+"'";
conn.rs1 = conn.state2.executeQuery(querys);

// System.out.println("nnnn"+nextVisit);
while(conn.rs1.next()){

dates.add(conn.rs1.getString(1));

}
for (int i = 0; i < dates.size(); i++) {
int j=i+1;


if (request.getParameter("NextVisit" + j) != null && !request.getParameter("NextVisit" + j).equals("")) {
nextVisit = request.getParameter("NextVisit" + j);
System.out.println("nextVisit....."+nextVisit);
}


updatePresentPreg="update anc_visits set VisitDate='" + nextVisit + "',Status='Not Attended' where ancVisitsID='" + dates.get(i) + "' ";
System.out.println("updatePresentPreg_______________"+updatePresentPreg);
conn.state.executeUpdate(updatePresentPreg);


}


            
            
            
            
            
            
            

            //=================================== for inserting data for the new prefix in the present pregnancy table==============================

            String new_visitNo = "";
            String new_date = "";
            String new_weight = "";
            String new_nextVisit = "";
            String new_PresentPregnancy = "";
            String ancno = "";
            int new_VisitNum = 0;
            String pregRows = "";
            int pregRow = 0;
            if (request.getParameter("pregnancy") != null && !request.getParameter("pregnancy").equals("")) {
                pregRows = request.getParameter("pregnancy");
                pregRow = Integer.parseInt(pregRows);
            }
            if (request.getParameter("ancNo") != null && !request.getParameter("ancNo").equals("")) {
                ancno = request.getParameter("ancNo");
//               System.out.println("ancno_____________"+ancno);    
            }
            for (int j = 1; j <= pregRow; j++) {
                if (request.getParameter("new_VisitNo" + j) != null && !request.getParameter("new_VisitNo" + j).equals("")) {
                    new_visitNo = request.getParameter("new_VisitNo" + j);
//                  new_VisitNum = Integer.parseInt(new_visitNo);
//               System.out.println("visitNo_____________"+visitNo);    
                }

                if (request.getParameter("new_date" + j) != null && !request.getParameter("new_date" + j).equals("")) {
                    new_date = request.getParameter("new_date" + j);
//                  System.out.println("new_date________________"+new_date);
                }
                if (request.getParameter("new_weight" + j) != null && !request.getParameter("new_weight" + j).equals("")) {
                    new_weight = request.getParameter("new_weight" + j);
//                  System.out.println("weight________________"+weight);
                }
                if (request.getParameter("new_NextVisit" + j) != null && !request.getParameter("new_NextVisit" + j).equals("")) {
                    new_nextVisit = request.getParameter("new_NextVisit" + j);
//                   System.out.println("new_nextVisit________________________"+new_nextVisit);
                }



                String query2 = "";

                if (!new_visitNo.equals("") && new_visitNo != null && !ancno.equals("") && ancno != null && new_date != null && !new_date.equals("") && !new_weight.equals("") && new_weight != null && !new_nextVisit.equals("") && new_nextVisit != null) {
                    query2 = "insert into present_pregnancy(motherid, anc_no,visit_no,date,Weight,Next_visit)"
                            + " VALUES ('"+motherID+"','" + ancno + "','" + new_visitNo + "','" + new_date + "','" + new_weight + "','" + new_nextVisit + "')";

                    System.out.println(query2);

                    conn.state.executeUpdate(query2);

                    String queryInsert = "insert into anc_visits set motherid='"+motherID+"',  ancNo='" + ancno + "', VisitDate='" + new_nextVisit + "',Status='Not Attended' ";
                    System.out.println(queryInsert);

                    conn.state.executeUpdate(queryInsert);
                    
                    String queryInsert1 = "update mother_details set VisitDate='" + new_nextVisit + "' where motherID='" + motherID + "'";
                    System.out.println(queryInsert1);

                    conn.state.executeUpdate(queryInsert1);

                }


            }




// =====================================updating data in the iron folate table============================================= 






//=========================== post data to prevservices==============================
            //prev_table_id
            ps_no_of_rows = request.getParameter("ps_no_of_rows");
            ps_no_of_old_rows = request.getParameter("ps_no_of_old_rows");

            //update the existing values first

            for (int a = 1; a <= Integer.parseInt(ps_no_of_old_rows); a++) {
                String ps_old_date, old_nextvisit, tableid;
                ps_old_date = request.getParameter("ps_old_date" + a);
                old_nextvisit = request.getParameter("old_nextvisit" + a);
                if (old_nextvisit == null) {
                    old_nextvisit = "not applicable";
                }
                tableid = request.getParameter("prev_table_id" + a);


                if (ps_old_date != null && !ps_old_date.equals("")) {
                    econn.st_2.executeUpdate("Update preventive_services set date='" + ps_old_date + "',next_visit_date='" + old_nextvisit + "' where table_id='" + tableid + "'");


                }//end of if
            }


            //save the new values    

            for (int a = 1; a <= Integer.parseInt(ps_no_of_rows); a++) {
                String previd, prevdate, nextvisit;
                prevdate = request.getParameter("date" + a);
                nextvisit = request.getParameter("nextvisit" + a);
                if (nextvisit == null) {
                    nextvisit = "not applicable";
                }
                previd = request.getParameter("prevention" + a);



                if (prevdate != null && !prevdate.equals("") && previd != null && !previd.toString().equals("")) {
                    econn.st_2.executeUpdate("insert into preventive_services set motherid='"+motherID+"', anc_no='" + ANC_No + "', date='" + prevdate + "', next_visit_date='" + nextvisit + "',prentive_id='" + previd + "'");
                }

            }





            //-----------------------------------------------------------end of prevention services--------------------        




// =====================================updating data in the iron folate table============================================= 

            String iron = "";
          
          
            String anc_no = "";
            int ironfolate = 0;
            if (request.getParameter("ironfolate_old_rows") != null && request.getParameter("ironfolate_old_rows") != "") {
                iron = request.getParameter("ironfolate_old_rows");
                ironfolate = Integer.parseInt(iron);
            }




            System.out.println("ironfolate" + ironfolate);

            // for loop for looping through the update in the iron folate div
            for (int l=1; l<=ironfolate; l++) {
                  String DateGiven = "";
            String ironfolateID = "";
              String ID = "";
                if (request.getParameter("DateGiven"+l) != null && !request.getParameter("DateGiven"+l).equals("")) {
                    DateGiven = request.getParameter("DateGiven" + l);
               System.out.println("DateGiven"+DateGiven);    
                }
                if (request.getParameter("ironfolateID"+l) != null && !request.getParameter("ironfolateID"+l).equals("")) {
                    ironfolateID = request.getParameter("ironfolateID" + l);
               System.out.println("ironfolateID "+ironfolateID);     
                }
                if (request.getParameter("ID"+l) != null && !request.getParameter("ID"+l).equals("")) {
                    ID = request.getParameter("ID"+l);
                     System.out.println("ID"+ID);  
                }
                if (!DateGiven.equals("") && DateGiven != null) {
                    String ironfolateQuery = "update ironfolate set Iron_FolateID='" + ironfolateID + "',DateGiven='" + DateGiven + "' where IronFolateID='" + ID + "'";

                    System.out.println("update"+ironfolateQuery);

                    conn.state.executeUpdate(ironfolateQuery);


                }
            }


            //=================================== for inserting data for the new prefix in the iron folate table==============================
            String ironfolateNew = "";



           
            if (request.getParameter("ancNo") != null && !request.getParameter("ancNo").equals("")) {
                anc_no = request.getParameter("ancNo");
//                     System.out.println("ANCNO__________"+anc_no);  
            }
            for (int k = 1; k <= ironfolate; k++) {
                 String New_DateGiven = "";
            String New_ironfolateID = "";
                if (request.getParameter("New_DateGiven" + k) != null && !request.getParameter("New_DateGiven" + k).equals("")) {
                    New_DateGiven = request.getParameter("New_DateGiven" + k);
              System.out.println("DATE GIVEN _________________"+New_DateGiven);    
                }

                if (request.getParameter("New_ironfolateID" + k) != null && !request.getParameter("New_ironfolateID" + k).equals("")) {
                    New_ironfolateID = request.getParameter("New_ironfolateID" + k);
                  System.out.println("NEW IRON FOLATE____________"+New_ironfolateID);
                }


                if (!New_DateGiven.equals("") && New_DateGiven != null ) {

                    String queryIronFolate = "insert into ironfolate(motherid, ancNo,Iron_FolateID,DateGiven)"
                            + " VALUES ('"+motherID+"','" + anc_no + "','" + New_ironfolateID + "','" + New_DateGiven + "')";

                    System.out.println("insert"+queryIronFolate);

                    try {
                        conn.state.executeUpdate(queryIronFolate);
                        
                    } catch (SQLException EX) {
                        System.out.println("ERROR" + EX.toString());
                    }


                }

            }














            //=====================================================end of ironfolate                




//====================================================SAVE AND UPDATE DELIVERY DETAILS====================================           
//=======================================================================================================================


            //RECEIVE THE VALUES FROM THE FORM
            String preg_duration, delivery_mode, delivery_date, mother_condition, apgar_score_1, apgar_score_5, apgar_score_10, rescuscitation, delivery_place = "0", conducted_by, conductor_others, delivery_place_others;

            delivery_update_or_save = request.getParameter("delivery_update_or_save");
            //if there are no any values in the database for form delivery, delivery_update_or_save =0, otherwise an update should be done
            if (delivery_update_or_save.equals("0")) {


                preg_duration = request.getParameter("preg_duration");
                preg_duration = preg_duration.replace("'", "");
                delivery_mode = request.getParameter("delivery_mode");
                delivery_mode = delivery_mode.replace("'", "");
                delivery_date = request.getParameter("delivery_date");
                mother_condition = request.getParameter("mother_condition");
                apgar_score_1 = request.getParameter("apgar_score_1");
                apgar_score_1 = apgar_score_1.replace("'", "");
                apgar_score_5 = request.getParameter("apgar_score_5");
                apgar_score_5 = apgar_score_5.replace("'", "");
                if(request.getParameter("apgar_score_10")==null){
                 apgar_score_10="";   
                }
                
                apgar_score_10 = request.getParameter("apgar_score_10");
                apgar_score_10 = apgar_score_10.replace("'", "");
                
                rescuscitation = request.getParameter("rescuscitation");
                delivery_place = request.getParameter("delivery_place");
                if (delivery_place.equals("")) {
                    delivery_place = "0";
                }
                conducted_by = request.getParameter("conducted_by");
                conductor_others = request.getParameter("conductorothers");
                if (conductor_others == null) {
                    conductor_others = "";
                }

                delivery_place_others = request.getParameter("deliveryothers");
                if (delivery_place_others == null) {
                    delivery_place_others = "";
                }

                //save the values into the database
                if (preg_duration != null && !delivery_place.equals("0") && delivery_date != null && apgar_score_10 != null && rescuscitation != null) {

                    //save to database

                    econn.st_6.executeUpdate("insert into  delivery set motherid='"+motherID+"',  anc_no='" + ANC_No + "',pregnancy_duration='" + preg_duration + "', delivery_mode='" + delivery_mode + "', date='" + delivery_date + "', mum_condition='" + mother_condition + "', apgar_score_1='" + apgar_score_1 + "',apgar_score_5='" + apgar_score_5 + "',apgar_score_10='" + apgar_score_10 + "',rescuscitation='" + rescuscitation + "',delivery_place='" + delivery_place + "',conductor_id='" + conducted_by + "',conductor_other='" + conductor_others + "',delivery_place_other='" + delivery_place_others + "'");
  if (session.getAttribute("userid") != null) {
         Date dat= new Date();
         String inserter = "insert into audit set host_comp='" + computername + "' , action='inserted details for a mother whose anc no is "+ANC_No+" in the Mother_baby form',time='" + dat + "',actor_id='" + session.getAttribute("userid") + "'";

                    econn.st3.executeUpdate(inserter);
                
            }

                }
            }//end of if
            else {

                //=========================UPDATE==================================          

                preg_duration = request.getParameter("old_preg_duration");
                preg_duration.replace("'", "");
                delivery_mode = request.getParameter("old_delivery_mode");
                delivery_mode.replace("'", "");
                delivery_date = request.getParameter("old_delivery_date");
                mother_condition = request.getParameter("old_mother_condition");
                apgar_score_1 = request.getParameter("old_apgar_score_1");
                apgar_score_1.replace("'", "");
                apgar_score_5 = request.getParameter("old_apgar_score_5");
                apgar_score_5.replace("'", "");
                apgar_score_10 = request.getParameter("old_apgar_score_10");
                apgar_score_10.replace("'", "");
                rescuscitation = request.getParameter("old_rescuscitation");
                delivery_place = request.getParameter("old_delivery_place");


                conducted_by = request.getParameter("old_conducted_by");
                conductor_others = request.getParameter("oldconductorothers");
                if (conductor_others == null) {
                    conductor_others = "";
                }

                delivery_place_others = request.getParameter("deliveryothers");
                if (delivery_place_others == null) {
                    delivery_place_others = "";
                }

                //save the values into the database
                if (preg_duration != null && delivery_date != null && !mother_condition.equals("") && apgar_score_10 != null && rescuscitation != null) {

                    //save to database

                    econn.st_6.executeUpdate("UPDATE delivery set pregnancy_duration='" + preg_duration + "', delivery_mode='" + delivery_mode + "', date='" + delivery_date + "', mum_condition='" + mother_condition + "', apgar_score_1='" + apgar_score_1 + "',apgar_score_5='" + apgar_score_5 + "',apgar_score_10='" + apgar_score_10 + "',rescuscitation='" + rescuscitation + "',delivery_place='" + delivery_place + "',conductor_id='" + conducted_by + "',conductor_other='" + conductor_others + "',delivery_place_other='" + delivery_place_others + "' WHERE anc_no='" + ANC_No + "'");


                }

            }//end of if

            //========================END OF UPDATE=====================



            //------------------------------------------------------------------------------------------------------------------          



            //================================================ for post natal mum insert ===================================  



            String postnatalMum = "";


            int Mumrows = 0;
            if (request.getParameter("PostNatal_old_rows") != null && !"".equals(request.getParameter("PostNatal_old_rows"))) {
                postnatalMum = request.getParameter("PostNatal_old_rows");
                Mumrows = Integer.parseInt(postnatalMum);
            }
           

            System.out.println("PostNatal_old_rows" + Mumrows);
            for (int i = 1; i <= Mumrows; i++) {
                 String VisitMum = "";
            String BP = "";
            String GeneralCondition = "";
            String Breast = "";
            String Involution = "";
            String ART = "";
            String HAART = "";
            String CTX = "";
            String FPCounselling = "";
            String time = "";
            String MumID = "";
                if (request.getParameter("Date" + i) != null && !request.getParameter("Date" + i).equals("")) {
                    VisitMum = request.getParameter("Date" + i);
//                  visitNum = Integer.parseInt(visitNo);
//               System.out.println("VisitMum"+VisitMum);    
                }
                if (request.getParameter("BP" + i) != null && !request.getParameter("BP" + i).equals("")) {
                    BP = request.getParameter("BP" + i);
//                  System.out.println("BP"+BP); 
                }
                if (request.getParameter("GeneralCondition" + i) != null && !request.getParameter("GeneralCondition" + i).equals("")) {
                    GeneralCondition = request.getParameter("GeneralCondition" + i);
//                  System.out.println("GeneralCondition"+GeneralCondition);
                }
                if (request.getParameter("Breast" + i) != null && !request.getParameter("Breast" + i).equals("")) {
                    Breast = request.getParameter("Breast" + i);
//                  System.out.println("Breast"+Breast);
                }
                if (request.getParameter("Involution" + i) != null && !request.getParameter("Involution" + i).equals("")) {
                    Involution = request.getParameter("Involution" + i);
//                   System.out.println("Involution"+Involution);
                }
                if (request.getParameter("ART" + i) != null && !request.getParameter("ART" + i).equals("")) {
                    ART = request.getParameter("ART" + i);
//                   System.out.println("ART"+ART);
                }
                if (request.getParameter("HAART" + i) != null && !request.getParameter("HAART" + i).equals("")) {
                    HAART = request.getParameter("HAART" + i);
//                   System.out.println("HAART"+HAART);
                }
                if (request.getParameter("CTX" + i) != null && !request.getParameter("CTX" + i).equals("")) {
                    CTX = request.getParameter("CTX" + i);
//                   System.out.println("CTX"+CTX);
                }
                if (request.getParameter("FPCounselling" + i) != null && !request.getParameter("FPCounselling" + i).equals("")) {
                    FPCounselling = request.getParameter("FPCounselling" + i);
//                   System.out.println("FPCounselling"+FPCounselling);
                }

                if (request.getParameter("post_natal_id" + i) != null && !request.getParameter("post_natal_id" + i).equals("")) {
                    MumID = request.getParameter("post_natal_id" + i);
//                   System.out.println("MumID"+MumID);
                }




                String queryMum = "";
                if (!VisitMum.equals("") && VisitMum != null && !BP.equals("") && BP != null && GeneralCondition != null && !GeneralCondition.equals("") && !Involution.equals("") && Involution != null
                        && !ART.equals("") && ART != null && !HAART.equals("") && HAART != null && !CTX.equals("") && CTX != null && !FPCounselling.equals("") && FPCounselling != null) {
                    queryMum = "update post_natal_examination set visit_date='" + VisitMum + "',general_condition='" + GeneralCondition + "',blood_pressure='" + BP + "',breast='" + Breast + "',uterus_involution='" + Involution + "',given_ART='" + ART + "', HAART='" + HAART + "',"
                            + " fp_counselling='" + FPCounselling + "' where post_natal_id='" + MumID + "'";
                    System.out.println("queryMum+++++++" + queryMum);
                    conn.state.executeUpdate(queryMum);


                }

            }

            //=================================== for inserting data for the new prefix in the present pregnancy table==============================

            String mumsRow = "";
            int mumsRow1 = 0;
            if (request.getParameter("PostNatal") != null && !request.getParameter("PostNatal").equals("")) {
                mumsRow = request.getParameter("PostNatal");
                mumsRow1 = Integer.parseInt(mumsRow);
//                      System.out.println("mumsRow1++++++++++"+mumsRow1);
            }
            if (request.getParameter("ancNo") != null && !request.getParameter("ancNo").equals("")) {
                ancno = request.getParameter("ancNo");
//               System.out.println("ancno_____________"+ancno);    
            }
            for (int k = 1; k <= mumsRow1; k++) {
                
            String New_VisitMum = "";
            String New_BP = "";
            String New_GeneralCondition = "";
            String New_Breast = "";
            String New_Involution = "";
            String New_ART = "";
            String New_HAART = "";
            String New_CTX = "";
            String New_FPCounselling = "";
            String hrID = "";
                if (request.getParameter("New_Date" + k) != null && !request.getParameter("New_Date" + k).equals("")) {
                    New_VisitMum = request.getParameter("New_Date" + k);
//                  visitNum = Integer.parseInt(visitNo);
//               System.out.println("New_VisitMum"+New_VisitMum);    
                }
                if (request.getParameter("New_BP" + k) != null && !request.getParameter("New_BP" + k).equals("")) {
                    New_BP = request.getParameter("New_BP" + k);
                    System.out.println("New_BP" + New_BP);
                }
                if (request.getParameter("New_GeneralCondition" + k) != null && !request.getParameter("New_GeneralCondition" + k) .equals("")) {
                    New_GeneralCondition = request.getParameter("New_GeneralCondition" + k);
//                  System.out.println("New_GeneralCondition"+New_GeneralCondition);
                }
                if (request.getParameter("New_Breast" + k) != null && !request.getParameter("New_Breast" + k).equals("")) {
                    New_Breast = request.getParameter("New_Breast" + k);
//                  System.out.println("New_Breast"+New_Breast);
                }
                if (request.getParameter("New_Involution" + k) != null && !request.getParameter("New_Involution" + k).equals("")) {
                    New_Involution = request.getParameter("New_Involution" + k);
//                   System.out.println("New_Involution"+New_Involution);

                }
                if (request.getParameter("New_ART" + k) != null && !request.getParameter("New_ART" + k).equals("")) {
                    New_ART = request.getParameter("New_ART" + k);
//                   System.out.println("New_ART"+New_ART);
                }
                if (request.getParameter("New_HAART" + k) != null && !request.getParameter("New_HAART" + k).equals("")) {
                    New_HAART = request.getParameter("New_HAART" + k);
                    System.out.println("New_HAART" + New_HAART);
                }
                if (request.getParameter("New_CTX" + k) != null && !request.getParameter("New_CTX" + k).equals("")) {
                    New_CTX = request.getParameter("New_CTX" + k);
//                   System.out.println("New_CTX"+New_CTX);
                }
                if (request.getParameter("New_FPCounselling" + k) != null && !request.getParameter("New_FPCounselling" + k).equals("")) {
                    New_FPCounselling = request.getParameter("New_FPCounselling" + k);
//                   System.out.println("New_FPCounselling"+New_FPCounselling);
                }
                String New_HR = "";
                if (request.getParameter("New_HRID" + k) != null && !request.getParameter("New_HRID" + k) .equals("")) {
                    New_HR = request.getParameter("New_HRID" + k);
//                   System.out.println("hrID"+hrID);
                }


                String queryMumInsert = "";

                if (!New_VisitMum.equals("") && New_VisitMum != null && !New_BP.equals("") && New_BP != null && New_GeneralCondition != null && !New_GeneralCondition.equals("") && !New_Involution.equals("") && New_Involution != null
                        && !New_ART.equals("") && New_ART != null && !New_HAART.equals("") && New_HAART != null && !New_CTX.equals("") && New_CTX != null && !New_FPCounselling.equals("") && New_FPCounselling != null) {
                    queryMumInsert = "insert into post_natal_examination ( motherid, anc_id,visit_date,blood_pressure,general_condition,breast,uterus_involution,given_ART, HAART, fp_counselling,time_id)"
                            + " VALUES('"+motherID+"','" + ancno + "','" + New_VisitMum + "','" + New_BP + "','" + New_GeneralCondition + "','" + New_Breast + "','" + New_Involution + "','" + New_ART + "','" + New_HAART + "','" + New_FPCounselling + "','" + New_HR + "')";

                    System.out.println(queryMumInsert);

                    conn.state.executeUpdate(queryMumInsert);
                }


            }






            //====================Post natal baby updates====================================================    



            String postnatalBaby = "";


            int Babyrows = 0;
            if (request.getParameter("PostNatalBaby_old_rows") != null && request.getParameter("PostNatalBaby_old_rows") != "") {
                postnatalBaby = request.getParameter("PostNatalBaby_old_rows");
                Babyrows = Integer.parseInt(postnatalBaby);

                System.out.println("PostNatalBaby_old_rows" + Babyrows);
            }
          



            for (int a = 1; a <= Babyrows; a++) {
                  String Condition = "";
            String FeedingMethod = "";
            String UmbilicalCord = "";
            String Immunization = "";
            String infantARV = "";
            String infantCTX = "";
            String baby_id = "";
                if (request.getParameter("Condition" + a) != null && !request.getParameter("Condition" + a).equals("")) {
                    Condition = request.getParameter("Condition" + a);
//                  visitNum = Integer.parseInt(visitNo);
//               System.out.println("Condition"+Condition);    
                }
                if (request.getParameter("FeedingMethod" + a) != null && !request.getParameter("FeedingMethod" + a).equals("")) {
                    FeedingMethod = request.getParameter("FeedingMethod" + a);
//                  System.out.println("FeedingMethod"+FeedingMethod); 
                }

                if (request.getParameter("UmbilicalCord" + a) != null && !request.getParameter("UmbilicalCord" + a).equals("")) {
                    UmbilicalCord = request.getParameter("UmbilicalCord" + a);
//                  System.out.println("UmbilicalCord"+UmbilicalCord);
                }
                if (request.getParameter("Immunization" + a) != null && !request.getParameter("Immunization" + a).equals("")) {
                    Immunization = request.getParameter("Immunization" + a);
//                  System.out.println("Immunization"+Immunization);
                }
                if (request.getParameter("infantARV" + a) != null && !request.getParameter("infantARV" + a).equals("")) {
                    infantARV = request.getParameter("infantARV" + a);
//                   System.out.println("infantARV"+infantARV);
                }
                if (request.getParameter("infantCTX" + a) != null && !request.getParameter("infantCTX" + a).equals("")) {
                    infantCTX = request.getParameter("infantCTX" + a);
//                   System.out.println("infantCTX"+infantCTX);
                }


                if (request.getParameter("baby_id" + a) != null && !request.getParameter("baby_id" + a).equals("")) {
                    baby_id = request.getParameter("baby_id" + a);
//                   System.out.println("baby_id"+baby_id);
                }




                String queryBaby = "";

                 if(!Condition.equals("") && Condition!=null && !FeedingMethod.equals("") && FeedingMethod!=null && UmbilicalCord!=null && !UmbilicalCord.equals("") && !Immunization.equals("") && Immunization!=null 
                     && !infantARV.equals("") && infantARV!=null && !infantCTX.equals("") && infantCTX!=null  && !baby_id.equals("") && baby_id !=null){
                queryBaby = "update post_natal_baby set baby_condition='" + Condition + "',feeding_method='" + FeedingMethod + "',"
                        + "umblical_cord='" + UmbilicalCord + "',immunization_status='" + Immunization + "',given_ARV='" + infantARV + "',given_CTX='" + infantCTX + "' where baby_id='" + baby_id + "'";
                System.out.println("queryMum+++++++" + queryBaby);

                conn.state.executeUpdate(queryBaby);

                }
            }




            //=================================== for inserting data for the new prefix in the post natal mum table==============================

            int Babyrows1 = 0;
            String postnatalBaby1 = "";
            if (request.getParameter("PostNatalBaby") != null && !request.getParameter("PostNatalBaby").equals("")) {
                postnatalBaby1 = request.getParameter("PostNatalBaby");
                Babyrows1 = Integer.parseInt(postnatalBaby1);

                System.out.println("PostNatalBaby " + Babyrows);
            }
            String ANC = "";
            if (request.getParameter("ancNo") != null && !request.getParameter("ancNo").equals("")) {
                ANC = request.getParameter("ancNo");
                System.out.println("ancno_____________" + ancno);
            }
            
            for (int a = 1; a <= Babyrows1; a++) {
                String New_Condition = "";
            String New_FeedingMethod = "";
            String New_UmbilicalCord = "";
            String New_Immunization = "";
            String New_infantARV = "";
            String New_infantCTX = "";
            String New_HourID = "";
                if (request.getParameter("New_Condition" + a) != null && !request.getParameter("New_Condition" + a).equals("")) {
                    New_Condition = request.getParameter("New_Condition" + a);
//                  visitNum = Integer.parseInt(visitNo);
//               System.out.println("New_Condition"+New_Condition);    
                }
                if (request.getParameter("New_FeedingMethod" + a) != null && !request.getParameter("New_FeedingMethod" + a).equals("")) {
                    New_FeedingMethod = request.getParameter("New_FeedingMethod" + a);
//                  System.out.println("New_FeedingMethod"+New_FeedingMethod); 
                }

                if (request.getParameter("New_UmbilicalCord" + a) != null && !request.getParameter("New_UmbilicalCord" + a).equals("")) {
                    New_UmbilicalCord = request.getParameter("New_UmbilicalCord" + a);
//                  System.out.println("New_UmbilicalCord"+New_UmbilicalCord);
                }
                if (request.getParameter("New_Immunization" + a) != null && !request.getParameter("New_Immunization" + a).equals("")) {
                    New_Immunization = request.getParameter("New_Immunization" + a);
//                  System.out.println("New_Immunization"+New_Immunization);
                }
                if (request.getParameter("New_infantARV" + a) != null && !request.getParameter("New_infantARV" + a).equals("")) {
                    New_infantARV = request.getParameter("New_infantARV" + a);
//                   System.out.println("New_infantARV"+New_infantARV);
                }
                if (request.getParameter("New_infantCTX" + a) != null && !request.getParameter("New_infantCTX" + a).equals("")) {
                    New_infantCTX = request.getParameter("New_infantCTX" + a);
//                   System.out.println("New_infantCTX"+New_infantCTX);
                }
                if (request.getParameter("New_HR" + a) != null && !request.getParameter("New_HR" + a).equals("")) {
                    New_HourID = request.getParameter("New_HR" + a);
                    System.out.println("New_HR" + New_HourID);
                }






              

                if (!New_Condition.equals("") && New_Condition != null && !New_FeedingMethod.equals("") && New_FeedingMethod != null && New_UmbilicalCord != null && !New_UmbilicalCord.equals("") && !New_Immunization.equals("") && New_Immunization != null
                        && !New_infantARV.equals("") && New_infantARV != null && !New_infantCTX .equals("") && New_infantCTX != null) {
                      String queryBabyInsert = "";
                    queryBabyInsert = "insert into post_natal_baby(motherid,anc_no,baby_condition,feeding_method,umblical_cord,immunization_status,given_ARV,given_CTX,hour_id)"
                            + "VALUES('"+motherID+"','" + ANC + "','" + New_Condition + "','" + New_FeedingMethod + "','" + New_UmbilicalCord + "','" + New_Immunization + "','" + New_infantARV + "','" + New_infantCTX + "','" + New_HourID + "')";
                    
                    
                    System.out.println("queryBabyInsert+++++++" + queryBabyInsert);

                    conn.state.executeUpdate(queryBabyInsert);

//                 }
                }


            }


           //========================================== insert into cervical cancer table ===============================================================
 
 
 
        String ANC1="";
          if(request.getParameter("ancNo")!= null && !request.getParameter("ancNo").equals("")){
                  ANC1= request.getParameter("ancNo");
               //System.out.println("ancno_____________"+ancno);    
              }
            
              int count=0;
              if((request.getParameter("New_CervixDate1")!= null || !request.getParameter("New_CervixDate1").equals(""))&&  (request.getParameter("New_CervixDate2").equals("") || request.getParameter("New_CervixDate2")==null)){
              count=1;
              }
              else if((request.getParameter("New_CervixDate1")== null || request.getParameter("New_CervixDate1").equals(""))&& (!request.getParameter("New_CervixDate2").equals("") || request.getParameter("New_CervixDate2")!=null)){
              count=2;
              }
              else if((request.getParameter("New_CervixDate1")!= null || !request.getParameter("New_CervixDate1").equals(""))&&  (!request.getParameter("New_CervixDate2").equals("") || request.getParameter("New_CervixDate2")!=null)){
              count=2;
              }
            
             
 for(int a=1;a<=count;a++) {
      String New_Cervix="";
             String New_CervixDate="";
             String New_Tests="";
             String New_Suspect="";
             String New_HSIL="";
             String New_Cryo="";
             String New_Leep="";
             String New_Others="";
             String New_Overt="";
             String New_Referral="";
             String test_id="";
            
              if(request.getParameter("New_CervixDate"+a)!= null && !request.getParameter("New_CervixDate"+a).equals("")){
                  New_CervixDate= request.getParameter("New_CervixDate"+a);
//                  visitNum = Integer.parseInt(visitNo);
               //System.out.println("New_CervixDate"+New_CervixDate);    
              }
              if(request.getParameter("New_Cervix"+a)!= null && !request.getParameter("New_Cervix"+a).equals("")){
                  New_Cervix= request.getParameter("New_Cervix"+a);
//                  visitNum = Integer.parseInt(visitNo);
              // System.out.println("New_Cervix"+New_Cervix);    
              }
              else if(request.getParameter("New_Cervix"+a)== null  ){
                  New_Cervix= "No";
//                  visitNum = Integer.parseInt(visitNo);
               //System.out.println("New_Cervix for No"+New_Cervix);    
              }
              if(request.getParameter("New_Tests"+a)!= null && !request.getParameter("New_Tests"+a).equals("")){
                  New_Tests= request.getParameter("New_Tests"+a);
                  //System.out.println("New_Tests"+New_Tests); 
              }
             
              if(request.getParameter("New_Suspect"+a)!= null && !request.getParameter("New_Suspect"+a).equals("")){
                  New_Suspect= request.getParameter("New_Suspect"+a);
                  //System.out.println("New_Suspect"+New_Suspect);
              }
               else if(request.getParameter("New_Suspect"+a)== null  ){
                  New_Suspect= "No";
//                  visitNum = Integer.parseInt(visitNo);
               //System.out.println("New_Suspect for No "+New_Suspect);    
              }
              if(request.getParameter("New_Cryo"+a)!= null && !request.getParameter("New_Cryo"+a).equals("")){
                  New_Cryo= request.getParameter("New_Cryo"+a);
                  //System.out.println("New_Cryo"+New_Cryo);
                  }
              else if(request.getParameter("New_Cryo"+a)== null  ){
                  New_Cryo= "No";
//                  visitNum = Integer.parseInt(visitNo);
               //System.out.println("New_Cryo for No "+New_Cryo);    
              }
              if(request.getParameter("New_Leep"+a)!= null && !request.getParameter("New_Leep"+a).equals("")){
                  New_Leep= request.getParameter("New_Leep"+a);
                  // System.out.println("New_Leep"+New_Leep);
                  }
              else if(request.getParameter("New_Leep"+a)== null  ){
                  New_Leep= "No";
//                  visitNum = Integer.parseInt(visitNo);
               //System.out.println("New_Leep for No "+New_Leep);    
              }
              if(request.getParameter("New_Others"+a)!= null && !request.getParameter("New_Others"+a).equals("")){
                  New_Others= request.getParameter("New_Others"+a);
                   //System.out.println("New_Others"+New_Others);
                  }
               else if(request.getParameter("New_Others"+a)== null || request.getParameter("New_Others"+a).equals("")){
                  New_Others= "";
                  
                  }
              if(request.getParameter("New_Referral"+a)!= null && !request.getParameter("New_Referral"+a).equals("")){
                  New_Referral= request.getParameter("New_Referral"+a);
                  // System.out.println("New_Referral"+New_Referral);
                  }
              if(request.getParameter("test_id"+a)!= null && !request.getParameter("test_id"+a).equals("")){
                  test_id= request.getParameter("test_id"+a);
                   //System.out.println("test_id"+test_id);
                  }
            String queryCervix="";
            

                 if(New_CervixDate!= null && !New_CervixDate.equals("")){
               queryCervix = "insert into cervical_screening(motherid,anc_no,date,test_id,cervixDone,results,cancer_suspicious,cryo,leep,others,referral)"
                       + "VALUES('"+motherID+"','"+ANC1+"','"+New_CervixDate+"','"+test_id+"','"+New_Cervix+"','"+New_Tests+"','"+New_Suspect+"','"+New_Cryo+"','"+New_Leep+"','"+New_Others+"','"+New_Referral+"')";
                // System.out.println("queryCervix+++++++"+queryCervix);
                   
                 conn.state.executeUpdate(queryCervix);
                                                                        }
    }
 
 int cervixrow1=0;
               String Cervix1="";
        if(request.getParameter("CervicalCancer")!= null && !request.getParameter("CervicalCancer").equals("")){
                    Cervix1= request.getParameter("CervicalCancer");
                      cervixrow1 = Integer.parseInt(Cervix1);
                      
                      //System.out.println("cervixrow1 "+cervixrow1 );
                  }
            
 
 
                 for(int a=1;a<=cervixrow1;a++) {
                      String New_Cervix1="";
             String New_CervixDate1="";
             String New_Tests1="";
             String New_PapCervix="";
             String New_HSIL1="";
             String New_Cryo1="";
             String New_Leep1="";
             String New_Others1="";
             String New_Overt1="";
             String New_Referral1="";
             String Paptest_id="";
 
              if(request.getParameter("New_CervixPapDate"+a)!= null && !request.getParameter("New_CervixPapDate"+a).equals("")){
                  New_CervixDate1= request.getParameter("New_CervixPapDate"+a);
//                  visitNum = Integer.parseInt(visitNo);
               
              }
              if(request.getParameter("New_PapCervix"+a)!= null && !request.getParameter("New_PapCervix"+a).equals("")){
                  New_PapCervix= request.getParameter("New_PapCervix"+a);
   
              }
              if(request.getParameter("New_Papsmear"+a)!= null && !request.getParameter("New_Papsmear"+a).equals("")){
                  New_Tests1= request.getParameter("New_Papsmear"+a);

              }
             
              
              if(request.getParameter("New_HSIL"+a)!= null && !request.getParameter("New_HSIL"+a).equals("")){
                  New_HSIL1= request.getParameter("New_HSIL"+a);

                  }
               else if(request.getParameter("New_HSIL"+a)==null ){
                   New_HSIL1= "No";
              }
              if(request.getParameter("New_Overt"+a)!= null && !request.getParameter("New_Overt"+a).equals("")){
                  New_Overt1= request.getParameter("New_Overt"+a);
                  
                  }
                else if(request.getParameter("New_Overt"+a)==null ){
                   New_Overt1= "No";
              }
              if(request.getParameter("New_PapCryo"+a)!= null && !request.getParameter("New_PapCryo"+a).equals("")){
                  New_Cryo1= request.getParameter("New_PapCryo"+a);

                  }
               else if(request.getParameter("New_PapCryo"+a)==null ){
                   New_Cryo1= "No";
              }
              if(request.getParameter("New_PapLeep"+a)!= null && !request.getParameter("New_PapLeep"+a).equals("")){
                  New_Leep1= request.getParameter("New_PapLeep"+a);
                 
                  }
              else if(request.getParameter("New_PapLeep"+a)==null ){
                   New_Leep1= "No";
              }
              if(request.getParameter("New_PapOthers"+a)!= null && !request.getParameter("New_PapOthers"+a).equals("")){
                  New_Others1= request.getParameter("New_PapOthers"+a);

                  }
              else if(request.getParameter("New_PapOthers"+a)== null || !request.getParameter("New_PapOthers"+a).equals("")){
                  New_Others1= "";
                  
                  }
             
              if(request.getParameter("New_PapReferral"+a)!= null && !request.getParameter("New_PapReferral"+a).equals("")){
                  New_Referral1= request.getParameter("New_PapReferral"+a);

                  }
              if(request.getParameter("Paptest_id"+a)!= null && !request.getParameter("Paptest_id"+a).equals("")){
                  Paptest_id= request.getParameter("Paptest_id"+a);

                  }
             
             
                String queryCervix1="";
                 
                 if(New_CervixDate1!=null && !New_CervixDate1.equals("") && !Paptest_id.equals("") && Paptest_id!=null && !New_PapCervix.equals("") && New_PapCervix!=null && New_Tests1!=null && !New_Tests1.equals("")&& !New_Cryo1.equals("") && New_Cryo1!=null 
                      && !New_Leep1.equals("") && New_Leep1!=null &&  New_HSIL1!=null && !New_Overt1.equals("") && New_Overt1!=null){
               queryCervix1 = "insert into cervical_screening(motherid,anc_no,date,test_id,cervixDone,results,cryo,leep,others,HSIL,overtCancer,referral)"
                       + "VALUES('"+motherID+"','"+ANC1+"','"+New_CervixDate1+"','"+Paptest_id+"','"+New_PapCervix+"','"+New_Tests1+"','"+New_Cryo1+"','"+New_Leep1+"','"+New_Others1+"','"+New_HSIL1+"','"+New_Overt1+"','"+New_Referral1+"')";
                // System.out.println("queryCervix1--------------"+queryCervix1);
                   
                 conn.state.executeUpdate(queryCervix1);
                 
                }
              }  
                 
         
                 //========================================== end of insert into cervical cancer table ===============================================================
                
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 //==========================================update cervical cancer table ===============================================================
 
 
 int cervixrows1=0;
               String Cervixs1="";
        if(request.getParameter("CervicalCancer_old_rows")!= null && !request.getParameter("CervicalCancer_old_rows").equals("")){
                    Cervixs1= request.getParameter("CervicalCancer_old_rows");
                      cervixrows1 = Integer.parseInt(Cervixs1);
                      
//                      System.out.println("cervixrows1 ------ "+cervixrows1 );
                  }
       
          if(request.getParameter("ancNo")!= null && !request.getParameter("ancNo").equals("")){
                  ANC1= request.getParameter("ancNo");
              // System.out.println("ancno_____________"+ancno);    
              }
             
             
 for(int a=1;a<=cervixrows1;a++) {
     
     String Cervix="";
             String CervixDate="";
             String tests="";
             String Suspect="";
            
             String Cryo="";
             String Leep="";
             String Others="";
             
             String Referral="";
             String testID="";
             String rowID="";
              if(request.getParameter("CervixDate"+a)!= null && !request.getParameter("CervixDate"+a).equals("")){
                 CervixDate= request.getParameter("CervixDate"+a);
//                  visitNum = Integer.parseInt(visitNo);
          //     System.out.println("CervixDate"+CervixDate);    
              }
              
               if(request.getParameter("Cervix"+a)!= null &&  !request.getParameter("Cervix"+a).equals("") ){
                  Cervix= request.getParameter("Cervix"+a);
//                   System.out.println("Cervix=========="+Cervix);
                  }
               else if(request.getParameter("Cervix"+a)== null){
                 Cervix= "No";
//                  visitNum = Integer.parseInt(visitNo);
               System.out.println("Cervix"+Cervix);    
              }
              if(request.getParameter("tests"+a)!= null && !request.getParameter("tests"+a).equals("")){
                 tests= request.getParameter("tests"+a);
//                  System.out.println("tests"+tests); 
              }
              
               if(request.getParameter("Suspect"+a)!= null &&  !request.getParameter("Suspect"+a).equals("") ){
                  Suspect= request.getParameter("Suspect"+a);
//                   System.out.println("Suspect=========="+Suspect);
                  }
               else if(request.getParameter("Suspect"+a)== null){
                  Suspect= "No";
//                  System.out.println("Suspect"+Suspect);
              }
               if(request.getParameter("Cryo"+a)!= null && !request.getParameter("Cryo"+a).equals("") ){
                  Cryo= request.getParameter("Cryo"+a);
//                   System.out.println("Cryo=========="+Cryo);
                  }
               else if(request.getParameter("Cryo"+a)== null){
                  Cryo= "No";
//                  System.out.println("Cryo"+Cryo);
                  }
                if(request.getParameter("Leep"+a)!= null &&  !request.getParameter("Leep"+a).equals("")){
                  Leep= request.getParameter("Leep"+a);
//                   System.out.println("Leep=========="+Leep);
                  }
                else if(request.getParameter("Leep"+a)== null ){
                  Leep= "No";
//                   System.out.println("Leep"+Leep);
                  }
              if(request.getParameter("Others"+a)!= null && !request.getParameter("Others"+a).equals("")){
                  Others= request.getParameter("Others"+a);
//                   System.out.println("Others"+Others);
                  }
              if(request.getParameter("Referral"+a)!= null && !request.getParameter("Referral"+a).equals("")){
                  Referral= request.getParameter("Referral"+a);
//                   System.out.println("Referral"+Referral);
                  }
              if(request.getParameter("testID"+a)!= null && !request.getParameter("testID"+a).equals("")){
                  testID= request.getParameter("testID"+a);
//                   System.out.println("testID"+testID);
                  }
              if(request.getParameter("rowID"+a)!= null && !request.getParameter("rowID"+a).equals("")){
                  rowID= request.getParameter("rowID"+a);
//                   System.out.println("rowID"+rowID);
                  }
           
            
String updateCervix="";
                 if(!CervixDate.equals("") && CervixDate!=null && !testID.equals("") && testID!=null){
               updateCervix = "update cervical_screening set date='"+CervixDate+"',test_id='"+testID+"',cervixDone='"+Cervix+"',results='"+tests+"',"
                       + "cancer_suspicious='"+Suspect+"',cryo='"+Cryo+"',leep='"+Leep+"',others='"+Others+"',referral='"+Referral+"' where screen_id='"+rowID+"'";
                 //System.out.println("updateCervix+++++++"+updateCervix);
                   
                 conn.state.executeUpdate(updateCervix);
}
    }
 
 
            
 
 
                 for(int a=1;a<=3;a++) {
                      String PapCervix1="";
             String CervixDate1="";
             String tests1="";
             String CervixPap="";
             String HSIL1="";
             String papsmear1="";
             String PapCryo1="";
             String PapLeep1="";
             String Others1="";
             String Overt1="";
             String Referral1="";
             String PaptestID="";
             String PaprowID="";
 
              if(request.getParameter("PapCervixDate"+a)!= null && !request.getParameter("PapCervixDate"+a).equals("")){
                  CervixDate1= request.getParameter("PapCervixDate"+a);

              }
             
              if(request.getParameter("cervixPap"+a)!= null && !request.getParameter("cervixPap"+a).equals("")){
                  CervixPap= request.getParameter("cervixPap"+a);
                  }
              
              
              if(request.getParameter("papsmear"+a)!= null && !request.getParameter("papsmear"+a).equals("")){
                  papsmear1= request.getParameter("papsmear"+a);
                 
              }
              
              if(request.getParameter("HSIL"+a)!= null &&  !request.getParameter("HSIL"+a).equals("") ){
                  HSIL1= request.getParameter("HSIL"+a);

                  }
              if(request.getParameter("Overt"+a)!= null && !request.getParameter("Overt"+a).equals("") ){
                  Overt1= request.getParameter("Overt"+a);

                  }
              if(request.getParameter("CryoPap"+a)!= null && !request.getParameter("CryoPap"+a).equals("")){
                  PapCryo1= request.getParameter("CryoPap"+a);

                  }
              if(request.getParameter("LeepPap"+a)!= null && !request.getParameter("LeepPap"+a).equals("")) {
                  PapLeep1=request.getParameter("LeepPap"+a);

                  }
              if(request.getParameter("OthersPap"+a)!= null && !request.getParameter("OthersPap"+a).equals("") ){
                  Others1=request.getParameter("OthersPap"+a);

                  }
              if(request.getParameter("PapReferral"+a)!= null && !request.getParameter("PapReferral"+a).equals("")){
                  Referral1= request.getParameter("PapReferral"+a);
              }
              if(request.getParameter("PaptestID"+a)!= null && !request.getParameter("PaptestID"+a).equals("")){
                  PaptestID= request.getParameter("PaptestID"+a);

                  }
              if(request.getParameter("PaprowID"+a)!= null && !request.getParameter("PaprowID"+a).equals("")){
                  PaprowID= request.getParameter("PaprowID"+a);

                  }
             
             
               String updateCervix1="";
                 
                  if(!CervixDate1.equals("") && CervixDate1!=null && !PaptestID.equals("") && PaptestID!=null && CervixPap!=null && !CervixPap.equals("") && !papsmear1.equals("") && papsmear1!=null 
                      && !HSIL1.equals("") && HSIL1!=null && !Overt1.equals("") && Overt1!=null && !PapCryo1.equals("") &&  PapCryo1!=null && PapLeep1!=null && !PapLeep1.equals("") && Others1!=null && !Others1.equals("") && Referral1!=null && !Referral1.equals("")){
               updateCervix1 = "update cervical_screening set date='"+CervixDate1+"',test_id='"+PaptestID+"',cervixDone='"+CervixPap+"',results='"+papsmear1+"',"
                       + "HSIL='"+HSIL1+"',overtCancer='"+Overt1+"',cryo='"+PapCryo1+"',leep='"+PapLeep1+"',others='"+Others1+"',referral='"+Referral1+"' where screen_id='"+PaprowID+"'";
                 
               System.out.println("updateCervix ------- "+updateCervix1);
                   
                 conn.state.executeUpdate(updateCervix1);
              }  
                 
              
                 
                 
              }
          



//=========================================================Vitamin a details=======================



            //=======save new details=================


            vit_new_no_of_rows = request.getParameter("vit_new_no_of_rows");
            vit_old_no_of_rows = request.getParameter("vit_old_no_of_rows");

            if (vit_new_no_of_rows != null) {

                for (int q = 1; q <= Integer.parseInt(vit_new_no_of_rows); q++) {
                    String vit_dose_id, dw_dosage, vit_nextvisit;

                    vit_dose_id = request.getParameter("vit_dose_id" + q);
                    dw_dosage = request.getParameter("dw_dosage" + q);
                    vit_nextvisit = request.getParameter("vit_nextvisit" + q);

                    if (vit_nextvisit != null && !vit_nextvisit.equals("") && dw_dosage.equals("yes")) {

                        econn.st_6.executeUpdate("Insert into vitamin_a_capsule set motherid='"+motherID+"',  dose_id ='" + vit_dose_id + "' , nextvisit_date='" + vit_nextvisit + "' ,anc_no='" + ANC_No + "' , dose_given='" + dw_dosage + "'");

                    }

                }
            }
            //=============================update old values==========================
            if (vit_old_no_of_rows != null) {
                for (int m = 1; m <= Integer.parseInt(vit_old_no_of_rows); m++) {
                    String vit_dose_id, dw_dosage, vit_nextvisit, vitamin_id;

                    vit_dose_id = request.getParameter("vit_old_dose_id" + p);
                    dw_dosage = request.getParameter("dw_old_dosage" + p);
                    vit_nextvisit = request.getParameter("vit_old_nextvisit" + p);
                    vitamin_id = request.getParameter("vit_id" + p);
                    if (vit_nextvisit != null && !vit_nextvisit.equals("")) {

                        econn.st_6.executeUpdate("Update vitamin_a_capsule set dose_id ='" + vit_dose_id + "' , nextvisit_date='" + vit_nextvisit + "' , dose_given='" + dw_dosage + "' where vitamin_id='" + vitamin_id + "'");


                        //System.out.println("I should have been updated"+vitamin_id);
                    }

                }

            }




//=========================================================end of vitamin a details=============




            //========================================DEWORMING========================================
            String dw_old_no_of_rows = request.getParameter("dw_old_no_of_rows");
            String dw_new_no_of_rows = request.getParameter("dw_new_no_of_rows");


            //======updating==============
            for (int r = 1; r <= Integer.parseInt(dw_old_no_of_rows); r++) {
                String dw_old_age, dw_old_drug, dew_old_dosage, dw_old_nextvisit, deworm_id;

                dw_old_age = request.getParameter("dw_old_age" + r);
                dw_old_drug = request.getParameter("dw_old_drug" + r);
                dew_old_dosage = request.getParameter("dew_old_dosage" + r);
                dw_old_nextvisit = request.getParameter("dw_old_nextvisit" + r);
                deworm_id = request.getParameter("deworm_id" + r);

                if (dew_old_dosage != null && !dw_old_nextvisit.equals("")) {

                    econn.st_6.executeUpdate("Update deworming set age_id ='" + dw_old_age + "' , next_visit='" + dw_old_nextvisit + "' , drug='" + dw_old_drug + "', dosage='" + dew_old_dosage + "' where deworming_id='" + deworm_id + "'");


                    //System.out.println("I should have been updated"+vitamin_id);

                }

            }




            ///========================SAVING A FRESH COPY===========

            for (int s = 1; s <= Integer.parseInt(dw_new_no_of_rows); s++) {
                String dw_old_age, dw_old_drug, dew_old_dosage, dw_old_nextvisit, deworm_id;

                dw_old_age = request.getParameter("dw_age" + s);
                dw_old_drug = request.getParameter("dw_drug" + s);
                dew_old_dosage = request.getParameter("dew_dosage" + s);
                dw_old_nextvisit = request.getParameter("dw_nextvisit" + s);
                //deworm_id=request.getParameter("deworm_id"+p);

                if (dew_old_dosage != null && !dw_old_nextvisit.equals("")) {

                    econn.st_6.executeUpdate("insert into deworming set motherid='"+motherID+"', anc_no='" + ANC_No + "', age_id ='" + dw_old_age + "' , next_visit='" + dw_old_nextvisit + "' , drug='" + dw_old_drug + "', dosage='" + dew_old_dosage + "'");


                    //System.out.println("I should have been updated"+vitamin_id);

                }

            }



            //====================================END OF DEWORMING===================================


            //===================================DNA PCR=============================================

            String dna_old_no_of_rows = request.getParameter("dna_old_no_of_rows");
            String dna_new_no_of_rows = request.getParameter("dna_new_no_of_rows");


            //======updating==============
            for (int t = 1; t <= Integer.parseInt(dna_old_no_of_rows); t++) {
                String test_id2, date_of_sample_collection, dna_results, dw_old_nextvisit, exposure_id;

                test_id2 = request.getParameter("dna_old_test_id" + t);
                date_of_sample_collection = request.getParameter("dna_old_date" + t);
                dna_results = request.getParameter("dna_old_results" + t);

                exposure_id = request.getParameter("dna_exposure_id" + t);

                if (dna_results != null && !date_of_sample_collection.equals("")) {

                    econn.st_6.executeUpdate("Update hiv_exposure set test_id ='" + test_id2 + "' , date_of_sample_collection='" + date_of_sample_collection + "' , results='" + dna_results + "' where exposure_id='" + exposure_id + "'");


                }

            }


            //==========saving===========

            for (int u = 1; u <= Integer.parseInt(dna_new_no_of_rows); u++) {
                String test_id1, date_of_sample_collection, results, exposure_id;

                test_id1 = request.getParameter("dna_test_id" + u);
                date_of_sample_collection = request.getParameter("dna_date" + u);
                results = request.getParameter("dna_results" + u);

                //exposure_id=request.getParameter("dna_exposure_id"+p);

                if (results != null && !date_of_sample_collection.equals("")) {

                     System.out.println("ANC NO ==========================="+ANC_No);
                   // econn.st_6.executeUpdate("insert into hiv_exposure set test_id ='" + test_id1 + "' , anc_no=" + ANC_No + " , date_of_sample_collection='" + date_of_sample_collection + "' , results='" + results + "' ");
                    econn.st_6.executeUpdate("insert into hiv_exposure (motherid,test_id,anc_no,date_of_sample_collection,results)values ('"+motherID+"', '"+test_id1+"','"+ANC_No+"','"+date_of_sample_collection+"','"+results+"') ");


                }
                

            }



            //=================================END OF DNA PCR=================================



            //THIS SERVLET SAVES ALL THE FORM FIELDS IN THE MOTHER BABY BOOK.

            
            //======decide whether to do a fresh entry or an update of mother details
            
            
            String dob="2014-05-09";
            
            conn.rs=conn.state.executeQuery("select DATE_SUB(CURDATE(),INTERVAL "+Age+" YEAR ) AS dob");
            if(conn.rs.next()){
              dob=conn.rs.getString(1);
            
            }
            
           
            
               String update_mother_details="";
               
               String update_maternal_details = "insert into maternal_details set lmp='" + lmp + "' , edd='" + edd + "',height='" + height + "',institution='" + institution + "', ancno='" + ANC_No + "', motherid='"+motherID+"' , uniqueid='"+uniqueid().trim()+"'";
              // String update_maternal_details = "insert into maternal_details set lmp='" + lmp + "' , edd='" + edd + "',height='" + height + "',institution='" + institution + "', ancno='" + ANC_No + "', motherid='"+motherID+"' ";

               
            if(request.getParameter("motherID")!=null&&!request.getParameter("motherID").equals("")){
            
     update_mother_details = "update mother_details set DOB='"+dob+"', delivery_date='" + edd + "' ,FNAME ='" + FName + "', SName ='" + SName + "' , LName='" + LName + "' ,Age ='" + Age + "' , Gravida='" + Gravida + "' ,parity ='" + parity + "', maritalStatus='" + maritalStatus + "' , education ='" + education + "' , PhoneNo='" + PhoneNo + "' ,occupation='" + occupation + "' ,nok='" + nok + "' ,relationship='" + nok_relationship + "' , NOKPhoneNo='" + NOKPhoneNo + "' ,anc_no='"+ANC_No+"' WHERE motherID='" + motherID + "'";
econn.st_1.executeUpdate(update_mother_details);

//update other register details
if(!ANC_No.equals("")){
String atohupd="update atoh set ancno='"+ANC_No+"'  WHERE motherID='" + motherID + "'";
String itopupd="update itop set ancno='"+ANC_No+"'  WHERE motherID='" + motherID + "'";
String qtowupd="update qtow set ancno='"+ANC_No+"'  WHERE motherID='" + motherID + "'";
String xtoadupd="update xtoad set ancno='"+ANC_No+"'  WHERE motherID='" + motherID + "'";
String aetoakupd="update aetoak set ancno='"+ANC_No+"'  WHERE motherID='" + motherID + "'";
String altoanupd="update altoan set ancno='"+ANC_No+"'  WHERE motherID='" + motherID + "'";
econn.st_1.executeUpdate(atohupd);
econn.st_1.executeUpdate(itopupd);
econn.st_1.executeUpdate(qtowupd);
econn.st_1.executeUpdate(xtoadupd);
econn.st_1.executeUpdate(aetoakupd);
econn.st_1.executeUpdate(altoanupd);
}

                //update the lower table
                econn.st_1.executeUpdate(update_maternal_details);
            
            }
            
            else{
            
            
                
 conn.rs5=conn.state5.executeQuery("select * from mother_details where (anc_no='"+ANC_No+"' && facilityname='"+facilityname+"') or motherID='"+motherID+"'");
            if(!conn.rs5.next()){    
           update_mother_details = "insert into mother_details set motherID='"+motherID+"' , DOB='"+dob+"', MotherProfileID='"+motherID+"',facilityname='"+facilityname+"', delivery_date='" + edd + "' ,FNAME ='" + FName + "', SName ='" + SName + "' , LName='" + LName + "' ,Age ='" + Age + "' , Gravida='" + Gravida + "' ,parity ='" + parity + "', maritalStatus='" + maritalStatus + "' , education ='" + education + "' , PhoneNo='" + PhoneNo + "' ,occupation='" + occupation + "' ,nok='" + nok + "' ,relationship='" + nok_relationship + "' , NOKPhoneNo='" + NOKPhoneNo + "', anc_no='" + ANC_No + "'";
            
            econn.st_1.executeUpdate(update_mother_details);


                //update the lower table
                econn.st_1.executeUpdate(update_maternal_details);
            
                               }
            else{
            
            msg="<font color=\"red\">mother details not saved. ANC "+ANC_No+" has already been asigned to someone else in Facility "+facilityname+"</font>";
            
            }
          
            
            }
            
            
             
           System.out.println(update_mother_details);


            //execute update of maternal profile....

            if (FName != null && ANC_No != null) {
                //update mother details table
                
                
                
                 if (session.getAttribute("userid") != null) {
         Date dat= new Date();
         String inserter = "insert into audit set host_comp='" + computername + "' , action='Updated details for a mother whose anc no is "+ANC_No+" using the Mother_baby book',time='" + dat + "',actor_id='" + session.getAttribute("userid") + "'";

                    econn.st3.executeUpdate(inserter);
                
            }

            }












        } catch (SQLException ex) {
            Logger.getLogger(saveMotherBaby.class.getName()).log(Level.SEVERE, null, ex);
        }





        session.setAttribute("mother_edited", msg);

        response.sendRedirect("viewallmothers");




    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    
    
       
    //====================random id functions================================ 

 public String uniqueid() {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milsec=cal.get(Calendar.MILLISECOND);
        
        
        return year+""+month+""+date+hour+min+sec+milsec+generateRandomNumber(800, 9000);
    }

 
 
   public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
 
//==========================================================================
    
    
    
}
