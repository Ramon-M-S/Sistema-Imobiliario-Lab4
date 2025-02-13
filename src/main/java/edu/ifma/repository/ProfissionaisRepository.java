package edu.ifma.repository;

import edu.ifma.models.Profissionais;
import edu.ifma.repository.Dao.DaoGeneric;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;

public class ProfissionaisRepository extends DaoGeneric<Profissionais> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LAB4_LBD");

    public ProfissionaisRepository() {
        super(Profissionais.class);
    }

    // Método para buscar profissionais por nome
    public List<Profissionais> findByNome(String nome) {
        EntityManager em = emf.createEntityManager();
        List<Profissionais> profissionais = em.createQuery(
                        "SELECT p FROM Profissionais p WHERE p.nome LIKE :nome", Profissionais.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
        em.close();
        return profissionais;
    }

    // Método para buscar profissionais por profissão
    public List<Profissionais> findByProfissao(String profissao) {
        EntityManager em = emf.createEntityManager();
        List<Profissionais> profissionais = em.createQuery(
                        "SELECT p FROM Profissionais p WHERE p.profissao = :profissao", Profissionais.class)
                .setParameter("profissao", profissao)
                .getResultList();
        em.close();
        return profissionais;
    }

    // Método para buscar profissionais com valor hora menor ou igual a um valor máximo
    public List<Profissionais> findByValorHoraMenorQue(BigDecimal valorMaximo) {
        EntityManager em = emf.createEntityManager();
        List<Profissionais> profissionais = em.createQuery(
                        "SELECT p FROM Profissionais p WHERE p.valor_hora <= :valorMaximo", Profissionais.class)
                .setParameter("valorMaximo", valorMaximo)
                .getResultList();
        em.close();
        return profissionais;
    }

    // Método para buscar profissionais por telefone
    public Profissionais findByTelefone(String telefone) {
        EntityManager em = emf.createEntityManager();
        Profissionais profissional = null;
        try {
            profissional = em.createQuery(
                            "SELECT p FROM Profissionais p WHERE p.telefone = :telefone", Profissionais.class)
                    .setParameter("telefone", telefone)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Nenhum profissional encontrado com o telefone " + telefone);
        } finally {
            em.close();
        }
        return profissional;
    }

    // Método para buscar profissionais por telefone secundário
    public Profissionais findByTelefone2(String telefone2) {
        EntityManager em = emf.createEntityManager();
        Profissionais profissional = null;
        try {
            profissional = em.createQuery(
                            "SELECT p FROM Profissionais p WHERE p.telefone2 = :telefone2", Profissionais.class)
                    .setParameter("telefone2", telefone2)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Nenhum profissional encontrado com o telefone secundário " + telefone2);
        } finally {
            em.close();
        }
        return profissional;
    }
}