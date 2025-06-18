package src;

public class Produto {

    private String codigoProduto;
    private String descricaoProduto;
    private float Valor;
    private String validade;

    public Produto(String codigoProduto, String descricaoProduto, float Valor, String validade) {
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.Valor = Valor;
        this.validade = validade;

    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float valor) {
        Valor = valor;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
