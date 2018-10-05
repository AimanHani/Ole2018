<html>
    <head>
        <title>Specials</title>
    </head>
    <body>
        <h2>Update Specials</h2>
        <form action="SpecialsServlet" method="post">
            <p><input type="checkbox" name="specials" value="s1"/>S1</p>
            <p><input type="checkbox" name="specials" value="s2"/>S2</p>
            <p><input type="checkbox" name="specials" value="s3"/>S3</p>
            <p><input type="checkbox" name="specials" value="s4"/>S4</p>
            <p><input type="checkbox" name="specials" value="s5"/>S5</p>
            <p><input type="checkbox" name="specials" value="s6"/>S6</p>
            <p><input type="submit" value="Update Specials"/>
        </form>
        <%
            if (request.getAttribute("updated") != null) {
                out.println(request.getAttribute("updated") + " specials");
            }
        %>
        <hr>
        show the specials table <br>
        s1 y <br>
        s2 n <br>
        s3 n <br>
        s4 n <br>

    </body>
</html>
