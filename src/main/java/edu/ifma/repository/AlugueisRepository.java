package edu.ifma.repository;

import edu.ifma.models.Alugueis;
import edu.ifma.repository.Dao.DaoGeneric;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AlugueisRepository extends DaoGeneric<Alugueis> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LAB4_LBD");

    public AlugueisRepository(){
        super(Alugueis.class);
    }

    // Listar todos os aluguéis de um determinado inquilino (via Locacao associada)
    public List<Alugueis> findByInquilinoId(int inquilinoId) {
        EntityManager em = emf.createEntityManager();
        List<Alugueis> lista = em.createQuery(
                        "SELECT a FROM Alugueis a WHERE a.locacao.inquilino.id = :inquilinoId", Alugueis.class)
                .setParameter("inquilinoId", inquilinoId)
                .getResultList();
        em.close();
        return lista;
    }

    // Listar todos os aluguéis de um inquilino pelo nome (via Locacao associada)
    public List<Alugueis> findByInquilinoNome(String nome) {
        EntityManager em = emf.createEntityManager();
        List<Alugueis> lista = em.createQuery(
                        "SELECT a FROM Alugueis a WHERE a.locacao.inquilino.nome LIKE :nome", Alugueis.class)
                .setParameter("nome", "%" + nome + "%") // Busca parcial pelo nome
                .getResultList();
        em.close();
        return lista;
    }

    // Recuperar todos os aluguéis dentro de um limite de preço do aluguel sugerido (via imóvel da locação)
    public List<Alugueis> findByValorAluguelMenorQue(double valorMaximo) {
        EntityManager em = emf.createEntityManager();
        List<Alugueis> lista = em.createQuery(
                        "SELECT a FROM Alugueis a WHERE a.locacao.imovel.valorAluguelSugerido <= :valorMaximo",
                        Alugueis.class)
                .setParameter("valorMaximo", valorMaximo)
                .getResultList();
        em.close();
        return lista;
    }

    // Recuperar uma lista de todos os aluguéis que foram pagos com atraso
    public List<Alugueis> findAlugueisComAtraso() {
        EntityManager em = emf.createEntityManager();
        List<Alugueis> lista = em.createQuery(
                        "SELECT a FROM Alugueis a WHERE a.dataPagamento > a.dataVencimento", Alugueis.class)
                .getResultList();
        em.close();
        return lista;
    }

    // Buscar aluguel por ID da locação
    public Alugueis findByLocacaoId(int locacaoId) {
        EntityManager em = emf.createEntityManager();
        Alugueis aluguel = null;

        try {
            aluguel = em.createQuery(
                            "SELECT a FROM Alugueis a WHERE a.locacao.id = :locacaoId", Alugueis.class)
                    .setParameter("locacaoId", locacaoId)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Nenhum aluguel encontrado para a locação ID " + locacaoId);
        } finally {
            em.close();
        }

        return aluguel;
    }

}

