package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotaFiscal {
    private LocalDateTime dataEmissao;
    private float valorTotal;
    private String numero;

    public NotaFiscal(String numero, float valorTotal) {
        this.numero = numero;
        this.valorTotal = valorTotal;
        this.dataEmissao = null;
    }

    public void emitirNotaFiscal(){
        this.dataEmissao = LocalDateTime.now(); //pega a data e hora atual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("====== Nota Fiscal Emitida ======");
        System.out.println("número: " +  numero);
        System.out.printf("Valor Total: R$ %.2f%n", valorTotal);
        System.out.println("Data de Emissao: R$ " + dataEmissao.format(formatter));
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    public String getNumero() {
        return numero;
    }


}
