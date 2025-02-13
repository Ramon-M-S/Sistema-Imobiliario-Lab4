package edu.ifma.repository;


import edu.ifma.models.ServicoImovel;
import edu.ifma.repository.Dao.DaoGeneric;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ServicoImovelRepository extends DaoGeneric<ServicoImovel> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LAB4_LBD");

    public ServicoImovelRepository() {
        super(ServicoImovel.class);
    }

    // Buscar todos os serviços associados a um imóvel APENAS se houver uma locação ativa
    public List<ServicoImovel> findByImovelComLocacaoAtiva(int imovelId) {
        EntityManager em = emf.createEntityManager();

        try {
            // Verificar se o imóvel tem uma locação ativa
            Long locacaoAtivaCount = em.createQuery(
                            "SELECT COUNT(l) FROM Locacao l WHERE l.imovel.id = :imovelId AND l.ativo = true",
                            Long.class)
                    .setParameter("imovelId", imovelId)
                    .getSingleResult();

            // Se não houver locação ativa, retornar uma lista vazia
            if (locacaoAtivaCount == 0) {
                return List.of();
            }

            // Buscar todos os serviços executados no imóvel
            List<ServicoImovel> servicos = em.createQuery(
                            "SELECT s FROM ServicoImovel s WHERE s.imovel.id = :imovelId",
                            ServicoImovel.class)
                    .setParameter("imovelId", imovelId)
                    .getResultList();

            return servicos;
        } catch (Exception e) {
            return List.of(); // Retorna lista vazia em caso de erro
        } finally {
            em.close();
        }
    }

    // Buscar todos os serviços associados a um profissional
    public List<ServicoImovel> findByProfissionalId(int profissionalId) {
        EntityManager em = emf.createEntityManager();
        List<ServicoImovel> servicos = em.createQuery(
                        "SELECT s FROM ServicoImovel s WHERE s.profissionais.id = :profissionalId", ServicoImovel.class)
                .setParameter("profissionalId", profissionalId)
                .getResultList();
        em.close();
        return servicos;
    }

    // Buscar todos os serviços realizados em uma determinada data
    public List<ServicoImovel> findByDataServico(Date dataServico) {
        EntityManager em = emf.createEntityManager();
        List<ServicoImovel> servicos = em.createQuery(
                        "SELECT s FROM ServicoImovel s WHERE s.dataServico = :dataServico", ServicoImovel.class)
                .setParameter("dataServico", dataServico)
                .getResultList();
        em.close();
        return servicos;
    }

    // Buscar todos os serviços com valor total maior ou igual a um valor mínimo
    public List<ServicoImovel> findByValorTotalMaiorQue(BigDecimal valorMinimo) {
        EntityManager em = emf.createEntityManager();
        List<ServicoImovel> servicos = em.createQuery(
                        "SELECT s FROM ServicoImovel s WHERE s.valorTotal >= :valorMinimo", ServicoImovel.class)
                .setParameter("valorMinimo", valorMinimo)
                .getResultList();
        em.close();
        return servicos;
    }

    // Buscar todos os serviços associados a um imóvel
    public List<ServicoImovel> findByImovelId(int imovelId) {
        EntityManager em = emf.createEntityManager();
        List<ServicoImovel> servicos = em.createQuery(
                        "SELECT s FROM ServicoImovel s WHERE s.imovel.id = :imovelId", ServicoImovel.class)
                .setParameter("imovelId", imovelId)
                .getResultList();
        em.close();
        return servicos;
    }
}