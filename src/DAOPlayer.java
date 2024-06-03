
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOPlayer {
    public static Connection _c;
    public static String _MODO;

    public DAOPlayer(String Modo) {
        _MODO = Modo;
        DAOMySql dms = new DAOMySql(Modo);
        if (dms.c != null) {
            _c = dms.c;
        } else {
            _c = null;
        }
    }

    public Player getPlayer(Integer id) {
        Player player = null;
        try {
            Statement stmt = _c.createStatement();
            String ssql = "SELECT * FROM usuario WHERE id_usuario = " +id;
            ResultSet rs = stmt.executeQuery(ssql);
            if (rs.next()) {
                player = new Player(rs.getString("nick"), rs.getString("pwd"), rs.getString("grupo"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return player;
    }
}

