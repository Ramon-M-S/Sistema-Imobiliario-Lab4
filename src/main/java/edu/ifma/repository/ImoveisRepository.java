package edu.ifma.repository;

import edu.ifma.models.Imoveis;
import edu.ifma.repository.Dao.DaoGeneric;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ImoveisRepository extends DaoGeneric<Imoveis> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LAB4_LBD");

    public ImoveisRepository() {
        super(Imoveis.class);
    }

    // Método para buscar imóveis por proprietário
    public List<Imoveis> findByProprietarioId(int proprietarioId) {
        EntityManager em = emf.createEntityManager();
        List<Imoveis> imoveis = em.createQuery(
                        "SELECT i FROM Imoveis i WHERE i.proprietario.id = :proprietarioId", Imoveis.class)
                .setParameter("proprietarioId", proprietarioId)
                .getResultList();
        em.close();
        return imoveis;
    }

    // Método para buscar imóveis por tipo de imóvel
    public List<Imoveis> findByTipoImovelId(int tipoImovelId) {
        EntityManager em = emf.createEntityManager();
        List<Imoveis> imoveis = em.createQuery(
                        "SELECT i FROM Imoveis i WHERE i.TipoImovel.id = :tipoImovelId", Imoveis.class)
                .setParameter("tipoImovelId", tipoImovelId)
                .getResultList();
        em.close();
        return imoveis;
    }

    // Método para buscar imóveis por bairro
    public List<Imoveis> findByBairro(String bairro) {
        EntityManager em = emf.createEntityManager();
        List<Imoveis> imoveis = em.createQuery(
                        "SELECT i FROM Imoveis i WHERE i.bairro LIKE :bairro", Imoveis.class)
                .setParameter("bairro", "%" + bairro + "%")
                .getResultList();
        em.close();
        return imoveis;
    }

    // Método para buscar imóveis com valor de aluguel sugerido menor ou igual a um valor máximo
    public List<Imoveis> findByValorAluguelSugeridoMenorQue(double valorMaximo) {
        EntityManager em = emf.createEntityManager();
        List<Imoveis> imoveis = em.createQuery(
                        "SELECT i FROM Imoveis i WHERE i.valorAluguelSugerido <= :valorMaximo", Imoveis.class)
                .setParameter("valorMaximo", valorMaximo)
                .getResultList();
        em.close();
        return imoveis;
    }

    // Método para buscar imóveis por número de dormitórios
    public List<Imoveis> findByDormitorios(Byte dormitorios) {
        EntityManager em = emf.createEntityManager();
        List<Imoveis> imoveis = em.createQuery(
                        "SELECT i FROM Imoveis i WHERE i.dormitorios = :dormitorios", Imoveis.class)
                .setParameter("dormitorios", dormitorios)
                .getResultList();
        em.close();
        return imoveis;
    }

    // Método para buscar imóveis por número de vagas de garagem
    public List<Imoveis> findByVagasGaragem(Byte vagasGaragem) {
        EntityManager em = emf.createEntityManager();
        List<Imoveis> imoveis = em.createQuery(
                        "SELECT i FROM Imoveis i WHERE i.vagasGaragem = :vagasGaragem", Imoveis.class)
                .setParameter("vagasGaragem", vagasGaragem)
                .getResultList();
        em.close();
        return imoveis;
    }

}