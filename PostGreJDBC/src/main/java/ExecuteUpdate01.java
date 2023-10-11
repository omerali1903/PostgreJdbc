import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = connection.createStatement();

        //executeUpdate:DML icin kullanilir:Datalari INSERT,UPDATE,DELETE
        //return:sorgudan etkilenen kayit sayisini dondurur.

        //ORNEK1: developers tablosunda maasi ortalama maastan az olanlarin maasini 5111 olarak guncelleyiniz

      //  String query = "UPDATE developers SET salary=5111 WHERE salary<(SELECT AVG(salary) FROM developers)";
        //int updated = st.executeUpdate(query);
        //System.out.println("guncellenen kayit sayisi : " + updated);

        ResultSet rs = st.executeQuery("SELECT name,salary FROM developers");

        while (rs.next()) {
            System.out.println(rs.getString("name") + "---" + rs.getDouble("salary"));
        }

        //ORNEK2: developers tablosunda yeni bir developer ekleyiniz.
        String query2 = "INSERT INTO developers(name,salary,prog_lang)VALUES('Aynur',7000,'Python')";
        int inserted = st.executeUpdate(query2);
        System.out.println("Eklenen kayit sayisi = " + inserted);


    }


}
