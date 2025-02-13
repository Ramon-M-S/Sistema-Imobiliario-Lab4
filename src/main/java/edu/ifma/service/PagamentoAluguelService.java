package edu.ifma.service;

import edu.ifma.models.Alugueis;
import edu.ifma.repository.AlugueisRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PagamentoAluguelService {
    private final AlugueisRepository alugueisRepository;

    public PagamentoAluguelService() {
        this.alugueisRepository = new AlugueisRepository();
    }

    // Registrar pagamento do aluguel e calcular multa se houver atraso
    public void registrarPagamento(int locacaoId, BigDecimal valorPago, Date dataPagamento) {
        // Buscar o aluguel associado à locação
        Alugueis aluguel = alugueisRepository.findByLocacaoId(locacaoId);

        if (aluguel == null) {
            throw new RuntimeException("Erro: Nenhum aluguel encontrado para a locação ID " + locacaoId);
        }

        // Calcular multa se o pagamento estiver atrasado
        BigDecimal multa = calcularMulta(aluguel.getDataVencimento(), dataPagamento, aluguel.getValorPago());
        BigDecimal valorFinal = aluguel.getValorPago().add(multa);

        // Atualizar pagamento no aluguel
        aluguel.setDataPagamento(dataPagamento);
        aluguel.setValorPago(valorFinal);
        alugueisRepository.update(aluguel);
    }

    // Calcular multa de 0,33% por dia de atraso, limitada a 20% do valor do aluguel
    private static BigDecimal calcularMulta(Date dataVencimento, Date dataPagamento, BigDecimal valorAluguel) {
        if (!dataPagamento.after(dataVencimento)) {
            return BigDecimal.ZERO; // Sem multa se pago no prazo
        }

        // Calcular dias de atraso sem conversão
        long diasAtraso = TimeUnit.MILLISECONDS.toDays(dataPagamento.getTime() - dataVencimento.getTime());

        // Calcular multa (0,33% ao dia, limitada a 20% do valor do aluguel)
        BigDecimal taxaMultaDiaria = new BigDecimal("0.0033");
        BigDecimal multaCalculada = valorAluguel.multiply(taxaMultaDiaria)
                .multiply(BigDecimal.valueOf(diasAtraso));
        BigDecimal multaMaxima = valorAluguel.multiply(new BigDecimal("0.20"));

        return multaCalculada.min(multaMaxima).setScale(2, RoundingMode.HALF_UP); // Arredondar para 2 casas
    }
}