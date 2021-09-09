package PlayerAreMobs.main;
import java.sql.*;
import java.util.Objects;

public class Sqlite {
    public static Connection conn;
    public static Statement statmt;
    public void Conn() throws ClassNotFoundException, SQLException
    {
        conn = DriverManager.getConnection("jdbc:sqlite:./PlayerAreMobs.db");
        statmt = conn.createStatement();
    }
    public void CreateDB() throws ClassNotFoundException, SQLException
    {
        this.Conn();
        statmt.execute("CREATE TABLE if not exists 'players' ('nick' text, 'mob' text);");
        this.CloseDB();


    }

    public void NewPlayer(String nick, String mob) throws SQLException, ClassNotFoundException {
        this.Conn();
        try (PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO players ('nick', 'mob') VALUES(?, ?)")) {
            statement.setObject(1, nick);
            statement.setObject(2, mob);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.CloseDB();

    }
    public void UpdatePlayer(String nick, String mob) throws SQLException, ClassNotFoundException {
        this.Conn();
        try (
             PreparedStatement statement = conn.prepareStatement("UPDATE players SET mob = ? WHERE nick = ?")) {

            // set the corresponding param
            statement.setString(1, mob);
            statement.setString(2, nick);
            // update
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("fuck......");
        }


        this.CloseDB();

    }
    public String GetPlayerMob(String nick) throws ClassNotFoundException, SQLException
    {
        this.Conn();
        ResultSet resultSet = statmt.executeQuery("SELECT mob,nick FROM players WHERE nick = \""+nick+"\"");
        String mob = null;
        try{
            mob = resultSet.getString("mob");
        } catch (SQLException ignored){

        }

        this.CloseDB();
        return mob;




    }

    // --------Закрытие--------
    public void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
    }

}
