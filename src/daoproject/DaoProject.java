package daoproject;

import daoproject.database.DBFactory;
import daoproject.entity.Colecao;
import daoproject.entity.Disco;
import daoproject.entity.Faixa;
import daoproject.interfaceDAO.ColecaoDAO;
import daoproject.interfaceDAO.DiscoDAO;
import daoproject.interfaceDAO.FaixaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DaoProject {

    public static void main(String[] args) {
        try {
            // Escolha o tipo de banco de dados
            String dbType = "H2"; // Ou "MYSQL"

            // Obter fábrica de conexão
            DBFactory dbFactory = DBFactory.getDBFactory(dbType);
            Connection connection = dbFactory.getConnection();

            // Se estiver usando H2, inicialize o banco de dados
            if (dbType.equalsIgnoreCase("H2")) {
                initializeDatabase(connection);
            }

            // Criar instâncias DAO
            DiscoDAO discoDAO = new DiscoDAOImpl(connection);
            FaixaDAO faixaDAO = new FaixaDAOImpl(connection);
            ColecaoDAO colecaoDAO = new ColecaoDAOImpl(connection);

            // Exemplo: Inserir uma coleção
            Colecao colecao = new Colecao();
            colecao.setNome("Minha Coleção Favorita");
            int colecaoId = colecaoDAO.inserir(colecao);
            System.out.println("Coleção inserida com ID: " + colecaoId);

            // Exemplo: Inserir um disco na coleção
            Disco disco = new Disco();
            disco.setTitulo("Thriller");
            disco.setArtista("Michael Jackson");
            disco.setColecaoId(colecaoId);
            int discoId = discoDAO.inserir(disco);
            System.out.println("Disco inserido com ID: " + discoId);

            // Exemplo: Inserir faixas no disco
            Faixa faixa1 = new Faixa();
            faixa1.setTitulo("Billie Jean");
            faixa1.setDuracao(293);
            faixa1.setDiscoId(discoId);
            faixaDAO.inserir(faixa1);

            Faixa faixa2 = new Faixa();
            faixa2.setTitulo("Thriller");
            faixa2.setDuracao(357);
            faixa2.setDiscoId(discoId);
            faixaDAO.inserir(faixa2);

            // Listar todos os discos da coleção
            System.out.println("\nListando todos os discos na coleção:");
            List<Disco> discos = discoDAO.listarPorColecaoId(colecaoId);
            for (Disco d : discos) {
                System.out.println("ID: " + d.getId() + ", Título: " + d.getTitulo() + ", Artista: " + d.getArtista());
            }

            // Listar todas as faixas do disco
            System.out.println("\nListando todas as faixas do disco:");
            List<Faixa> faixas = faixaDAO.listarPorDiscoId(discoId);
            for (Faixa f : faixas) {
                System.out.println("ID: " + f.getId() + ", Título: " + f.getTitulo() + ", Duração: " + f.getDuracao() + " segundos");
            }

            // Atualizar o nome da coleção
            colecao.setNome("Coleção de Clássicos");
            colecaoDAO.atualizar(colecao);
            System.out.println("\nColeção atualizada: " + colecao.getNome());

            // Excluir uma faixa
            if (!faixas.isEmpty()) {
                Faixa faixaParaDeletar = faixas.get(0);
                faixaDAO.deletar(faixaParaDeletar.getId());
                System.out.println("\nFaixa deletada: " + faixaParaDeletar.getTitulo());
            }

            // Excluir o disco
            discoDAO.deletar(discoId);
            System.out.println("Disco deletado com ID: " + discoId);

            // Excluir a coleção
            colecaoDAO.deletar(colecaoId);
            System.out.println("Coleção deletada com ID: " + colecaoId);

            // Fechar conexão
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initializeDatabase(Connection connection) {
        String createColecoesTable = "CREATE TABLE IF NOT EXISTS colecoes (id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(255))";
        String createDiscosTable = "CREATE TABLE IF NOT EXISTS discos (id INT AUTO_INCREMENT PRIMARY KEY, titulo VARCHAR(255), artista VARCHAR(255), colecao_id INT, FOREIGN KEY (colecao_id) REFERENCES colecoes(id))";
        String createFaixasTable = "CREATE TABLE IF NOT EXISTS faixas (id INT AUTO_INCREMENT PRIMARY KEY, titulo VARCHAR(255), duracao INT, disco_id INT, FOREIGN KEY (disco_id) REFERENCES discos(id))";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createColecoesTable);
            stmt.execute(createDiscosTable);
            stmt.execute(createFaixasTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
