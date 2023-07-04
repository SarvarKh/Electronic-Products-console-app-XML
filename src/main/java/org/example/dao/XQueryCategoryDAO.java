package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.sf.saxon.s9api.*;
import net.sf.saxon.value.AtomicValue;

public class XQueryCategoryDAO {
    private static String url = "jdbc:postgresql://localhost:5432/myXML";
    private static String dbUserName = "postgres";
    private static String dbPassword = null;

    public static void getCategories() throws SQLException, SaxonApiException, ClassNotFoundException {
        // Connect to the PostgreSQL database
        Class.forName("org.postgresql.Driver");
        System.out.println("Postgres JDBC Driver Registered!");
        // get hold of the DriverManager
        Connection conn = DriverManager.getConnection(url, dbUserName, dbPassword);

        // Create an XQuery processor using Saxon HE
        Processor processor = new Processor(false);
        XQueryCompiler compiler = processor.newXQueryCompiler();

        // Execute an XQuery expression on the database
        String query = "declare variable $conn external; " +
                "for $i in db:query($conn, '/category/products/product/name/text()') return $i";
        XQueryExecutable exec = compiler.compile(query);
        XQueryEvaluator eval = exec.load();
        eval.setExternalVariable(new QName("conn"), new XdmAtomicValue((AtomicValue) conn));
        XdmValue result = eval.evaluate();

        // Print the result of the XQuery expression
        System.out.println(result.toString());

        // Close the database connection
        conn.close();
    }
}
