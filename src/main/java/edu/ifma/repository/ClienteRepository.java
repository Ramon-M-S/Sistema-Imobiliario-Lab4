package edu.ifma.repository;

import edu.ifma.models.Clientes;
import edu.ifma.repository.Dao.DaoGeneric;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ClienteRepository extends DaoGeneric<Clientes> {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LAB4_LBD");

    public ClienteRepository(Class<Clientes> entityClass) {
        super(Clientes.class);
    }

    public void salvar(Clientes cliente) {
        if (cpfExists(cliente.getCpf())) {
            throw new RuntimeException("Erro: CPF já cadastrado no sistema!");
        }
        super.save(cliente);
    }

    public boolean cpfExists(String cpf) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT COUNT(c) FROM Clientes c WHERE c.cpf = :cpf", Long.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
        em.close();
        return count > 0;
    }

    // Método para buscar cliente por CPF
    public Clientes findByCpf(String cpf) {
        EntityManager em = emf.createEntityManager();
        Clientes cliente = null;
        try {
            cliente = em.createQuery("SELECT c FROM Clientes c WHERE c.cpf = :cpf", Clientes.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Nenhum cliente encontrado com o CPF " + cpf);
        } finally {
            em.close();
        }
        return cliente;
    }

    // Método para buscar cliente por nome
    public List<Clientes> findByNome(String nome) {
        EntityManager em = emf.createEntityManager();
        List<Clientes> clientes = em.createQuery("SELECT c FROM Clientes c WHERE c.nome LIKE :nome", Clientes.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
        em.close();
        return clientes;
    }

    // Método para buscar cliente por email
    public Clientes findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        Clientes cliente = null;
        try {
            cliente = em.createQuery("SELECT c FROM Clientes c WHERE c.email = :email", Clientes.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Nenhum cliente encontrado com o email " + email);
        } finally {
            em.close();
        }
        return cliente;
    }
}
