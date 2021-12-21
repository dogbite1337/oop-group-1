package com.company;

import express.Express;
import java.sql.*;

public class Application {
    Connection con;

    public Application() {
        Express app = new Express();

        app.listen(4000);

        con = MySQL.INSTANCE.getConnection();

        try{
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
