<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/bank.css">
        
    </head>
    <div style="text-align: center">
        <a href="index.html"> <img style="border: 0" src="logo.png"> </a><br>
        <h1>&#x1F4B0; JavaBank</h1><hr/><br></div>
    <div style="float: center">
        <div style="text-align: center"><h1><span style="color: #00000">Logowanie</span> </h1>
            <h3>Zaloguj się:</h3></div>
        <div id="panel">
            <form method="post" action="Login">
                <label>Login:</label> <input type="text" id="user" name="user">
                <label>Hasło:</label><input type="password" id="pass" name="pass"><br>
                <div id="lower"> <br>
                    <input type="checkbox"><div style="float: left;" <label class="check" for="checkbox">Zapamiętaj mnie!</label></div><br><br>
                    <input type="submit" style="font-size: large" value="Zaloguj"></p></div>
        </div></div>
    </form>

    <br><div style="text-align: center">
    <%
        String invalid = request.getParameter("invalid");
        if ("true".equals(invalid)) {
            out.println("<h4 style='color: red'>Nieprawidłowe dane logowania</h4>");
        }
    %>
    Jeśli nie masz konta: 
   <button type="button"><a href="/Bank/create.jsp" style="font-size: large">Utwórz konto</a></button></div>

</div>           



</body>
</html>