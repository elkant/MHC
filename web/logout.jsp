<%-- 
    Document   : logout
    Created on : Aug 6, 2013, 1:04:54 PM
    Author     : SIXTYFOURBIT
--%>

<%@page import="java.net.InetAddress"%>
<%@page import="java.util.Calendar"%>
<%@page import="sender.dbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>log out</title>
    </head>
    <body>
        <%
            String current_time, status = "", who;
            dbConn conn = new dbConn();


//____________________COMPUTER NAME____________________________________
            String computername = InetAddress.getLocalHost().getHostName();
//System.out.println("Computer name "+computername);

            if (session.getAttribute("userid") != null) {
            }


            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int date = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            String yr, mnth, dater, hr, mn, sc, action = "";
            yr = Integer.toString(year);
            mnth = Integer.toString(month);
            dater = Integer.toString(date);
            hr = Integer.toString(hour);
            mn = Integer.toString(min);
            sc = Integer.toString(sec);
            current_time = sc + "-" + mn + "-" + "-" + hr + "-" + dater + "-" + mnth + "-" + yr;

            // if(session.getAttribute("userid").toString()!=null){
// status=session.getAttribute("userid").toString();
// }

            if (status.equals("1")) {
                who = "Administrator";
                System.out.println("Admin");
            } else {
                who = "Clerk";
            }

            if (session.getAttribute("userid") != null) {

                String inserter = "insert into audit set host_comp='" + computername + "' , action='Logged out ',time='" + current_time + "',actor_id='" + session.getAttribute("userid") + "'";

                conn.st3.executeUpdate(inserter);
            }
            session.invalidate();
            response.sendRedirect("index.jsp");
        %>

    </body>
</html>
