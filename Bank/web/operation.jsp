<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/bank.css">
    <title>Twoje konto</title>
    </head>
    <body>
        
        <div style="text-align: center">
            <a href="index.html"> <img style="border: 0" src="logo.png"> </a><br>
            <h1>&#x1F4B0; JavaBank</h1><hr/><br></div>
 <div style="text-align: center">
        <h3>Twoje konto:</h3>
        
            <%@ page import="jp.*" %>
            <% Account account = (Account) session.getAttribute("account");%>


<div style="text-align:left">
    <table align="center">
   <tr>
       <td><div id="panel3"><label2><span style="color: white"><b>Zalogowany jako:</b></span></label2></td></div> <td><div id="panel4"><label><span style="color: #ff5722"><%= account.getFullName()%> </span><a  href="/Bank/Login?logout=true"> <button type="button"style="font-size: large">wyloguj</button></label></td> </div></tr>
   <tr>
       <td><div id="panel3"><label2><span style="color: white"><b>Twój nr konta:</b></span></label2></td></div> <td><div id="panel4"><label2><span style="color: white"><%= account.getAccountNumber()%></span></label2></td> </div>
   </tr>
     <tr>
         <td> <div id="panel3"><label2><span style="color: white"><b>Stan konta:</b></span></label2></td></div> <td><div id="panel4"><label2><span style="color: white"><%= account.getDeposit()%></label2></span></td> </div>
   </tr>
</table></div>
    <hr/>

    <h3>Operacje:</h3></div>
    
        <div id="panel2">
            <form action="Operation">
                <label><input type="radio" checked="true"  name="operation" value="deposit"/>Wpłata</label></div>
        <div id="panel2"> <label><input type="radio" name="operation"  value="withdraw"/>Wypłata</label></div>
        <div id="panel2">  <br> <label2> <input type="radio"  name="operation"   value="transfer"/>Przelew do   


                </script>
                <select  name="to">
                    <%BankRemote bank = (BankRemote) session.getAttribute("remote");

                        List<Account> accounts = bank.getAllAccounts();
                        for (Account other : accounts) {
                            if (other.getId() == account.getId()) {
                                continue;
                            }
                            String option = String.format("<option value='%d'>%s (%s)</option>", other.getId(), other.getAccountNumber(), other.getFullName());
                            out.println(option);
                        }
                    %>             </label2>
                </select> <br/> </div>
        <div id="panel2"><label2>
                Na kwotę: <input type="text" id="dep" name="amount" value="0"/><br/><br/></label2> </div><br>
       <div style="text-align:center"> <input type="submit" style="font-size: large" value="Zatwierdź"> 
        </form>

        <%
            String error = (String) session.getAttribute("error");
            if (error != null) {
                out.println("<h4 style='color: red'>Nastąpił błąd podczas operacji: " + error + "</h4>");
            }
            session.removeAttribute("error");
        %>  <br><br>  </div>
        </body>
        </html>