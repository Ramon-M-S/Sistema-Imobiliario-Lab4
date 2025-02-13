package edu.ifma.service;

import edu.ifma.models.Clientes;
import edu.ifma.models.Imoveis;
import edu.ifma.models.Locacao;
import edu.ifma.repository.ImoveisRepository;
import edu.ifma.repository.LocacaoRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LocacaoService {

    private final LocacaoRepository locacaoRepository;

    public LocacaoService() {
        this.locacaoRepository = new LocacaoRepository();
        ImoveisRepository imoveisRepository = new ImoveisRepository();
    }

    // Registrar uma nova locação
    public void registrarLocacao(Clientes inquilino, Imoveis imovel, Date dataInicio, Date dataFim, Double valorAluguel) {

        // Verificar se já existe uma locação ativa para o imóvel
        boolean isImovelAlugado = locacaoRepository.isImovelAlugado(imovel.getId());
        if (isImovelAlugado) {
            throw new RuntimeException("Erro: O imóvel já está alugado e não pode ser locado no momento.");
        }

        // Criar nova locação
        Locacao novaLocacao = new Locacao();
        novaLocacao.setInquilino(inquilino);
        novaLocacao.setImovel(imovel);
        novaLocacao.setDataInicio(dataInicio);
        novaLocacao.setDataFim(dataFim);
        novaLocacao.setValorAluguel(BigDecimal.valueOf(valorAluguel));
        novaLocacao.setAtivo(true); // A locação agora está ativa

        // Salvar no banco de dados
        locacaoRepository.salvar(novaLocacao);
    }

    // Encerrar uma locação (tornar inativa)
    public void encerrarLocacao(int locacaoId) {
        Locacao locacao = locacaoRepository.findById(locacaoId);
        if (locacao != null) {
            locacao.setAtivo(false); // Encerra a locação
            locacaoRepository.update(locacao);
        } else {
            throw new RuntimeException("Erro: Locação não encontrada com o ID " + locacaoId);
        }
    }

    // Buscar locações ativas de um cliente (inquilino)
    public List<Locacao> buscarLocacoesAtivasPorCliente(int clienteId) {
        return locacaoRepository.findByClienteId(clienteId).stream()
                .filter(Locacao::isAtivo)
                .collect(Collectors.toList());
    }

    // Buscar locações ativas de um imóvel
    public List<Locacao> buscarLocacoesAtivasPorImovel(int imovelId) {
        return locacaoRepository.findByImovelId(imovelId).stream()
                .filter(Locacao::isAtivo)
                .collect(Collectors.toList());
    }

    // Calcular o valor total de uma locação (considerando multa, se houver)
    public BigDecimal calcularValorTotalLocacao(int locacaoId) {
        Locacao locacao = locacaoRepository.findById(locacaoId);
        if (locacao == null) {
            throw new RuntimeException("Erro: Locação não encontrada com o ID " + locacaoId);
        }

        BigDecimal valorAluguel = locacao.getValorAluguel();
        BigDecimal percentualMulta = locacao.getPercentualMulta();

        // Se a locação estiver ativa e a data de fim já passou, aplicar multa
        if (locacao.isAtivo() && new Date().after(locacao.getDataFim())) {
            BigDecimal multa = valorAluguel.multiply(percentualMulta).divide(BigDecimal.valueOf(100));
            return valorAluguel.add(multa);
        }

        return valorAluguel;
    }
}