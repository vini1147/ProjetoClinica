
package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelBeans.BeansAgendamento;
import modeloConection.ConexaoBD;


public class DaoAgendamento {
    BeansAgendamento agenda = new BeansAgendamento();
    ConexaoBD conex = new ConexaoBD();
    ConexaoBD conexPaciente = new ConexaoBD();
    ConexaoBD conexMedico = new ConexaoBD();
    int codMed;
    int codPac;
    
    public void Salvar(BeansAgendamento agenda){
        BuscaMedico(agenda.getNomeMed());
        BuscaPaciente(agenda.getNomePac());
        conex.conexao();
        try {
            PreparedStatement pst= conex.con.prepareStatement("insert into agenda (agenda_codpac, agenda_codmedico, agenda_motivo, agenda_turno, agenda_data, agenda_status)values(?,?,?,?,?,?)");
            pst.setInt(1, codPac);
            pst.setInt(2, codMed);
            pst.setString(3,agenda.getMotivo());
            pst.setString(4, agenda.getTurno());
            pst.setDate(5, new java.sql.Date(agenda.getData().getTime()));
            pst.setString(6, agenda.getStatus());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Agendamento concluído!!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar a consulta!"+ex);
        }
        conex.desconecta();
    }
    
    public void BuscaMedico(String nomeMedico){
        conexMedico.conexao();
        conexMedico.executaSql("select *from medicos where nome_medico='"+nomeMedico+"'");
        try {
            conexMedico.rs.first();
            codMed = conexMedico.rs.getInt("cod_medico");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Médico não encontrado!");
        }
        
        conexMedico.desconecta();
    }
    
    public void BuscaPaciente(String nomePaciente){
        conexPaciente.conexao();
        conexPaciente.executaSql("select *from pacientes where paci_nome='"+nomePaciente+"'");
        try {
            conexPaciente.rs.first();
            codPac = conexPaciente.rs.getInt("paci_codigo");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Paciente não encontrado!");
        }
        
        conexPaciente.desconecta();
    
    }
    
}
