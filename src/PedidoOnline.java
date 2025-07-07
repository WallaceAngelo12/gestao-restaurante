package src;

public class PedidoOnline extends  Pedido{
    private Endereco enderecoEntrega;

    public PedidoOnline(String codigoPedido, Endereco endereco){
        super(codigoPedido);
        this.enderecoEntrega = enderecoEntrega;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public String getLocalizacao(){
        return enderecoEntrega.enderecoReturn();
    }
}
