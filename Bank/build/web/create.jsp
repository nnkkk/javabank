<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/bank.css">
        
    </head>
    <body>
    <div style="text-align: center">
        <a href="index.html"> <img style="border: 0" src="logo.png"> </a><br>
        <h1>&#x1F4B0; JavaBank</h1><hr/><br></div>
    <div style="float:center">
            <div style="text-align: center"><h1><span style="color: #000000">Utwórz konto</span> </h1>
                <p><b>Podaj dane nowego konta:</b></p></div>
            <div id="panel">
                <form action="OpenAccount"> 

                    <label>Imię:</label>     <input type="text" id="firstName" name="firstName"/>
                    <label>Nazwisko:</label>    <input type="text" id="lastName" name="lastName"/>
                  <label>Login:</label>      <input type="text" id="user" name="login">
                  <label>Hasło:</label>       <input type="password" id="pass" name="password">
                    <div id="lower"> <br>
                        <input type="checkbox"><div style="float: left;" <label class="check" for="checkbox">Zapamiętaj mnie!</label></div><br><br>
                        <input type="submit" style="font-size: large" value="Utwórz"></p></div>
            </div>
        </form>
        <br>
        </form><div style="text-align: center">
            Jeśli masz konto:
        <button type="button"> <a href="/Bank/login.jsp" style="font-size: large">Zaloguj</a></button><br><br></div>
</body>
</html>


