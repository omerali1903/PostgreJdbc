import java.sql.*;

/*
PreparedStatement: Statement extend eder(Statementin gelismis halidir)

PreparedStatement, birden fazla kez kullanilabilen önceden derlenmis parametreli sorgular
olusturmamizi saglar.
 */
public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = connection.createStatement();

        //ÖRNEK1:(Prepared Statement kullanarak) bolumler tablosunda Matematik bölümünün taban_puani'nı 475 olarak güncelleyiniz.

        //String query="UPDATE bolumler SET taban_puani=475 WHERE bolum ILIKE 'matematik'";
        // st.executeQuery(query);

        //Sorguyu parametreli olarak String tipinde yazalim

        String query = "UPDATE bolumler SET taban_puani=? WHERE bolum ILIKE ?";

        //String tipindeki sorgudan parametreli sorgu olustur
        PreparedStatement prst = connection.prepareStatement(query);

        //parametrelerin degerlerini set ediyoruz.
        prst.setInt(1, 475);
        prst.setString(2, "matematik");

        //sorguyu calistiralim.
        int updated = prst.executeUpdate();
        System.out.println("updated = " + updated);

        System.out.println("-------------ORNEK2-----------");

        //ÖRNEK2:Prepared Statement kullanarak bolumler tablosunda Edebiyat bölümünün taban_puanı'nı 455 olarak güncelleyiniz.

        prst.setInt(1, 455);
        prst.setString(2, "edebiyat");

        int updated2 = prst.executeUpdate();
        System.out.println("updated2 = " + updated2);

        //ÖRNEK3:Prepared Statement kullanarak ogrenciler tablosundan Matematik bölümünde okuyanları siliniz.

        String query2 = "DELETE FROM ogrenciler WHERE bolum ILIKE ?";

        PreparedStatement prst2 = connection.prepareStatement(query2);

        prst2.setString(1, "matematik");

        int deleted = prst.executeUpdate();
        System.out.println("deleted = " + deleted);

        //ÖRNEK4:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475, kampus='Merkez') bölümünü ekleyiniz.

        String query4="INSERT INTO bolumler VALUES(?,?,?,?)";//(id,bolum,taban_puni,kampüs)
        PreparedStatement prst3=connection.prepareStatement(query4);

        prst3.setString(2,"Yazılım Mühendisliği");
        prst3.setInt(1,5006);
        prst3.setString(4,"Merkez");
        prst3.setInt(3,475);


        int inserted=prst3.executeUpdate();
        System.out.println("inserted = " + inserted);

        prst3.close();
        prst2.close();
        prst.close();
        st.close();
        connection.close();


    }


}
