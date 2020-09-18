
package modeloConection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ConexaoBD {
    /*Prepara e realizar pesquisa*/
    public Statement stm;
    
    /*Guarda o que foi pesquisado*/
    public ResultSet rs;
    
    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/projetoclinica";
    private String usuario = "postgres";
    private String senha = "Filhote-123";
    
    /*Responsavel pela conexão ao BD*/
    public Connection con;
    
    public void conexao(){
        
        try {
            /*Seta a propiedade do driver da conexão*/
            System.setProperty("jdbc.Drives", driver);
            con=DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conexão com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o Banco de Dados:\n"+ex.getMessage());
        }
    }
    
    public void executaSql(String sql){
        try {
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ExecutaSql:\n"+ex.getMessage());
        }
    
    }
    
    public void desconecta(){
        try {
            con.close();
            //JOptionPane.showMessageDialog(null, "Desconectado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com Banco de Dados:\n"+ex.getMessage());
        }
    }
}
