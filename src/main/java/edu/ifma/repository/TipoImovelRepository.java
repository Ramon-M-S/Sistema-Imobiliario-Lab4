package edu.ifma.repository;

import edu.ifma.models.TipoImovel;
import edu.ifma.repository.Dao.DaoGeneric;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TipoImovelRepository extends DaoGeneric<TipoImovel> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab04");

    public TipoImovelRepository() {
        super(TipoImovel.class);
    }

    // Metodo para buscar tipos de imóvel por descrição
    public List<TipoImovel> findByDescricao(String descricao) {
        EntityManager em = emf.createEntityManager();
        List<TipoImovel> tipos = em.createQuery(
                        "SELECT t FROM TipoImovel t WHERE t.descricao LIKE :descricao", TipoImovel.class)
                .setParameter("descricao", "%" + descricao + "%")
                .getResultList();
        em.close();
        return tipos;
    }

    // Metodo para buscar todos os tipos de imóvel ordenados por descrição
    public List<TipoImovel> findAllOrderByDescricao() {
        EntityManager em = emf.createEntityManager();
        List<TipoImovel> tipos = em.createQuery(
                "SELECT t FROM TipoImovel t ORDER BY t.descricao ASC", TipoImovel.class)
                .getResultList();
        em.close();
        return tipos;
    }

    // Metodo para verificar se um tipo de imovel já existe com a mesma descrição
    public boolean existsByDescricao(String descricao) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT COUNT(t) FROM TipoImovel t WHERE t.descricao = :descricao", Long.class)
                .setParameter("descricao", descricao)
                .getSingleResult();
        em.close();
        return count > 0;
    }

    // Sobrescrever o metodo salvar para evitar duplicação de descrição
    public void salvar(TipoImovel tipoImovel) {
        super.save(tipoImovel);
    }

}